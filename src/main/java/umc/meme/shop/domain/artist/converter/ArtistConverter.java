package umc.meme.shop.domain.artist.converter;

import org.springframework.data.domain.Page;
import umc.meme.shop.domain.artist.dto.response.ArtistDto;
import umc.meme.shop.domain.artist.dto.response.ArtistPageDto;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;

import java.util.List;

public class ArtistConverter {
    public static ArtistPageDto favoriteArtistPageConverter(Page<FavoriteArtist> page) {

        List<ArtistDto> content = page.stream()
                .map(ArtistDto::from)
                .toList();

        return ArtistPageDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
