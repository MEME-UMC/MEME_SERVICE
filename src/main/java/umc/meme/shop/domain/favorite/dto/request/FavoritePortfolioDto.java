package umc.meme.shop.domain.favorite.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FavoritePortfolioDto {
    @NotBlank(message = "modelId를 입력해주세요.")
    private Long modelId;
    @NotBlank(message = "portfolioId를 입력해주세요.")
    private Long portfolioId;
}
