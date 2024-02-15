package umc.meme.shop.domain.artist.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistProfileDetailDto{
    @NotNull
    private Long userId;
    @NotNull
    private Long artistId;
}
