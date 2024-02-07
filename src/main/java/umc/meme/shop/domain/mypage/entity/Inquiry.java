package umc.meme.shop.domain.mypage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.domain.mypage.dto.request.MypageInquiryDto;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.user.UserRepository;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    @Column(nullable = false)
    private String inquiryTitle;

    @Column(nullable = false)
    private String inquiryText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Inquiry from(MypageInquiryDto dto, User user) {
        return Inquiry.builder()
                .inquiryText(dto.getInquiryText())
                .inquiryTitle(dto.getInquiryTitle())
                .user(user)
                .build();
    }

}
