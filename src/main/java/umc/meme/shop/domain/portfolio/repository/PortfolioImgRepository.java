package umc.meme.shop.domain.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;

import java.util.Optional;

public interface PortfolioImgRepository extends JpaRepository<PortfolioImg, Long> {
    Optional<PortfolioImg> findBySrcAndPortfolio(String src, Portfolio portfolio);
}
