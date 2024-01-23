package umc.meme.shop.domain.portfolio.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

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
                .isBlock(false)
                .build();

        portfolioRepository.save(portfolio);
    }

    // 포트폴리오 조회
    @Transactional
    public List<PortfolioDto> getPortfolio(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        List<Portfolio> portfolioList = portfolioRepository.findByArtist(artist);
        return portfolioList.stream()
                .map(PortfolioDto::from)
                .collect(Collectors.toList());
    }

    // 포트폴리오 수정/삭제
    @Transactional
    public void updatePortfolio(Long portfolioId, UpdatePortfolioDto request) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        portfolio.updatePortfolio(request);
    }

}
