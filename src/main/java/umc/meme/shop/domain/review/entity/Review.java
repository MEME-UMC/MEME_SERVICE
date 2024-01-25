package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name="model_id", nullable = false)
    private Model model;

    @Column(nullable = false)
    private int star;

    @Column(nullable = true, length = 200)
    private String comment;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean isBlock;
}
