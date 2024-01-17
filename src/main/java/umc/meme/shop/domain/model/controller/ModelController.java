package umc.meme.shop.domain.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.model.service.ModelService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ModelController {
    private final ModelService modelService;

    //    @Operation(summary = "상세 정보 수정")
    @PatchMapping("/mypage/{userId}/type")
    public ApiResponse type(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

    /**favorite**/

    //    @Operation(summary = "관심 아티스트 조회")
    @GetMapping("/mypage/{userId}/favorite/artist")
    public ApiResponse favoriteArtist(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    //    @Operation(summary = "관심 메이크업 조회")
    @GetMapping("/mypage/{userId}/favorite/portfolio")
    public ApiResponse favoritePortfolio(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    /**review**/

    //    @Operation(summary = "내가 쓴 리뷰 조회")
    @GetMapping("/mypage/{userId}/review")
    public ApiResponse getReview(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    //    @Operation(summary = "리뷰 작성")
    @PostMapping("/model/review")
    public ApiResponse postReview(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

    /**search**/

    //    @Operation(summary = "메이크업 검색")
    @GetMapping("/search")
    public ApiResponse search(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    //    @Operation(summary = "메이크업 검색 - 관심 아티스트")
    @GetMapping("/search/artist")
    public ApiResponse searchArtist(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    //    @Operation(summary = "메이크업 검색 - 최근 검색어")
    @GetMapping("/search/recent")
    public ApiResponse searchRecent(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }

    //    @Operation(summary = "메이크업 검색 - 카테고리")
    @GetMapping("/search/category")
    public ApiResponse searchCategory(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP, "");
    }
}
