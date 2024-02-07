package umc.meme.shop.domain.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioImgDto {
    private Long portfolioImgId;

    //이미지 링크
    private String portfolioImgSrc;

    //삭제 여부
    private boolean isDelete = false;

    public static PortfolioImgDto from(PortfolioImg img){
        return PortfolioImgDto.builder()
                .portfolioImgId(img.getPortfolioImgId())
                .portfolioImgSrc(img.getSrc())
                .isDelete(false)
                .build();
    }
}
