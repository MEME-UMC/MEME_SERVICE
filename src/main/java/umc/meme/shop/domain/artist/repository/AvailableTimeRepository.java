package umc.meme.shop.domain.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.artist.entity.AvailableTime;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
}
