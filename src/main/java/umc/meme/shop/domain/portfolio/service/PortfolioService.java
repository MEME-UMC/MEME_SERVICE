package umc.meme.shop.domain.portfolio.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final ArtistRepository artistRepository;
    private final PortfolioRepository portfolioRepository;

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
}
