package umc.meme.shop.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewDto {
    @NotBlank(message = "modelId를 입력해주세요")
    private Long modelId;
    @NotBlank(message = "reservationId를 입력해주세요")
    private Long reservationId;
    @NotBlank(message = "별점을 입력해주세요")
    private int star;
    private String comment;
}
