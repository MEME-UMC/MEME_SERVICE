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
        Optional<Model> model = modelRepository.findById(reservationDto.getModelId());
        if(model.isEmpty()){
            throw new GlobalException(ErrorStatus.NOT_EXIST_USER);
        }

        Optional<Portfolio> portfolio = portfolioRepository.findById(reservationDto.getPortfolioId());
        if(portfolio.isEmpty()){
            throw new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO);
        }
        Reservation reservation = Reservation.builder()
                .model(model.get())
                .portfolio(portfolio.get())
                .status(Status.EXPECTED)
                .reservationTime(reservationDto.getReservationTime())
                .reservationDate(reservationDto.getReservationDate())
                .build();

        reservationRepository.save(reservation);
    }

    //예약하기 상태 변경
    @Transactional
    public void updateReservationStatus(Long reservationId, AlterReservationDto reservationDto){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));
        Status status = reservationDto.getStatus();
        reservation.updateReservation(status);
    }

    //아티스트 예약 조회
    public List<ReservationResponseDto> getArtistReservation(Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        List<Reservation> reservationList = reservationRepository.findByArtist(artist);
        return reservationList.stream()
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }

    //모델 예약 조회
    public List<ReservationResponseDto> getModelReservation(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        List<Reservation> reservationList = reservationRepository.findByModel(model);
        return reservationList.stream()
                .map(ReservationResponseDto::from)
                .collect(Collectors.toList());
    }
}
