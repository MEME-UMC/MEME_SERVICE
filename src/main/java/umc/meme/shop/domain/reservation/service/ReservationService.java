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
import umc.meme.shop.domain.reservation.dto.response.ReservationResponseDto;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.domain.reservation.entity.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ArtistRepository artistRepository;
    private final ModelRepository modelRepository;
    private final PortfolioRepository portfolioRepository;
    private final ReservationRepository reservationRepository;

    //예약하기
    @Transactional
    public void createReservation(ReservationRequestDto reservationDto){
        Model model = modelRepository.findById(reservationDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(reservationDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        // 두 개 이상의 예약을 한번에 요청한 경우
        if (reservationDto.getReservationDayOfWeekAndTime().size() != 1) {
            throw new GlobalException(ErrorStatus.NOT_ALLOW_OVER_ONE_RESERVATION);
        }

        Reservation reservation = Reservation.builder()
                .model(model)
                .portfolio(portfolio)
                .status(Status.EXPECTED)
                .reservationDayOfWeekAndTime(reservationDto.getReservationDayOfWeekAndTime())
                .reservationDate(reservationDto.getReservationDate())
                .build();

        model.updateReservationList(reservation);
        reservationRepository.save(reservation);
    }

    //예약하기 상태 변경
    @Transactional
    public void updateReservationStatus(Long reservationId, AlterReservationDto reservationDto){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));
        Status status = reservationDto.getStatus();

        if(reservation.getStatus() == status)
            throw new GlobalException(ErrorStatus.ALREADY_CHANGE_STATUS);
        if(reservation.getStatus() == Status.COMPLETE && status == Status.CANCEL)
            throw new GlobalException(ErrorStatus.INVALID_CHANGE_STATUS);

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
