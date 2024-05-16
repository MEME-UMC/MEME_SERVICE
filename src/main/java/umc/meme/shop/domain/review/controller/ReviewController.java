package umc.meme.shop.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.request.DeleteReviewDto;
import umc.meme.shop.domain.review.dto.request.UpdateReviewDto;
import umc.meme.shop.domain.review.service.ReviewService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 리스트 조회", description = "리뷰 리스트를 조회하는 API입니다.")
    @GetMapping("/{portfolioId}")
    public ApiResponse getReviewList(@PathVariable(name = "portfolioId") Long portfolioId,
                                     @RequestParam(value = "page", defaultValue = "0", required = false) int page
    ){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_GET, reviewService.getReviewList(portfolioId, page));
    }

    @Operation(summary = "리뷰 작성 가능 예약 리스트 조회", description = "리뷰 작성이 가능한 예약 리스트를 조회하는 API입니다.")
    @GetMapping("/available/{modelId}")
    public ApiResponse getReviewReservationList(@PathVariable(name = "modelId") Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_AVAILABLE_GET, reviewService.getReviewReservationList(modelId));
    }

    @Operation(summary = "리뷰 작성", description = "리뷰를 작성하는 API입니다.")
    @PostMapping()
    public ApiResponse createReview(@RequestBody ReviewDto reviewDto){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_CREATE, reviewService.createReview(reviewDto));
    }

    @Operation(summary = "리뷰 세부 조회", description = "리뷰를 세부 조회하는 API입니다.")
    @GetMapping("/details/{reviewId}")
    public ApiResponse getReviewDetails(@PathVariable(name = "reviewId") Long reviewId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_DETAILS_GET, reviewService.getReviewDetails(reviewId));
    }

    @Operation(summary = "내가 쓴 리뷰 조회", description = "본인이 쓴 리뷰를 조회하는 API입니다.")
    @GetMapping("/me/{modelId}")
    public ApiResponse getMyReview(@PathVariable(name = "modelId") Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_GET, reviewService.getMyReview(modelId));
    }

    @Operation(summary = "리뷰 수정", description = "모델이 작성한 리뷰를 수정하는 API입니다.")
    @PatchMapping()
    public ApiResponse updateReview(@RequestBody UpdateReviewDto updateReviewDto){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_PATCH, reviewService.updateReview(updateReviewDto));
    }

    @Operation(summary = "리뷰 삭제", description = "모델이 작성한 리뷰를 삭제하는 API입니다.")
    @DeleteMapping()
    public ApiResponse deleteReview(@RequestBody DeleteReviewDto reviewDto){
        reviewService.deleteReview(reviewDto);
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_DELETE);
    }
}
