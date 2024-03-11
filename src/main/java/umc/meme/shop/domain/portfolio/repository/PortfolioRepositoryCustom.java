package umc.meme.shop.domain.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.Category;

public interface PortfolioRepositoryCustom {
    Page<Portfolio> findByArtist(Artist artist, Pageable pageable);
    Page<Portfolio> findByCategory(Category category, Pageable pageable);
    Page<Portfolio> search(String query, Pageable pageable);
    Page<Portfolio> findAllNotBlocked(Pageable pageable);
}
