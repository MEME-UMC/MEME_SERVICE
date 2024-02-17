package umc.meme.shop.domain.artist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.global.enums.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistProfileDto {
    private Long userId;
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

    public static ArtistProfileDto from(Artist artist){
        return ArtistProfileDto.builder()
                .userId(artist.getUserId())
                .profileImg(artist.getProfileImg())
                .nickname(artist.getNickname())
                .gender(artist.getGender())
                .introduction(artist.getIntroduction())
                .workExperience(artist.getWorkExperience())
                .region(artist.getRegion())
                .specialization(artist.getSpecialization())
                .makeupLocation(artist.getMakeupLocation())
                .shopLocation(artist.getShopLocation())
                .availableDayOfWeek(artist.getAvailableDayOfWeekAndTime())
                .build();
    }
}
