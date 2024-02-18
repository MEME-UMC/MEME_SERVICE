package umc.meme.shop.domain.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.mypage.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
