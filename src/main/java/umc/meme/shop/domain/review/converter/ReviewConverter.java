package umc.meme.shop.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.response.ReviewListPageDto;
import umc.meme.shop.domain.review.dto.response.ReviewResponseDto;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewListPageDto reviewPageConverter(Page<Review> page) {

        List<ReviewResponseDto> content = page.stream()
                .map(ReviewResponseDto::from)
                .toList();

        return ReviewListPageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
