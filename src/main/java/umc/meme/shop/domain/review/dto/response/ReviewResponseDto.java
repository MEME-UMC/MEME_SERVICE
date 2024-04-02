package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private String modelNickName;
    private String modelProfileImg;
    private int star;
    private String comment;
    private LocalDateTime createdAt; //리뷰 작성 날짜
    private List<ReviewImgDto> reviewImgDtoList;

    public static ReviewResponseDto from(Review review){
        List<ReviewImgDto> reviewImgDtoList = review.getReviewImgList()
                .stream()
                .map(ReviewImgDto::from)
                .toList();

        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .modelNickName(review.getModel().getNickname())
                .modelProfileImg(review.getModel().getProfileImg())
                .star(review.getStar())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .reviewImgDtoList(reviewImgDtoList)
                .build();
    }
}
