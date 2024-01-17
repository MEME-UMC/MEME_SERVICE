package umc.meme.shop.domain.portfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePortfolioDto {
    private Category category;
    private String makeupName;
    private int price;
    private String info;
    private boolean isBlock;
}
