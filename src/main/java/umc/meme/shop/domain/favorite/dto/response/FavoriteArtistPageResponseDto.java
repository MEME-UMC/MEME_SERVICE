package umc.meme.shop.domain.favorite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import umc.meme.shop.domain.artist.dto.response.SimpleArtistDto;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteArtistPageResponseDto {
    private List<SimpleArtistDto> content;
    private int currentPage; //현재 페이지 번호
    private int pageSize; //페이지 크기
    private int totalNumber; //전체 메이크업 개수
    private int totalPage; //전체 페이지 개수

    public static FavoriteArtistPageResponseDto from(Page<FavoriteArtist> page, List<SimpleArtistDto> content){
        return FavoriteArtistPageResponseDto.builder()
                .content(content)
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalNumber(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
