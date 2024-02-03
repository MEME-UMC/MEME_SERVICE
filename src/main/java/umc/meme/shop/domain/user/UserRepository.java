package umc.meme.shop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.artist.entity.Artist;

public interface UserRepository extends JpaRepository<User, Long> {
}
