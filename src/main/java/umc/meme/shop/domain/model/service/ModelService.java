package umc.meme.shop.domain.model.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.response.SimpleArtistDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.favorite.dto.request.FavoriteArtistDto;
import umc.meme.shop.domain.favorite.dto.request.FavoritePortfolioDto;
import umc.meme.shop.domain.favorite.dto.response.FavoriteArtistPageResponseDto;
import umc.meme.shop.domain.favorite.dto.response.FavoritePortfolioResponsePageDto;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.favorite.repository.FavoriteArtistRepository;
import umc.meme.shop.domain.favorite.repository.FavoritePortfolioRepository;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioPageDto;
import umc.meme.shop.domain.portfolio.dto.response.SimplePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final ArtistRepository artistRepository;
    private final FavoriteArtistRepository favoriteArtistRepository;
    private final FavoritePortfolioRepository favoritePortfolioRepository;
    private final PortfolioRepository portfolioRepository;

    /**temp model create method**/
    @Transactional
    public void createModel(ModelProfileDto dto){
        Model model = Model.from(dto);
        model.tempMethod();
        modelRepository.save(model);
    }

    //모델 프로필 관리
    @Transactional
    public void updateModelProfile(ModelProfileDto request){
        Model model = modelRepository.findById(request.getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        model.updateModel(request);
    }


    //관심 아티스트 조회
    @Transactional
    public FavoriteArtistPageResponseDto getFavoriteArtist(Long modelId, int page){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        //paging
        List<FavoriteArtist> favoriteArtistList = model.getFavoriteArtistList();
        Page<FavoriteArtist> favoriteArtistPage = getPage(page, favoriteArtistList);

        //관심 아티스트 리스트
        List<SimpleArtistDto> content = favoriteArtistPage.getContent().stream()
                .map(favoriteArtist -> artistRepository.findById(favoriteArtist.getArtistId())
                        .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST)))
                .map(SimpleArtistDto::from)
                .collect(Collectors.toList());

        return FavoriteArtistPageResponseDto.from(favoriteArtistPage, content);
    }

    //관심 메이크업 조회
    @Transactional
    public FavoritePortfolioResponsePageDto getFavoritePortfolio(Long modelId, int page){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        //list를 page로 변환
        List<FavoritePortfolio> favoritePortfolioList = model.getFavoritePortfolioList();
        Page<FavoritePortfolio> favoritePortfolioPage = getPage(page, favoritePortfolioList);

        return FavoritePortfolioResponsePageDto.from(favoritePortfolioPage);
    }

    //관심 아티스트 추가
    @Transactional
    public void addFavoriteArtist(FavoriteArtistDto favoriteArtistDto) {
        Model model = modelRepository.findById(favoriteArtistDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Artist artist = artistRepository.findById(favoriteArtistDto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //이미 관심 아티스트가 존재하는 경우
        if (favoriteArtistRepository.existsByModelAndArtistId(model, artist.getUserId())) {
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_FAVORITE_ARTIST);
        }

        FavoriteArtist favoriteArtist = FavoriteArtist.from(artist, model);
        model.updateFavoriteArtistList(favoriteArtist);
        favoriteArtistRepository.save(favoriteArtist);
    }

    //관심 메이크업 추가
    @Transactional
    public void addFavoritePortfolio(FavoritePortfolioDto favoritePortfolioDto) {
        Model model = modelRepository.findById(favoritePortfolioDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(favoritePortfolioDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        //이미 관심 포트폴리오가 존재하는 경우
        if (favoritePortfolioRepository.existsByModelAndPortfolio(model,portfolio)) {
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_FAVORITE_PORTFOLIO);
        }

        FavoritePortfolio favoritePortfolio = FavoritePortfolio.from(model, portfolio);
        model.updateFavoritePortfolioList(favoritePortfolio);
        favoritePortfolioRepository.save(favoritePortfolio);
    }

    //관심 아티스트 삭제
    @Transactional
    public void deleteFavoriteArtist(FavoriteArtistDto favoriteArtistDto){
        Model model = modelRepository.findById(favoriteArtistDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Artist artist = artistRepository.findById(favoriteArtistDto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        FavoriteArtist favoriteArtist = favoriteArtistRepository.findByModelAndArtistId(model, artist.getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_FAVORITE_ARTIST));
        favoriteArtistRepository.delete(favoriteArtist);
    }

    //관심 메이크업 삭제
    @Transactional
    public void deleteFavoritePortfolio(FavoritePortfolioDto favoritePortfolioDto) {
        Model model = modelRepository.findById(favoritePortfolioDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(favoritePortfolioDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        FavoritePortfolio favoritePortfolio = favoritePortfolioRepository.findByModelAndPortfolio(model, portfolio)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_FAVORITE_PORTFOLIO));
        favoritePortfolioRepository.delete(favoritePortfolio);
    }


    /**search**/
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
    public PortfolioPageDto searchAll(int page, String sortBy){
        Pageable pageable = setPageRequest(page, sortBy);
        Page<Portfolio> portfolioPage = portfolioRepository.findAllNotBlocked(pageable);
        return PortfolioPageDto.from(portfolioPage);
    }

    /**recommend**/
    //리뷰 많은 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendReview(){
        Pageable pageable = setPageRequest(0, "review");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    //최신 등록 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendRecent(){
        Pageable pageable = setPageRequest(0, "recent");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    //검색하기 정렬 기준 설정
    private Pageable setPageRequest(int page, String sortBy){

        Sort sort;
        if(sortBy.equals("desc"))
            sort = Sort.by("price").descending();
        else if(sortBy.equals("asc"))
            sort = Sort.by("price").ascending();
        else if(sortBy.equals("review"))
            sort = Sort.by("averageStars").descending();
        else if(sortBy.equals("recent"))
            sort = Sort.by("createdAt").descending();
        else
            throw new GlobalException(ErrorStatus.INVALID_SORT_CRITERIA);

        //별점 높은 순 정렬 추가
        Sort finalSort = sort.and(Sort.by("averageStars").descending());
        return PageRequest.of(page, 30, finalSort);
    }

    //TODO: change List -> Page
    private Page getPage(int page, List list){
        Pageable pageable = PageRequest.of(page, 30);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        //list를 page로 변환
        return new PageImpl<>(list.subList(start, end),
                pageable, list.size());
    }

}
