package umc.meme.shop.domain.artist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.portfolio.entity.enums.Category;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistProfileDto {
    private String profileImg;
    private String nickname;
    private Gender gender;
    private String introduction;
    private WorkExperience workExperience;
    private List<Region> region;
    private List<Category> specialization;
    private MakeupLocation makeupLocation;
    private String shopLocation;
    private Map<DayOfWeek, Times> availableDayOfWeek;
}
