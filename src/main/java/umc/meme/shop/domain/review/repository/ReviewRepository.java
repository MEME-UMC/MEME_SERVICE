package umc.meme.shop.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
