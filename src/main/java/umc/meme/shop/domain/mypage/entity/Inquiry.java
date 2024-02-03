package umc.meme.shop.domain.mypage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.mypage.dto.request.MypageInquiryDto;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    @Column(nullable = false)
    private String inquiryTitle;

    @Column(nullable = false)
    private String inquiryText;


    public void updateInquiry(MypageInquiryDto request) {
        if (request.getInquiryTitle() != null) {
            this.inquiryTitle = request.getInquiryTitle();
        }
        if (request.getInquiryText() != null) {
            this.inquiryText = request.getInquiryText();
        }
    }
}
