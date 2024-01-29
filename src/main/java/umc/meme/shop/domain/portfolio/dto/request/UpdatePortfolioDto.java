package umc.meme.shop.domain.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioImgDto;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePortfolioDto {
    @NotBlank(message = "artistId를 입력해주세요.")
    private Long artistId;
    @NotBlank(message = "portfolioId를 입력해주세요.")
    private Long portfolioId;
    private Category category;
    private String makeupName;
    private int price;
    private String info;
    private boolean isBlock;
    private PortfolioImgDto portfolioImg;
}
