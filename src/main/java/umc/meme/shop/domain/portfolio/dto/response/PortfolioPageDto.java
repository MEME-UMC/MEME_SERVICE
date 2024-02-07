package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioPageDto {
    private List<PortfolioDto> content;
    private int currentPage; //현재 페이지 번호
    private int pageSize; //페이지 크기
    private int totalNumber; //전체 메이크업 개수
    private int totalPage; //전체 페이지 개수

    public static PortfolioPageDto from(Page<Portfolio> page){
        //검색 결과가 없을 시
        if(page.getContent().isEmpty())
            throw new GlobalException(ErrorStatus.SEARCH_NOT_FOUNT);

        List<PortfolioDto> content = page.stream()
                .map(PortfolioDto::from)
                .toList();

        return PortfolioPageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
