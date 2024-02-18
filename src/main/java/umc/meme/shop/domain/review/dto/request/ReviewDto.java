package umc.meme.shop.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;

@Data
public class ReviewDto {
    @NotBlank(message = "modelId를 입력해주세요")
    private Long modelId;
    @NotBlank(message = "reservationId를 입력해주세요")
    private Long reservationId;
    @NotBlank(message = "별점을 입력해주세요")
    private int star;
    private String comment;
    private List<String> reviewImgSrc;

}
