package umc.meme.shop.domain.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.mypage.dto.request.MypageInquiryDto;
import umc.meme.shop.domain.mypage.service.MypageService;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MypageService mypageService;

    @Operation(summary = "마이페이지 조회")
    @GetMapping("/profile/{userId}")
    public ApiResponse getProfile(@PathVariable(name = "userId") Long userId) {
        return ApiResponse.SuccessResponse(SuccessStatus.MYPAGE_GET, mypageService.getProfile(userId));
    }

    @Operation(summary = "문의하기", description = "문의하기 API입니다.")
    @PostMapping("/contact")
    public ApiResponse contact(@RequestBody MypageInquiryDto mypageInquiryDto){
        mypageService.createInquiry(mypageInquiryDto);
        return ApiResponse.SuccessResponse(SuccessStatus.CONTACT_CREATE);
    }

    @Operation(summary = "문의 조회하기", description = "문의 조회하기 API입니다.")
    @GetMapping("/contact/{userId}")
    public ApiResponse getContact(@PathVariable(name = "userId") Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.CONTACT_GET, mypageService.getInquiry(userId));
    }
}
