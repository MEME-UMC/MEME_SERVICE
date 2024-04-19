package umc.meme.shop.domain.artist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleArtistDto {
    private Long artistId;
    private String profileImg;
    private String artistNickName;
    private String email;
    private String region;
    private Long modelCount; //해당 아티스트를 관심 아티스트로 설정한 모델 수


    public static SimpleArtistDto from(Artist artist, Long modelCount) {
        return SimpleArtistDto.builder()
                .artistId(artist.getUserId())
                .profileImg(artist.getProfileImg())
                .artistNickName(artist.getNickname())
                .email(artist.getEmail())
                .region(artist.getRegion().get(0).getValue())
                .modelCount(modelCount)
                .build();
    }
}
