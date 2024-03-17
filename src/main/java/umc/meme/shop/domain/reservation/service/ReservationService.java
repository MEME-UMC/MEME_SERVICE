package umc.meme.shop.domain.reservation.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.response.AvailableTimeDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.artist.repository.AvailableTimeRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.domain.reservation.dto.request.AlterReservationDto;
import umc.meme.shop.domain.reservation.dto.request.ReservationRequestDto;
import umc.meme.shop.domain.reservation.dto.response.ArtistLocationDto;
import umc.meme.shop.domain.reservation.dto.response.ReservationCompleteDto;
import umc.meme.shop.domain.reservation.dto.response.ReservationResponseDto;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ArtistRepository artistRepository;
    private final ModelRepository modelRepository;
    private final PortfolioRepository portfolioRepository;
    private final ReservationRepository reservationRepository;
    private final AvailableTimeRepository availableTimeRepository;

    //아티스트 예약 가능 장소 조회
    public ArtistLocationDto getArtistLocation(Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        return ArtistLocationDto.from(artist);
    }

    //아티스트 예약 가능 시간 조회
    public List<AvailableTimeDto> getArtistTime(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        List<AvailableTime> availableTimeList = artist.getAvailableTimeList();
        availableTimeList.removeIf(AvailableTime::isReservated);

        return availableTimeList.stream()
                .map(AvailableTimeDto::from)
                .toList();
    }

    //예약하기
    @Transactional
    public ReservationCompleteDto createReservation(ReservationRequestDto reservationDto){
        Model model = modelRepository.findById(reservationDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(reservationDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        AvailableTime availableTime = availableTimeRepository.findById(reservationDto.getAvailableTimeId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.INVAILD_AVAILABLE_TIME));


        //예약 중복 처리
        List<Reservation> reservationList = reservationRepository.findByModelAndPortfolio(model, portfolio);
        for(int i=0; i<reservationList.size(); i++){
            Reservation reservation = reservationList.get(i);
            if(reservation.getDayOfWeek().equals(reservationDto.getDayOfWeek())
                    && reservation.getTimes().equals(reservationDto.getTimes())
                && checkDuplicateReservation(reservation.getReservationDate(), reservationDto.getReservationDate())){
                throw new GlobalException(ErrorStatus.NOT_ALLOW_DUPLICATED_RESERVATION);
            }
        }

        //reservation 생성
        Reservation reservation = Reservation.from(model, portfolio, reservationDto);
        model.updateReservationList(reservation);
        reservationRepository.save(reservation);

        //예약 가능 시간 테이블 상태 변경
        availableTime.updateIsReservated(true);

        return ReservationCompleteDto.from(portfolio, reservation);
    }

    //예약하기 상태 변경
    @Transactional
    public void updateReservationStatus(AlterReservationDto reservationDto){
        Reservation reservation = reservationRepository.findById(reservationDto.getReservationId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));
        Status status = reservationDto.getStatus();

        if(reservation.getStatus() == status)
            throw new GlobalException(ErrorStatus.ALREADY_CHANGE_STATUS);
        if(reservation.getStatus() == Status.COMPLETE && status == Status.CANCEL)
            throw new GlobalException(ErrorStatus.INVALID_CHANGE_STATUS);
        if(reservation.getStatus() == Status.CANCEL && status == Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_CHANGE_COMPLETE);

        reservation.updateReservation(status);
        // TODO : 아티스트 예약 가능 시간 값 변경
    }

    //아티스트 예약 조회
    public List<ReservationResponseDto> getArtistReservation(Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        List<Reservation> reservationList = reservationRepository.findByArtist(artist);
        return reservationList.stream()
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    //모델 예약 조회
    public List<ReservationResponseDto> getModelReservation(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        List<Reservation> reservationList = model.getReservationList();
        return reservationList.stream()
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    private boolean checkDuplicateReservation(Date date1, Date date2){
        //두 date가 동일하면 false
        if(date1.getYear() == date2.getYear()){
            if(date1.getMonth() == date2.getMonth())
                if(date1.getDay() == date2.getDay())
                    return true;
        }
        return false;
    }


}
