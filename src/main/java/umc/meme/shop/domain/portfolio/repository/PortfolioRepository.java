package umc.meme.shop.domain.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
