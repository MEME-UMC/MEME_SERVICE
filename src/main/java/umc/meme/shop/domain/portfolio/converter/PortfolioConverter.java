package umc.meme.shop.domain.portfolio.converter;

import org.springframework.data.domain.Page;
import umc.meme.shop.domain.favorite.dto.response.FavoriteArtistResponseDto;
import umc.meme.shop.domain.favorite.dto.response.FavoritePortfolioResponseDto;
import umc.meme.shop.domain.favorite.dto.response.FavoritePortfolioResponsePageDto;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioPageDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;

import java.util.List;

public class PortfolioConverter {

    public static FavoritePortfolioResponsePageDto favoritePortfolioPageConverter(Page<FavoritePortfolio> page){

        List<FavoritePortfolioResponseDto> content = page.stream()
                .map(FavoritePortfolioResponseDto::from)
                .toList();

        return FavoritePortfolioResponsePageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    public static PortfolioPageDto portfolioPageConverter(Page<Portfolio> page){

        List<PortfolioDto> content = page.stream()
                .map(PortfolioDto::from)
                .toList();

        return PortfolioPageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}


