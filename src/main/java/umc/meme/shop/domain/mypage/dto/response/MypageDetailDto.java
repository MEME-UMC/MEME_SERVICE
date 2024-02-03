package umc.meme.shop.domain.mypage.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.Gender;

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
}
