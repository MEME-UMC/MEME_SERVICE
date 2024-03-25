package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyReviewResponseDto {
    private Long reviewId;
    private String artistNickName;
    private String portfolioImg;
    private String location; //장소
    private LocalDateTime createdAt;

    public static MyReviewResponseDto from(Review review){
        return MyReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .artistNickName(review.getPortfolio().getArtist().getNickname())
                .portfolioImg(review.getPortfolio().getPortfolioImgList().get(0).getSrc()) //첫 번째 포트폴리오 이미지
                .location(review.getPortfolio().getArtist().getShopLocation())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
