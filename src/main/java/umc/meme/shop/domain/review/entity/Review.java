package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.Portfolio;

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

    @Column(nullable = false)
    private int star;

    @Column(nullable = true, length = 200)
    private String comment;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean isBlock;
}
