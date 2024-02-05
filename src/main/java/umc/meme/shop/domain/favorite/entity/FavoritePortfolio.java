package umc.meme.shop.domain.favorite.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.user.User;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavoritePortfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoritePortfolioId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User model;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;
}
