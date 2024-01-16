package umc.meme.shop.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.review.entity.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
}
