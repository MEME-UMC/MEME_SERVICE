package umc.meme.shop.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.model.entity.Model;

import java.util.List;

public interface FavoriteArtistRepository extends JpaRepository<FavoriteArtist, Long> {
    List<FavoriteArtist> findByModel(Model model);
}
