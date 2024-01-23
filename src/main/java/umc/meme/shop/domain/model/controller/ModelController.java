package umc.meme.shop.domain.model.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.model.dto.request.ModelTypeDto;
import umc.meme.shop.domain.model.service.ModelService;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ModelController {
    private final ModelService modelService;

    @Operation(summary = "상세 정보 수정", description = "모델의 피부 type을 수정하는 API입니다.")
    @PatchMapping("/mypage/{userId}/type")
    public ApiResponse type(@PathVariable Long userId, @RequestBody ModelTypeDto modelTypeDto){
        return ApiResponse.SuccessResponse(SuccessStatus.TYPE_UPDATE);
    }

    /**favorite**/

    @Operation(summary = "관심 아티스트 조회", description = "관심 아티스트를 조회하는 API입니다.")
    @GetMapping("/mypage/{userId}/favorite/artist")
    public ApiResponse favoriteArtist(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_ARTIST_GET, "");
    }

    @Operation(summary = "관심 메이크업 조회", description = "관심 메이크업을 조회하는 API입니다.")
    @GetMapping("/mypage/{userId}/favorite/portfolio")
    public ApiResponse favoritePortfolio(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.FAVORITE_PORTFOLIO_GET, "");
    }

    /**review**/

    @Operation(summary = "내가 쓴 리뷰 조회", description = "본인이 쓴 리뷰를 조회하는 API입니다.")
    @GetMapping("/mypage/{userId}/review")
    public ApiResponse getReview(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_GET, "");
    }

    @Operation(summary = "리뷰 작성", description = "리뷰를 작성하는 API입니다.")
    @PostMapping("/model/review")
    public ApiResponse postReview(@RequestBody ReviewDto reviewDto){
        //TODO: ReviewImg 추가
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_CREATE);
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
