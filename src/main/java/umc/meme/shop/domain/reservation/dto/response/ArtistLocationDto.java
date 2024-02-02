package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.MakeupLocation;
import umc.meme.shop.domain.artist.entity.enums.Region;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistLocationDto {
    private MakeupLocation makeupLocation; //샵 재직 여부
    private String shopLocation; //샵 위치
    private List<String> region; //활동 가능 지역
}
