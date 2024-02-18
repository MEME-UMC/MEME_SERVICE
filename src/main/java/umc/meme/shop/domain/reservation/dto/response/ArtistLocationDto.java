package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.global.enums.MakeupLocation;
import umc.meme.shop.global.enums.Region;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistLocationDto {
    private MakeupLocation makeupLocation; //샵 재직 여부
    private String shopLocation; //샵 위치
    private List<Region> region; //활동 가능 지역

    public static ArtistLocationDto from(Artist artist){
        return ArtistLocationDto.builder()
                .makeupLocation(artist.getMakeupLocation())
                .shopLocation(artist.getShopLocation())
                .region(artist.getRegion())
                .build();
    }
}
