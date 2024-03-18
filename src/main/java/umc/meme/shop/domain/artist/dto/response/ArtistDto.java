package umc.meme.shop.domain.artist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.dto.request.AvailableTimeRequestDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.dto.response.SimplePortfolioDto;
import umc.meme.shop.global.enums.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private Long artistId; //관심 등록 여부

    private Boolean isFavorite;

    private Gender gender;

    private String nickname;

    private String profileImg;

    private String introduction;

    private WorkExperience workExperience;

    private String shopLocation; //샵 위치

    private List<Region> region;

    private List<Category> specialization;

    private MakeupLocation makeupLocation;

    private List<AvailableTimeRequestDto> availableTimeList;

    private List<SimplePortfolioDto> simplePortfolioDtoList;



    public static ArtistDto from(Artist artist, boolean isFavorite){
        List<AvailableTimeRequestDto> availableTimeDtoList = artist.getAvailableTimeList()
                .stream().map(AvailableTimeRequestDto::from)
                .toList();

        List<SimplePortfolioDto> portfolioDtoList = artist.getPortfolioList()
                .stream()
                .map(SimplePortfolioDto::from)
                .toList();

        return ArtistDto.builder()
                .artistId(artist.getUserId())
                .isFavorite(isFavorite)
                .gender(artist.getGender())
                .nickname(artist.getNickname())
                .profileImg(artist.getProfileImg())
                .introduction(artist.getIntroduction())
                .workExperience(artist.getWorkExperience())
                .region(artist.getRegion())
                .specialization(artist.getSpecialization())
                .makeupLocation(artist.getMakeupLocation())
                .availableTimeList(availableTimeDtoList)
                .simplePortfolioDtoList(portfolioDtoList)
                .build();
    }

}


