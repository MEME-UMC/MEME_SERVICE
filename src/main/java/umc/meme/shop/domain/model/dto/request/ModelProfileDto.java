package umc.meme.shop.domain.model.dto.request;

import lombok.Data;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.model.entity.enums.PersonalColor;
import umc.meme.shop.domain.model.entity.enums.SkinType;

@Data
public class ModelProfileDto {
    private String profileImg;
    private String nickname;
    private Gender gender;
    private SkinType skinType;
    private PersonalColor personalColor;
}
