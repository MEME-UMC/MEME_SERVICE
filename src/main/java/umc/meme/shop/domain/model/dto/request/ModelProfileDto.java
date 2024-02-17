package umc.meme.shop.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.global.enums.Gender;
import umc.meme.shop.global.enums.PersonalColor;
import umc.meme.shop.global.enums.SkinType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelProfileDto {
    private Long userId;
    private String profileImg;
    private String nickname;
    private Gender gender;
    private SkinType skinType;
    private PersonalColor personalColor;

    public static ModelProfileDto from(Model model){
        return ModelProfileDto.builder()
                .userId(model.getUserId())
                .profileImg(model.getProfileImg())
                .nickname(model.getNickname())
                .gender(model.getGender())
                .skinType(model.getSkinType())
                .personalColor(model.getPersonalColor())
                .build();
    }
}
