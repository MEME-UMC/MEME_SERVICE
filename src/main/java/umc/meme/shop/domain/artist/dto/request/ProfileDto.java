package umc.meme.shop.domain.artist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.AvailableTime;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.artist.entity.enums.MakeupLocation;
import umc.meme.shop.domain.artist.entity.enums.Region;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String profileImg;
    private String nickname;
    private Gender gender;
    private String introduction;
    private List<Region> region;
    private List<Category> specialization;
    private MakeupLocation makeupLocation;
    private AvailableTime availableTime;
}
