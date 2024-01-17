package umc.meme.shop.domain.review.dto.request;

import lombok.Data;

@Data
public class ReviewDto {
    private int star;
    private String comment;
}
