package umc.meme.shop.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.request.UpdateReviewDto;
import umc.meme.shop.domain.review.service.ReviewService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "리뷰를 작성하는 API입니다.")
    @PostMapping("/{modelId}")
    public ApiResponse createReview(@PathVariable Long modelId, @RequestBody ReviewDto reviewDto){
        //TODO: ReviewImg 추가
        reviewService.createReview(modelId, reviewDto);
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_CREATE);
    }

    @Operation(summary = "내가 쓴 리뷰 조회", description = "본인이 쓴 리뷰를 조회하는 API입니다.")
    @GetMapping("/{modelId}")
    public ApiResponse getReview(@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_GET, reviewService.getMyReview(modelId));
    }

    @Operation(summary = "리뷰 관리", description = "block 상태를 통해 리뷰 공개 유무를 결정할 수 있는 API입니다.")
    @PatchMapping("/{artistId}/{portfolioId}")
    public ApiResponse updateReview(@PathVariable Long artistId, @PathVariable Long portfolioId, @RequestBody UpdateReviewDto reviewDto){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_UPDATE);
    }
}
