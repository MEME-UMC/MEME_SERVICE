package umc.meme.shop.domain.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;

public interface PortfolioImgRepository extends JpaRepository<PortfolioImg, Long> {
}
