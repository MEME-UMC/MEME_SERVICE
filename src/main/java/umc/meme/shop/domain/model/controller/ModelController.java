package umc.meme.shop.domain.model.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.favorite.dto.request.FavoriteArtistDto;
import umc.meme.shop.domain.favorite.dto.request.FavoritePortfolioDto;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.service.ModelService;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ModelController {
    private final ModelService modelService;

    @Operation(summary = "모델 프로필 관리")
    @PatchMapping("/mypage/{modelId}/profile/model")
    public ApiResponse updateModelProfile (@PathVariable Long modelId,
                                @RequestBody ModelProfileDto modelProfileDto){
        modelService.updateModelProfile(modelId, modelProfileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.MODEL_PROFILE_UPDATE);
    }

    /**favorite**/

    @Operation(summary = "관심 아티스트 조회", description = "관심 아티스트를 조회하는 API입니다.")
    @GetMapping("/mypage/{modelId}/favorite/artist")
    public ApiResponse getFavoriteArtist(@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_GET, modelService.getFavoriteArtist(modelId));
    }

    @Operation(summary = "관심 메이크업 조회", description = "관심 메이크업을 조회하는 API입니다.")
    @GetMapping("/mypage/{modelId}/favorite/portfolio")
    public ApiResponse getFavoritePortfolio(@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_GET, modelService.getFavoritePortfolio(modelId));
    }

    @Operation(summary = "관심 아티스트 추가", description = "관심 아티스트를 추가하는 API입니다.")
    @PostMapping("/mypage/{modelId}/favorite/artist")
    public ApiResponse postFavoriteArtist(@PathVariable Long modelId, @RequestBody FavoriteArtistDto favoriteArtistDto) {
        modelService.addFavoriteArtist(modelId, favoriteArtistDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_POST);
    }

    @Operation(summary = "관심 메이크업 추가", description = "관심 메이크업을 추가하는 API입니다.")
    @PostMapping("/mypage/{modelId}/favorite/portfolio")
    public ApiResponse postFavoritePortfolio(@PathVariable Long modelId, @RequestBody FavoritePortfolioDto favoritePortfolioDto) {
        modelService.addFavoritePortfolio(modelId, favoritePortfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_POST);
    }

    @Operation(summary = "관심 아티스트 삭제", description = "관심 아티스트를 삭제하는 API입니다.")
    @DeleteMapping("/mypage/{modelId}/favorite/artist")
    public ApiResponse deleteFavoriteArtist(@PathVariable Long modelId, @RequestBody FavoriteArtistDto favoriteArtistDto) {
        modelService.deleteFavoriteArtist(modelId, favoriteArtistDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_DELETE);
    }

    @Operation(summary = "관심 메이크업 삭제", description = "관심 메이크업을 삭제하는 API입니다.")
    @DeleteMapping("/mypage/{modelId}/favorite/portfolio")
    public ApiResponse deleteFavoritePortfolio(@PathVariable Long modelId, @RequestBody FavoritePortfolioDto favoritePortfolioDto) {
        modelService.deleteFavoritePortfolio(modelId, favoritePortfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_DELETE);
    }


    /**search**/

    @Operation(summary = "메이크업 검색", description = "메이크업을 검색하는 API입니다.")
    @GetMapping("/search")
    public ApiResponse search(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    @Operation(summary = "메이크업 검색 - 관심 아티스트", description = "관심 아티스트로 검색하는 API입니다.")
    @GetMapping("/search/artist")
    public ApiResponse searchArtist(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    @Operation(summary = "메이크업 검색 - 최근 검색어", description = "최근 검색어 안에서 검색하는 API입니다.")
    @GetMapping("/search/recent")
    public ApiResponse searchRecent(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    @Operation(summary = "메이크업 검색 - 카테고리", description = "메이크업 카테고리로 검색하는 API입니다.")
    @GetMapping("/search/category")
    public ApiResponse searchCategory(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }
}
