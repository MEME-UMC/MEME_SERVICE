package umc.meme.shop.domain.mypage.dto.response;


import lombok.Data;
import umc.meme.shop.domain.artist.entity.enums.Gender;

@Data
public class MypageDetailDto {
    private String profileImg;
    private String nickname;
    private String name;
    private Gender gender;
    private String email;
}
