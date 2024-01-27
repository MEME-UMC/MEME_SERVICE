package umc.meme.shop.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewDto {
    @NotBlank(message = "artistId를 입력해주세요")
    private Long artistId;
    @NotBlank(message = "reviewId를 입력해주세요")
    private Long reviewId;
    @NotBlank(message = "block 여부를 입력해주세요")
    private boolean isBlock;
}
