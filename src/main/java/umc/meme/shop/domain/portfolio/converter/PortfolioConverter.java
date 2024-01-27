package umc.meme.shop.domain.portfolio.converter;

import org.springframework.data.domain.Page;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioPageDto;

import java.util.List;

public class PortfolioConverter {

    public static PortfolioPageDto portfolioConverter(Page<FavoritePortfolio> page){

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


