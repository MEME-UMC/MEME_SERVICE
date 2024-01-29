package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;
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

    private String averageStars;

    private List<PortfolioImgDto> portfolioImgDtoList;

    private List<ReviewResponseDto> reviewResponseDtoList;

    public static PortfolioDto from(Portfolio portfolio) {
        // 리뷰 리스트를 ReviewResponseDto 리스트로 변환
        List<ReviewResponseDto> reviewResponseDtoList = portfolio.getReviewList()
                .stream()
                .map(ReviewResponseDto::from)
                .toList();

        // 별점 평균 계산
        String averageStars = calculateAverageStars(reviewResponseDtoList);

        // PortfolioImg 리스트를 PortfolioImgDto 리스트로 변환
        List<PortfolioImgDto> portfolioImgDtoList = portfolio.getPortfolioImgList()
                .stream()
                .map(portfolioImg -> new PortfolioImgDto(portfolioImg.getPortfolioImgId(), portfolioImg.getSrc(), false))
                .toList();

        System.out.println(reviewResponseDtoList);

    private int reviewCount; //리뷰 개수

    public static PortfolioDto from(Portfolio portfolio){
        return PortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .isBlock(portfolio.isBlock())
                .portfolioImgDtoList(portfolioImgDtoList)
                .reviewResponseDtoList(reviewResponseDtoList)
                .averageStars(portfolio.getAverageStars())
                .reviewCount(portfolio.getReviewList().size())
                .build();
    }

    //관심 메이크업
    public static PortfolioDto from(FavoritePortfolio favoritePortfolio){
        Portfolio portfolio = favoritePortfolio.getPortfolio();
        return PortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .isBlock(portfolio.isBlock())
                .averageStars(portfolio.getAverageStars())
                .reviewCount(portfolio.getReviewList().size())
                .build();
    }

}
