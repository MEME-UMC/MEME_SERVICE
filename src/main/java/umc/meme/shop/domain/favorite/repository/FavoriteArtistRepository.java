package umc.meme.shop.domain.favorite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;

public interface FavoriteArtistRepository extends JpaRepository<FavoriteArtist, Long> {
}
