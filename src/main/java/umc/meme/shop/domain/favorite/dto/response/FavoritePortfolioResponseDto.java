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

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePortfolioResponseDto {
    private String portfolioImg;
    private String artistNickName;

    public static FavoritePortfolioResponseDto from(FavoritePortfolio favoritePortfolio) {

        Portfolio portfolio = favoritePortfolio.getPortfolio();
        Artist artist = portfolio.getArtist();

        return FavoritePortfolioResponseDto.builder()
                .artistNickName(artist.getNickname())
                .portfolioImg(portfolio.getPortfolioImgList().get(0).getSrc())
                .build();

    }
}
