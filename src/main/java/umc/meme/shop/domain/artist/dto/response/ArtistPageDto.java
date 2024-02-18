package umc.meme.shop.domain.artist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistPageDto {
    private List<ArtistDto> content;
    private int currentPage; //현재 페이지 번호
    private int pageSize; //페이지 크기
    private int totalNumber; //전체 메이크업 개수
    private int totalPage; //전체 페이지 개수
}
