package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImgDto {
    private Long ReviewImgId;
    private String reviewImgSrc;
    private boolean isDelete = false;
}
