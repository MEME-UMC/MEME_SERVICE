package umc.meme.shop.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewDto {
    @NotBlank(message = "portfolioId를 입력해주세요")
    private Long portfolioId;
    @NotBlank(message = "별점을 입력해주세요")
    private int star;
    private String comment;
}
