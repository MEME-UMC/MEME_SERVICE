package umc.meme.shop.domain.favorite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteArtistResponseDto {
    private Long artistId;
    private String profileImg;
    private String artistNickName;

    public static FavoriteArtistResponseDto from(FavoriteArtist favoriteArtist) {
        Artist artist = favoriteArtist.getArtist();
        return FavoriteArtistResponseDto.builder()
                .artistId(artist.getUserId())
                .profileImg(artist.getProfileImg())
                .artistNickName(artist.getNickname())
                .build();
    }
}
