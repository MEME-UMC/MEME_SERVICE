package umc.meme.shop.domain.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.mypage.service.MypageService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MypageService mypageService;

    @Operation(summary = "모델 마이페이지 조회")
    @GetMapping("/{modelId}/profile/model")
    public ApiResponse getModelProfile (@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.MYPAGE_GET, mypageService.getModelProfile(modelId));
    }

    @Operation(summary = "아티스트 마이페이지 조회")
    @GetMapping("/{artistId}/profile/artist")
    public ApiResponse getArtistProfile(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.MYPAGE_GET, mypageService.getArtistProfile(artistId));
    }

    @Operation(summary = "내 정보 조회", description = "본인 정보를 조회하는 API입니다.")
    @GetMapping("/{userId}/details")
    public ApiResponse details(@PathVariable Long userId){
        //MypageDetailDto
        return ApiResponse.SuccessResponse(SuccessStatus.DETAILS_GET, "");
    }

    @Operation(summary = "약관 및 정책", description = "약관 및 정책을 조회하는 API입니다.")
    @GetMapping("/tos")
    public ApiResponse tos(){
        return ApiResponse.SuccessResponse(SuccessStatus.TOS_GET, mypageService.getTos());
    }

    @Operation(summary = "문의하기", description = "문의하기 API입니다.")
    @PostMapping("/contact")
    public ApiResponse contact(){
        return ApiResponse.SuccessResponse(SuccessStatus.CONTACT_CREATE, "");
    }
}
