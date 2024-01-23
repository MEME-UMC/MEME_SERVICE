package umc.meme.shop.domain.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    //    @Operation(summary = "모델 프로필 관리")
    @PatchMapping("/mypage/{userId}/profile/model")
    public ApiResponse profile (@PathVariable Long userId,
                                @RequestBody ModelProfileDto modelProfileDto){
        modelService.updateModel(userId, modelProfileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.TYPE_UPDATE);
    }

    /**favorite**/

    //    @Operation(summary = "관심 아티스트 조회")
    @GetMapping("/mypage/{userId}/favorite/artist")
    public ApiResponse favoriteArtist(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_GET, modelService.getFavoriteArtist(userId));
    }

    //    @Operation(summary = "관심 메이크업 조회")
    @GetMapping("/mypage/{userId}/favorite/portfolio")
    public ApiResponse favoritePortfolio(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_GET, modelService.getFavoritePortfolio(userId));
    }

    /**review**/

    //    @Operation(summary = "내가 쓴 리뷰 조회")
    @GetMapping("/mypage/{userId}/review")
    public ApiResponse getReview(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_GET, "");
    }

    //    @Operation(summary = "리뷰 작성")
    @PostMapping("/model/review")
    public ApiResponse postReview(@RequestBody ReviewDto reviewDto){
        //TODO: ReviewImg 추가
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_CREATE);
    }

    /**search**/

    //    @Operation(summary = "메이크업 검색")
    @GetMapping("/search")
    public ApiResponse search(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    //    @Operation(summary = "메이크업 검색 - 관심 아티스트")
    @GetMapping("/search/artist")
    public ApiResponse searchArtist(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    //    @Operation(summary = "메이크업 검색 - 최근 검색어")
    @GetMapping("/search/recent")
    public ApiResponse searchRecent(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }

    //    @Operation(summary = "메이크업 검색 - 카테고리")
    @GetMapping("/search/category")
    public ApiResponse searchCategory(){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, "");
    }
}
