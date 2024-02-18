package umc.meme.shop.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioPageDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PortfolioRepository portfolioRepository;
    private final ArtistRepository artistRepository;

    //검색
    public PortfolioPageDto search(String query, int page, String sortBy){
        Pageable pageable = setPageRequest(page, sortBy);

        //query 검색
        Page<Portfolio> portfolioPage = portfolioRepository.search(query, pageable);
        return PortfolioPageDto.from(portfolioPage);
    }

    //카테고리 검색
    public PortfolioPageDto searchCategory(Category category, int page, String sortBy){
        Pageable pageable = setPageRequest(page, sortBy);
        Page<Portfolio> portfolioPage = portfolioRepository.findByCategory(category, pageable);
        return PortfolioPageDto.from(portfolioPage);
    }

    //관심 아티스트 검색
    public PortfolioPageDto searchArtist(Long artistId, int page, String sortBy){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        Pageable pageable = setPageRequest(page, sortBy);
        Page<Portfolio> portfolioPage = portfolioRepository.findByArtist(artist, pageable);
        return PortfolioPageDto.from(portfolioPage);
    }

    //전체 조회
    public PortfolioPageDto searchAll(int page, String sortBy) {
        Pageable pageable = setPageRequest(page, sortBy);
        Page<Portfolio> portfolioPage = portfolioRepository.findAllNotBlocked(pageable);
        return PortfolioPageDto.from(portfolioPage);
    }

    //검색하기 정렬 기준 설정
    private Pageable setPageRequest(int page, String sortBy){

        Sort sort = switch (sortBy) {
            case "desc" -> Sort.by("price").descending();
            case "asc" -> Sort.by("price").ascending();
            case "review" -> Sort.by("averageStars").descending();
            case "recent" -> Sort.by("createdAt").descending();
            default -> throw new GlobalException(ErrorStatus.INVALID_SORT_CRITERIA);
        };

        //별점 높은 순 정렬 추가
        Sort finalSort = sort.and(Sort.by("averageStars").descending());
        return PageRequest.of(page, 30, finalSort);
    }
}
