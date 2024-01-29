package umc.meme.shop.domain.portfolio.dto.response;

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

    private String averageStars;

    private int reviewCount; //리뷰 개수

    private List<PortfolioImgDto> portfolioImgDtoList;

    private List<ReviewResponseDto> reviewResponseDtoList;

    public static PortfolioDto from(Portfolio portfolio) {

        // PortfolioImg 리스트를 PortfolioImgDto 리스트로 변환
        List<PortfolioImgDto> portfolioImgDtoList = portfolio.getPortfolioImgList()
                .stream()
                .map(portfolioImg -> new PortfolioImgDto(portfolioImg.getPortfolioImgId(), portfolioImg.getSrc(), false))
                .toList();
//        System.out.println("getPortfolio");
//        System.out.println(portfolio.getPortfolioImgList());
//        System.out.println(portfolioImgDtoList);
//        System.out.println("Converting Portfolio ID " + portfolio.getPortfolioId() + " with " + portfolio.getPortfolioImgList().size() + " images");

        return PortfolioDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .isBlock(portfolio.isBlock())
                .portfolioImgDtoList(portfolioImgDtoList)
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
