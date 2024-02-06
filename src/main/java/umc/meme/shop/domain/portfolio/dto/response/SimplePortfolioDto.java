package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.global.enums.MakeupLocation;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplePortfolioDto {
    private Long portfolioId;
    private String portfolioImg;
    private Category category;
    private String makeupName;
    private String artistName;
    private int price;
    private MakeupLocation makeupLocation; //샵 재직 여부

    public static SimplePortfolioDto from(FavoritePortfolio favoritePortfolio){
        Portfolio portfolio = favoritePortfolio.getPortfolio();
        Artist artist = portfolio.getArtist();

        return SimplePortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .portfolioImg(portfolio.getPortfolioImgList().get(0).getSrc())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .artistName(artist.getNickname())
                .price(portfolio.getPrice())
                .makeupLocation(artist.getMakeupLocation())
                .build();
    }

    public static SimplePortfolioDto from(Portfolio portfolio){
        Artist artist = portfolio.getArtist();

        return SimplePortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .portfolioImg(portfolio.getPortfolioImgList().get(0).getSrc())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .artistName(artist.getNickname())
                .price(portfolio.getPrice())
                .makeupLocation(artist.getMakeupLocation())
                .build();
    }

}
