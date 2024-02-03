package umc.meme.shop.domain.artist.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private List<PortfolioDto> portfolioDtoList;



    public static ArtistDto from(Artist artist){
        List<PortfolioDto> portfolioDtoList = artist.getPortfolioList()
                .stream()
                .map(PortfolioDto::from)
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
                .portfolioDtoList(portfolioDtoList)
                .build();
    }

}


