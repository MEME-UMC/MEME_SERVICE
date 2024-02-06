package umc.meme.shop.domain.artist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.dto.response.SimplePortfolioDto;
import umc.meme.shop.global.enums.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private Long artistId;

    private Gender gender;

    private String nickname;

    private String profileImg;

    private String introduction;

    private WorkExperience workExperience;

    private List<String> region;

    private List<String> specialization;

    private MakeupLocation makeupLocation;

    private Map<DayOfWeek, Times> availableDayOfWeekAndTime;

    private List<SimplePortfolioDto> simplePortfolioDtoList;



    public static ArtistDto from(Artist artist){
        List<SimplePortfolioDto> portfolioDtoList = artist.getPortfolioList()
                .stream()
                .map(SimplePortfolioDto::from)
                .toList();

        return ArtistDto.builder()
                .artistId(artist.getUserId())
                .gender(artist.getGender())
                .nickname(artist.getNickname())
                .profileImg(artist.getProfileImg())
                .introduction(artist.getIntroduction())
                .workExperience(artist.getWorkExperience())
                .region(artist.getRegion())
                .specialization(artist.getSpecialization())
                .makeupLocation(artist.getMakeupLocation())
                .availableDayOfWeekAndTime(artist.getAvailableDayOfWeekAndTime())
                .simplePortfolioDtoList(portfolioDtoList)
                .build();
    }

}


