package umc.meme.shop.domain.review.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponseDto {
    private List<ReviewResponseDto> reviewResponseDtoList;
    private Map<Integer, Integer> starStatus;

    public static ReviewListResponseDto from(List<ReviewResponseDto> reviewResponseDtoList, Map<Integer, Integer> starStatus) {
        return ReviewListResponseDto.builder()
                .reviewResponseDtoList(reviewResponseDtoList)
                .starStatus(starStatus)
                .build();
    }
}
