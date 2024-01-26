package umc.meme.shop.domain.artist.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;

import java.util.List;
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

    private List<PortfolioDto> portfolioDtoList;

    public static ArtistDto from(Artist artist){
        List<PortfolioDto> portfolioDtoList = artist.getPortfolioList()
                .stream()
                .map(PortfolioDto::from)
                .toList();

        return ArtistDto.builder()
                .artistId(artist.getArtistId())
                .gender(artist.getGender())
                .nickname(artist.getNickname())
                .profileImg(artist.getProfileImg())
                .introduction(artist.getIntroduction())
                .workExperience(artist.getWorkExperience())
                .region(artist.getRegion())
                .specialization(artist.getSpecialization())
                .makeupLocation(artist.getMakeupLocation())
                .portfolioDtoList(portfolioDtoList)
                .build();
    }

    //관심 아티스트
    public static ArtistDto from(FavoriteArtist favoriteArtist){
        Artist artist = favoriteArtist.getArtist();
        List<PortfolioDto> portfolioDtoList = artist.getPortfolioList()
                .stream()
                .map(PortfolioDto::from)
                .toList();

        return ArtistDto.builder()
                .artistId(artist.getArtistId())
                .gender(artist.getGender())
                .nickname(artist.getNickname())
                .profileImg(artist.getProfileImg())
                .introduction(artist.getIntroduction())
                .workExperience(artist.getWorkExperience())
                .region(artist.getRegion())
                .specialization(artist.getSpecialization())
                .makeupLocation(artist.getMakeupLocation())
                .portfolioDtoList(portfolioDtoList)
                .build();
    }

}


