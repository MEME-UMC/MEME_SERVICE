package umc.meme.shop.domain.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PortfolioImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioImgId;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false)
    private String src;
}
