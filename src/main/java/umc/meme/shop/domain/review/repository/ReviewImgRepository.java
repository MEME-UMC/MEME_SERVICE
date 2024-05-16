package umc.meme.shop.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.review.entity.Review;
import umc.meme.shop.domain.review.entity.ReviewImg;

import java.util.Optional;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
    Optional<ReviewImg> findBySrcAndReview(String src, Review review);
}
