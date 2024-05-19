package umc.meme.shop.domain.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.global.enums.Category;

import java.util.List;

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
    private Long durationTime;
    private Boolean isBlock;
    private List<String> portfolioImgSrcList;
}

