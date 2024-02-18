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
public class CreatePortfolioDto {
    @NotBlank(message = "artistId를 입력해주세요.")
    private Long artistId;
    @NotBlank(message = "카테고리를 입력해주세요")
    private Category category;
    @NotBlank(message = "메이크업 명을 입력해주세요")
    private String makeupName;
    @NotBlank(message = "가격을 입력해주세요")
    private int price;
    @NotBlank(message = "메이크업 정보를 입력해주세요")
    private String info;
    @NotBlank(message = "포트폴리오 이미지를 업로드해주세요")
    private List<String> portfolioImgSrc;
}
