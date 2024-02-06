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

    public static SimpleArtistDto from(Artist artist) {
        return SimpleArtistDto.builder()
                .artistId(artist.getUserId())
                .profileImg(artist.getProfileImg())
                .artistNickName(artist.getNickname())
                .build();
    }
}
