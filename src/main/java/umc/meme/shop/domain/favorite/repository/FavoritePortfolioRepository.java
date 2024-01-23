package umc.meme.shop.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;

import java.util.List;

public interface FavoritePortfolioRepository extends JpaRepository<FavoritePortfolio, Long> {
    List<FavoritePortfolio> findByModel(Model model);
    boolean existsByModelAndPortfolio(Model model, Portfolio portfolio);
}
