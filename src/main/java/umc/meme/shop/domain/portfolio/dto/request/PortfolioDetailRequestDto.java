package umc.meme.shop.domain.portfolio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDetailRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long portfolioId;
}
