package umc.meme.shop.domain.favorite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePortfolioResponseDto {
    private Long portfolioId;
    private String portfolioImg;
    private Category category;
    private String makeupName;
    private int price;

    public static FavoritePortfolioResponseDto from(FavoritePortfolio favoritePortfolio) {

        Portfolio portfolio = favoritePortfolio.getPortfolio();

        return FavoritePortfolioResponseDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .portfolioImg(portfolio.getPortfolioImgList().get(0).getSrc())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .build();

    }
}
