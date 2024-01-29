package umc.meme.shop.domain.portfolio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    @Query("SELECT r FROM Portfolio r WHERE r.artist = :artist")
    List<Portfolio> findByArtist(@Param("artist") Artist artist);

    @Query("SELECT p FROM Portfolio p WHERE p.category = :category")
    Page<Portfolio> findByCategory(@Param("category") Category category, Pageable pageable);

    boolean existsByMakeupName(String makeupName);
}
