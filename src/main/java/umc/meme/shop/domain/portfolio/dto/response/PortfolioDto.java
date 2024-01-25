package umc.meme.shop.domain.portfolio.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;
import umc.meme.shop.domain.review.dto.response.ReviewResponseDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {
    private Long portfolioId;

    private Category category;

    private String makeupName;

    private int price;

    private String info;

    private Boolean isBlock;

    private List<ReviewResponseDto> reviewResponseDtoList;

    public static PortfolioDto from(Portfolio portfolio){
        List<ReviewResponseDto> reviewResponseDtoList = portfolio.getReviewList()
                .stream()
                .map(ReviewResponseDto::from)
                .toList();
        return PortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .isBlock(portfolio.isBlock())
                .reviewResponseDtoList(reviewResponseDtoList)
                .build();
    }

    //관심 메이크업
    public static PortfolioDto from(FavoritePortfolio favoritePortfolio){
        Portfolio portfolio = favoritePortfolio.getPortfolio();

        List<ReviewResponseDto> reviewResponseDtoList = portfolio.getReviewList()
                .stream()
                .map(ReviewResponseDto::from)
                .toList();

        return PortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .isBlock(portfolio.isBlock())
                .reviewResponseDtoList(reviewResponseDtoList)
                .build();
    }
}
