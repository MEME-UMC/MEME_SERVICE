package umc.meme.shop.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;

public interface FavoritePortfolioRepository extends JpaRepository<FavoritePortfolio, Long> {
}
