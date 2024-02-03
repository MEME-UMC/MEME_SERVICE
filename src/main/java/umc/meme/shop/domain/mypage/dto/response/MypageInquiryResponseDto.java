package umc.meme.shop.domain.mypage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.mypage.entity.Inquiry;
import umc.meme.shop.domain.user.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MypageInquiryResponseDto {
    private String userEmail;
    private String inquiryTitle;
    private String inquiryText;

    public static MypageInquiryResponseDto from(Inquiry inquiry) {

        return MypageInquiryResponseDto.builder()
                .inquiryText(inquiry.getInquiryText())
                .inquiryTitle(inquiry.getInquiryTitle())
                .userEmail(inquiry.getUser().getEmail())
                .build();
    }
}
