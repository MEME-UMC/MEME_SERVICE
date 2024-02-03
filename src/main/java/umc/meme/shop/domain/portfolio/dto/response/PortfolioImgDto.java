package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioImgDto {
    private Long portfolioImgId;

    //이미지 링크
    private String portfolioImgSrc;

    //삭제 여부

    private boolean isDelete = false;
}
