package umc.meme.shop.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.model.entity.Model;

import java.util.List;
import java.util.Optional;

public interface FavoriteArtistRepository extends JpaRepository<FavoriteArtist, Long> {
    List<FavoriteArtist> findByModel(Model model);
    boolean existsByModelAndArtistId(Model model, Long userId);
    Optional<FavoriteArtist> findByModelAndArtistId(Model model, Long userId);
    Long countByArtistId(Long artistId);
}
