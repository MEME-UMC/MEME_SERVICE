package umc.meme.shop.domain.mypage.dto.request;


import lombok.*;
import umc.meme.shop.domain.mypage.entity.Inquiry;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MypageInquiryDto {
    private Long inquiryId;
    private Long userId;
    private String inquiryTitle;
    private String inquiryText;

    public static MypageInquiryDto from(Inquiry inquiry) {
        return MypageInquiryDto.builder()
                .inquiryId(inquiry.getInquiryId())
                .inquiryTitle(inquiry.getInquiryTitle())
                .inquiryText(inquiry.getInquiryText())
                .build();
    }
}
