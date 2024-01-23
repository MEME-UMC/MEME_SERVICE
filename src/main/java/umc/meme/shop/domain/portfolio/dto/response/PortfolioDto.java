package umc.meme.shop.domain.portfolio.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {
    private Long portfolioId;

    private Category category;

    private String makeupName;

    private int price;

    private String info;

    public static PortfolioDto from(Portfolio portfolio){
        return PortfolioDto.builder()
                .category(portfolio.getCategory())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .info(portfolio.getInfo())
                .build();
    }
}
