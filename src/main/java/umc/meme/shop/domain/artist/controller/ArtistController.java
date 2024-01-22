package umc.meme.shop.domain.artist.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.domain.review.dto.request.UpdateReviewDto;
import umc.meme.shop.domain.artist.service.ArtistService;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.exception.GlobalException;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArtistController {
    private final ArtistService artistService;

    //ApiResponse TEST
    @GetMapping("/temp/success")
    public ApiResponse tempSuccess(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }
    @GetMapping("/temp/failure")
    public ApiResponse tempFailure(){
        return ApiResponse.FailureResponse(ErrorStatus.TEMP);
    }

    @GetMapping("/temp/exception")
    public ApiResponse tempException(){
        throw new GlobalException(ErrorStatus.TEMP);
//        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

    @Operation(summary = "프로필 관리")
    @PatchMapping("/mypage/{userId}/profile")
    public ApiResponse updateProfile(@PathVariable Long userId, @RequestBody ArtistProfileDto profileDto){
        return ApiResponse.SuccessResponse(SuccessStatus.PROFILE_UPDATE);
    }

    @Operation(summary = "포트폴리오 조회")
    @GetMapping("/mypage/{userId}/portfolio")
    public ApiResponse getPortfolio(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_GET, "");
    }

    @Operation(summary = "포트폴리오 생성")
    @PostMapping("/mypage/{userId}/portfolio")
    public ApiResponse createPortfolio(@PathVariable Long userId, @RequestBody CreatePortfolioDto portfolioDto){
        // TODO: PortfolioImg 추가
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_CREATE);
    }

    @Operation(summary = "포트폴리오 수정/삭제")
    @PatchMapping("/mypage/{userId}/portfolio")
    public ApiResponse updatePortfolio(@PathVariable Long userId, @RequestBody UpdatePortfolioDto portfolioDto){
        // TODO: PortfolioImg 추가
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_UPDATE);
    }

    @Operation(summary = "예약 조회")
    @GetMapping("/mypage/{userId}/reservation")
    public ApiResponse getReservation(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_GET, "");
    }

    @Operation(summary = "리뷰 관리")
    @PatchMapping("/artist/review")
    public ApiResponse updateReview(@RequestBody UpdateReviewDto reviewDto){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_UPDATE);
    }

}
