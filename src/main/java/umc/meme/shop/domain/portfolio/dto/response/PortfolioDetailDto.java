package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.global.enums.MakeupLocation;
import umc.meme.shop.global.enums.Region;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDetailDto {
    private Long portfolioId;

    private Long userId;

    private Boolean isFavorite;

    private Category category;

    private String artistProfileImg;

    private String artistNickName;

    private String makeupName;

    private int price;

    private String info;

    private MakeupLocation makeupLocation; //샵 재직 여부

    private String shopLocation; //샵 위치

    private List<Region> region; //활동 가능 지역

    private Boolean isBlock;

    private String averageStars;

    private int reviewCount; //리뷰 개수

    private List<PortfolioImgDto> portfolioImgDtoList;

    public static PortfolioDetailDto from(Portfolio portfolio, boolean isFavorite) {
        Artist artist = portfolio.getArtist();

        // PortfolioImg 리스트를 PortfolioImgDto 리스트로 변환
        List<PortfolioImgDto> portfolioImgDtoList = portfolio.getPortfolioImgList()
                .stream()
                .map(PortfolioImgDto::from)
                .toList();

        return PortfolioDetailDto.builder()
                .portfolioId(portfolio.getPortfolioId())
                .userId(portfolio.getArtist().getUserId())
                .isFavorite(isFavorite)
                .category(portfolio.getCategory())
                .artistProfileImg(artist.getProfileImg())
                .artistNickName(artist.getNickname())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .makeupLocation(artist.getMakeupLocation())
                .shopLocation(artist.getShopLocation())
                .region(artist.getRegion())
                .isBlock(portfolio.isBlock())
                .portfolioImgDtoList(portfolioImgDtoList)
                .averageStars(portfolio.getAverageStars())
                .reviewCount(portfolio.getReviewList().size())
                .build();
    }
}
