package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.ReviewImg;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewImgDto {
    private Long reviewImgId;
    private String reviewImgSrc;

    public static ReviewImgDto from(ReviewImg img){
        return ReviewImgDto.builder()
                .reviewImgId(img.getReviewImgId())
                .reviewImgSrc(img.getSrc())
                .build();
    }

}
