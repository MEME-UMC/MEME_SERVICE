package umc.meme.shop.domain.model.dto.request;

import lombok.Data;
import umc.meme.shop.global.enums.Gender;
import umc.meme.shop.global.enums.PersonalColor;
import umc.meme.shop.global.enums.SkinType;

@Data
public class ModelProfileDto {
    private Long modelId;
    private String profileImg;
    private String nickname;
    private Gender gender;
    private SkinType skinType;
    private PersonalColor personalColor;
}
