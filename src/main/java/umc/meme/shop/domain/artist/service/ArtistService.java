package umc.meme.shop.domain.artist.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.domain.reservation.dto.response.ReservationResponseDto;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final PortfolioRepository portfolioRepository;
    private final ReservationRepository reservationRepository;

    //포트폴리오 생성
    @Transactional
    public void createPortfolio(Long artistId, CreatePortfolioDto portfolioDto){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        Portfolio portfolio = Portfolio.builder()
                .artist(artist)
                .category(portfolioDto.getCategory())
                .makeupName(portfolioDto.getMakeupName())
                .info(portfolioDto.getInfo())
                .price(portfolioDto.getPrice())
                .build();
        portfolioRepository.save(portfolio);
    }

    //예약 조회
    public List<ReservationResponseDto> getReservation(Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

         List<Reservation> reservationList = reservationRepository.findByArtist(artist);
         return reservationList.stream()
                 .map(ReservationResponseDto::from)
                 .collect(Collectors.toList());
    }
}
