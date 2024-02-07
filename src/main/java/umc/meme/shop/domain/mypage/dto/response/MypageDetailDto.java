package umc.meme.shop.domain.mypage.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.global.enums.Gender;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MypageDetailDto {
    private String profileImg;
    private String nickname;
    private String name;
    private Gender gender;
    private String email;

    public static MypageDetailDto from(User user){
        return MypageDetailDto.builder()
                .profileImg(user.getProfileImg())
                .nickname(user.getNickname())
                .name(user.getUserName())
                .gender(user.getGender())
                .email(user.getEmail())
                .build();
    }
}
