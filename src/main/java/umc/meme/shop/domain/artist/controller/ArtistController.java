package umc.meme.shop.domain.artist.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.review.dto.request.UpdateReviewDto;
import umc.meme.shop.domain.artist.service.ArtistService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArtistController {
    private final ArtistService artistService;

    @Operation(summary = "아티스트 프로필 관리")
    @PatchMapping("/mypage/{userId}/profile/artist")
    public ApiResponse updateProfile(@PathVariable Long userId, @RequestBody ArtistProfileDto profileDto){
        return ApiResponse.SuccessResponse(SuccessStatus.PROFILE_UPDATE);
    }

    @Operation(summary = "리뷰 관리", description = "block 상태를 통해 리뷰 공개 유무를 결정할 수 있는 API입니다.")
    @PatchMapping("/artist/review")
    public ApiResponse updateReview(@RequestBody UpdateReviewDto reviewDto){
        return ApiResponse.SuccessResponse(SuccessStatus.REVIEW_UPDATE);
    }

}
