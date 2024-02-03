package umc.meme.shop.domain.artist.converter;

import org.springframework.data.domain.Page;
import umc.meme.shop.domain.favorite.dto.response.FavoriteArtistPageResponseDto;
import umc.meme.shop.domain.favorite.dto.response.FavoriteArtistResponseDto;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;

import java.util.List;

public class ArtistConverter {
    public static FavoriteArtistPageResponseDto favoriteArtistPageConverter(Page<FavoriteArtist> page) {
        List<FavoriteArtistResponseDto> content = page.stream()
                .map(FavoriteArtistResponseDto::from)
                .toList();

        return FavoriteArtistPageResponseDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
