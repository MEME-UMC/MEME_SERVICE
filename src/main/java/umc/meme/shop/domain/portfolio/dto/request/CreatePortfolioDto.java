package umc.meme.shop.domain.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePortfolioDto {
    @NotBlank(message = "카테고리를 입력해주세요")
    private Category category;
    @NotBlank(message = "메이크업 명을 입력해주세요")
    private String makeupName;
    @NotBlank(message = "가격을 입력해주세요")
    private int price;
    @NotBlank(message = "메이크업 정보를 입력해주세요")
    private String info;
}
