package umc.meme.shop.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByModel(Model model);
}
