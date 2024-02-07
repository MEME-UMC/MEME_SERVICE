package umc.meme.shop.domain.favorite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.portfolio.dto.response.SimplePortfolioDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePortfolioResponsePageDto {
    private List<SimplePortfolioDto> content;
    private int currentPage; //현재 페이지 번호
    private int pageSize; //페이지 크기
    private int totalNumber; //전체 메이크업 개수
    private int totalPage; //전체 페이지 개수

    public static FavoritePortfolioResponsePageDto from(Page<FavoritePortfolio> page){
        List<SimplePortfolioDto> content = page.stream()
                .map(SimplePortfolioDto::from)
                .toList();

        return FavoritePortfolioResponsePageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
