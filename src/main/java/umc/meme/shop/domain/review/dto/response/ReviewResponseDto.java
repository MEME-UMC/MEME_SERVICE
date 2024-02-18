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
    private String modelNickName;
    private int star;
    private String comment;
    private List<ReviewImgDto> reviewImgDtoList;

    public static ReviewResponseDto from(Review review){
        List<ReviewImgDto> reviewImgDtoList = review.getReviewImgList()
                .stream()
                .map(ReviewImgDto::from)
                .toList();

        return ReviewResponseDto.builder()
                .modelNickName(review.getModel().getNickname())
                .star(review.getStar())
                .comment(review.getComment())
                .reviewImgDtoList(reviewImgDtoList)
                .build();
    }
}
