package umc.meme.shop.domain.artist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final PortfolioRepository portfolioRepository;

    //포트폴리오 생성
    public void createPortfolio(Long artistId, CreatePortfolioDto portfolioDto){
        Optional<Artist> artist = artistRepository.findById(artistId);
        if(artist.isEmpty())
            throw new GlobalException(ErrorStatus.NOT_EXIST_USER);

        Portfolio portfolio = Portfolio.builder()
                .artist(artist.get())
                .category(portfolioDto.getCategory())
                .makeupName(portfolioDto.getMakeupName())
                .info(portfolioDto.getInfo())
                .price(portfolioDto.getPrice())
                .build();
        portfolioRepository.save(portfolio);
    }
}
