package umc.meme.shop.domain.model.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.favorite.dto.request.FavoriteArtistDto;
import umc.meme.shop.domain.favorite.dto.request.FavoritePortfolioDto;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.service.ModelService;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ModelController {
    private final ModelService modelService;

    /**temp model create method**/
    @Operation(summary = "temp 모델 생성(프론트랑 상관X)")
    @PostMapping("/model")
    public ApiResponse createModel(@RequestBody ModelProfileDto profileDto){
        modelService.createModel(profileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

    @Operation(summary = "모델 프로필 관리")
    @PatchMapping("/mypage/profile/model")
    public ApiResponse updateModelProfile (@RequestBody ModelProfileDto modelProfileDto){
        modelService.updateModelProfile(modelProfileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.MODEL_PROFILE_UPDATE);
    }

    /**favorite**/

    @Operation(summary = "관심 아티스트 조회", description = "관심 아티스트를 조회하는 API입니다.")
    @GetMapping("/mypage/{modelId}/favorite/artist")
    public ApiResponse getFavoriteArtist(@PathVariable Long modelId,
                                         @RequestParam(value = "page", defaultValue = "0", required = false)int page){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_GET, modelService.getFavoriteArtist(modelId, page));
    }

    @Operation(summary = "관심 메이크업 조회", description = "관심 메이크업을 조회하는 API입니다.")
    @GetMapping("/mypage/{modelId}/favorite/portfolio")
    public ApiResponse getFavoritePortfolio(@PathVariable Long modelId,
                                            @RequestParam(value = "page", defaultValue = "0", required = false) int page
                                            ){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_GET, modelService.getFavoritePortfolio(modelId, page));
    }

    @Operation(summary = "관심 아티스트 추가", description = "관심 아티스트를 추가하는 API입니다.")
    @PostMapping("/mypage/favorite/artist")
    public ApiResponse postFavoriteArtist(@RequestBody FavoriteArtistDto favoriteArtistDto) {
        modelService.addFavoriteArtist(favoriteArtistDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_POST);
    }

    @Operation(summary = "관심 메이크업 추가", description = "관심 메이크업을 추가하는 API입니다.")
    @PostMapping("/mypage/favorite/portfolio")
    public ApiResponse postFavoritePortfolio(@RequestBody FavoritePortfolioDto favoritePortfolioDto) {
        modelService.addFavoritePortfolio(favoritePortfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_POST);
    }

    @Operation(summary = "관심 아티스트 삭제", description = "관심 아티스트를 삭제하는 API입니다.")
    @DeleteMapping("/mypage/favorite/artist")
    public ApiResponse deleteFavoriteArtist(@RequestBody FavoriteArtistDto favoriteArtistDto) {
        modelService.deleteFavoriteArtist(favoriteArtistDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_DELETE);
    }

    @Operation(summary = "관심 메이크업 삭제", description = "관심 메이크업을 삭제하는 API입니다.")
    @DeleteMapping("/mypage/favorite/portfolio")
    public ApiResponse deleteFavoritePortfolio(@RequestBody FavoritePortfolioDto favoritePortfolioDto) {
        modelService.deleteFavoritePortfolio(favoritePortfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_DELETE);
    }


    /**search**/

    @Operation(summary = "메이크업 검색", description = "메이크업을 검색/최근 검색어로 검색하는 API입니다.")
    @GetMapping("/search")
    public ApiResponse search(@RequestParam(value = "query") String query,
                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(value = "sort", defaultValue = "desc") String sort){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, modelService.search(query, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 관심 아티스트", description = "관심 아티스트로 검색하는 API입니다.")
    @GetMapping("/search/artist")
    public ApiResponse searchArtist(@RequestParam(value = "artistId") Long artistId,
                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(value = "sort", defaultValue = "desc") String sort
                                    ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, modelService.searchArtist(artistId, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 카테고리", description = "메이크업 카테고리로 검색하는 API입니다.")
    @GetMapping("/search/category")
    public ApiResponse searchCategory(@RequestParam(value = "category") Category category,
                                      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                      @RequestParam(value = "sort", defaultValue = "desc") String sort
                                      ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, modelService.searchCategory(category, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 전체", description = "메이크업 전체를 검색하는 API입니다.")
    @GetMapping("/search/all")
    public ApiResponse searchAll( @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                      @RequestParam(value = "sort", defaultValue = "desc") String sort
                                ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, modelService.searchAll(page, sort));
    }

    /**recommend**/
    @Operation(summary = "포트폴리오 추천 - 리뷰 순", description = "리뷰가 많은 순으로 포트폴리오를 추천하는 API입니다.")
    @GetMapping("/recommend/review")
    public ApiResponse recommendReview(){
        return ApiResponse.SuccessResponse(SuccessStatus.RECOMMEND_REVIEW_GET, modelService.recommendReview());
    }

    @Operation(summary = "포트폴리오 추천 - 최신 순", description = "최근 등록된 순으로 포트폴리오를 추천하는 API입니다.")
    @GetMapping("/recommend/recent")
    public ApiResponse recommendRecent(){
        return ApiResponse.SuccessResponse(SuccessStatus.RECOMMEND_RECENT_GET, modelService.recommendRecent());
    }
}
