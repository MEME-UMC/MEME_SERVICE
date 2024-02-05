package umc.meme.shop.domain.reservation.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.domain.reservation.dto.request.AlterReservationDto;
import umc.meme.shop.domain.reservation.dto.request.ReservationRequestDto;
import umc.meme.shop.domain.reservation.dto.response.ArtistLocationDto;
import umc.meme.shop.domain.reservation.dto.response.ArtistTimeDto;
import umc.meme.shop.domain.reservation.dto.response.ReservationCompleteDto;
import umc.meme.shop.domain.reservation.dto.response.ReservationResponseDto;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;
import umc.meme.shop.global.exception.GlobalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ArtistRepository artistRepository;
    private final ModelRepository modelRepository;
    private final PortfolioRepository portfolioRepository;
    private final ReservationRepository reservationRepository;

    //아티스트 예약 가능 장소 조회
    public ArtistLocationDto getArtistLocation(Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        return ArtistLocationDto.builder()
                .makeupLocation(artist.getMakeupLocation())
                .shopLocation(artist.getShopLocation())
                .region(artist.getRegion())
                .build();
    }

    //아티스트 예약 가능 시간 조회

    public List<ArtistTimeDto> getArtistTime(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        Map<DayOfWeek, Times> availableDayOfWeekAndTime = artist.getAvailableDayOfWeekAndTime();

        List<ArtistTimeDto> artistTimeList = new ArrayList<>();

        for (Map.Entry<DayOfWeek, Times> entry : availableDayOfWeekAndTime.entrySet()) {
            DayOfWeek dayOfWeek = entry.getKey();
            Times availableTime = entry.getValue();

            ArtistTimeDto artistTimeDto = ArtistTimeDto.builder()
                    .availableDayOfWeek(dayOfWeek)
                    .availableTime(availableTime)
                    .build();

            artistTimeList.add(artistTimeDto);
        }

        return artistTimeList;
    }

    //예약하기
    @Transactional
    public ReservationCompleteDto createReservation(ReservationRequestDto reservationDto){
        Model model = modelRepository.findById(reservationDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(reservationDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        // 두 개 이상의 예약을 한번에 요청한 경우
        if (reservationDto.getReservationDayOfWeekAndTime().size() != 1) {
            throw new GlobalException(ErrorStatus.NOT_ALLOW_OVER_ONE_RESERVATION);
        }

        //예약 중복 처리
        List<Reservation> reservationList = reservationRepository.findByModelAndPortfolio(model, portfolio);
        for(int i=0; i<reservationList.size(); i++){
            if(reservationList.get(i).getReservationDayOfWeekAndTime().equals(reservationDto.getReservationDayOfWeekAndTime())){
                throw new GlobalException(ErrorStatus.TEMP);
            }
        }

        Reservation reservation = Reservation.builder()
                .model(model)
                .portfolio(portfolio)
                .status(Status.EXPECTED)
                .reservationDayOfWeekAndTime(reservationDto.getReservationDayOfWeekAndTime())
                .reservationDate(reservationDto.getReservationDate())
                .location(reservationDto.getLocation())
                .build();

        model.updateReservationList(reservation);
        reservationRepository.save(reservation);

        return ReservationCompleteDto.builder()
                .makeupName(portfolio.getMakeupName())
                .artistNickName(portfolio.getArtist().getNickname())
                .location(reservation.getLocation())
                .reservationDate(reservation.getReservationDate())
                .reservationDayOfWeekAndTime(reservation.getReservationDayOfWeekAndTime())
                .build();
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


}
