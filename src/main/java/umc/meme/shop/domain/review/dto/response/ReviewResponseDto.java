package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private String modelName;
    private int star;
    private String comment;
//    private List<ReviewImgDto> reviewImgDtoList;

    public static ReviewResponseDto from(Review review){
//        List<ReviewImgDto> reviewImgDtoList = review.getReviewImgList()
//                .stream()
//                .map(reviewImg -> new ReviewImgDto(reviewImg.getReviewImgId(), reviewImg.getSrc(), false))
//                .toList();

        return ReviewResponseDto.builder()
                .modelName(review.getModel().getName())
                .star(review.getStar())
                .comment(review.getComment())
//                .reviewImgDtoList(reviewImgDtoList)
                .build();
    }
}
