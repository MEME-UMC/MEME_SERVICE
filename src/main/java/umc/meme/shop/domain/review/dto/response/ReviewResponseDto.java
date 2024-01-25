package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private String modelName;
    private int star;
    private String comment;

    public static ReviewResponseDto from(Review review){
        return ReviewResponseDto.builder()
                .modelName(review.getModel().getName())
                .star(review.getStar())
                .comment(review.getComment())
                .build();
    }
}
