package umc.meme.shop.domain.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.artist.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
