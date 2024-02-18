package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.meme.shop.domain.review.entity.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListPageDto {
    private List<ReviewResponseDto> content;
    private Map<Integer, Integer> starStatus;
    private int currentPage; //현재 페이지 번호
    private int pageSize; //페이지 크기
    private int totalNumber; //전체 메이크업 개수
    private int totalPage; //전체 페이지 개수

    public static ReviewListPageDto from(Page<Review> page){
        List<ReviewResponseDto> content = page.stream()
                .map(ReviewResponseDto::from)
                .toList();

        return ReviewListPageDto.builder()
                .content(content)
                .starStatus(setStarStatus(page.getContent())) //별점 현황
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    private static Map<Integer, Integer> setStarStatus(List<Review> list){

        Map<Integer, Integer> starStatus = new HashMap<>(Map.of(5, 0, 4, 0, 3, 0, 2, 0, 1, 0));
        for (Review review : list) {
            int star = review.getStar();
            starStatus.put(star, starStatus.get(star) + 1);
        }
        return starStatus;
    }
}
