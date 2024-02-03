package umc.meme.shop.domain.mypage.dto.request;


import lombok.*;
import umc.meme.shop.domain.mypage.entity.Inquiry;
import umc.meme.shop.domain.user.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MypageInquiryDto {
    private Long userId;
    private String inquiryTitle;
    private String inquiryText;

    public static MypageInquiryDto from(Inquiry inquiry) {

        User user = inquiry.getUser();

        return MypageInquiryDto.builder()
                .userId(user.getUserId())
                .inquiryTitle(inquiry.getInquiryTitle())
                .inquiryText(inquiry.getInquiryText())
                .build();
    }
}
