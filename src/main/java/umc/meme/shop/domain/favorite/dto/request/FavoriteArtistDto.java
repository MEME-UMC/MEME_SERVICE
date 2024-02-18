package umc.meme.shop.domain.favorite.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FavoriteArtistDto {
    @NotBlank(message = "modelId를 입력해주세요.")
    private Long modelId;
    @NotBlank(message = "artistId를 입력해주세요.")
    private Long artistId;
}
