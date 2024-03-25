package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDetailsDto {
    private String artistNickName;
    private String makeupName;
    private int star;
    private String comment;
    private List<ReviewImgDto> reviewImgDtoList;

    public static ReviewDetailsDto from(Review review){
        List<ReviewImgDto> reviewImgDtoList = review.getReviewImgList()
                .stream()
                .map(ReviewImgDto::from)
                .toList();

        return ReviewDetailsDto.builder()
                .artistNickName(review.getPortfolio().getArtist().getNickname())
                .makeupName(review.getPortfolio().getMakeupName())
                .star(review.getStar())
                .comment(review.getComment())
                .reviewImgDtoList(reviewImgDtoList)
                .build();
    }
}
