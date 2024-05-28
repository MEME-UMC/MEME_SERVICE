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
import umc.meme.shop.domain.reservation.dto.request.ReservationTimeRequestDto;
import umc.meme.shop.domain.reservation.dto.response.*;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<AvailableTimeDto> getArtistTime(ReservationTimeRequestDto requestDto) {
        Artist artist = artistRepository.findById(requestDto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        // parse date format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date_str = requestDto.getDate();
        LocalDate date = LocalDate.parse(date_str, dateFormatter);

        List<AvailableTime> availableTimeList = artist.getAvailableTimeList();
        availableTimeList.removeIf(AvailableTime::isReservated);

        // 해당 날짜에 해당하는 테이블만 리턴
        return availableTimeList.stream()
                .filter(t -> t.getDate().equals(date))
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

        //예약 가능 시간 확인, 중복 확인
        if(availableTime.isReservated())
            throw new GlobalException(ErrorStatus.INVAILD_AVAILABLE_TIME);

        if(!portfolio.getArtist().equals(availableTime.getArtist()))
            throw new GlobalException(ErrorStatus.INVAILD_AVAILABLE_TIME);

        //reservation 생성
        Reservation reservation = Reservation.from(model, portfolio, availableTime, reservationDto.getLocation());
        model.updateReservationList(reservation);
        availableTime.updateReservation(reservation);
        reservationRepository.save(reservation);

        //예약 가능 시간 테이블 상태 변경
        availableTime.updateIsReservated(true);

        return ReservationCompleteDto.from(portfolio, reservation);
    }

    //예약하기 상태 변경
    @Transactional
    public void updateReservationStatus(AlterReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getReservationId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));
        Status status = reservationDto.getStatus();

        if (reservation.getStatus() == status)
            throw new GlobalException(ErrorStatus.ALREADY_CHANGE_STATUS);
        if (reservation.getStatus() == Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_CHANGE_STATUS);
        if (reservation.getStatus() == Status.CANCEL && status == Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_CHANGE_COMPLETE);

        AvailableTime availableTime = reservation.getAvailableTime();
        if (reservation.getStatus() == Status.EXPECTED) {
            if (status == Status.COMPLETE) //예약 완료 시
                reservation.updateReservation(status);
            else if (status == Status.CANCEL) { //예약 취소 시
                availableTime.updateIsReservated(false);
                reservationRepository.delete(reservation);
            }
        }
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

    //예약 상세 조회
    public ReservationDetailDto getReservationDetails(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));
        Model model = modelRepository.findById(reservation.getModel().getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        return ReservationDetailDto.from(reservation, model);
    }

}
