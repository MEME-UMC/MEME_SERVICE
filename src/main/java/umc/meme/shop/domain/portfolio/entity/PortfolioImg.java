package umc.meme.shop.domain.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.meme.shop.domain.common.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter
public class PortfolioImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioImgId;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false)
    private String src;

    public PortfolioImg(String src) {
        this.src = src;
    }

    public void updateSrc(String src) {
        this.src = src;
    }
}
