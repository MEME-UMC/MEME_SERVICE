package umc.meme.shop.domain.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

//    @Operation(summary = "마이페이지")
    @GetMapping("/{userId}")
    public ApiResponse mypage(@PathVariable Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.MYPAGE_GET, "");
    }

//    @Operation(summary = "내 정보 조회")
    @GetMapping("/{userId}/details")
    public ApiResponse details(@PathVariable Long userId){
        //MypageDetailDto
        return ApiResponse.SuccessResponse(SuccessStatus.DETAILS_GET, "");
    }

//    @Operation(summary = "약관 및 정책")
    @GetMapping("/tos")
    public ApiResponse tos(){
        return ApiResponse.SuccessResponse(SuccessStatus.TOS_GET, "");
    }

//    @Operation(summary = "문의하기")
    @PostMapping("/contact")
    public ApiResponse contact(){
        return ApiResponse.SuccessResponse(SuccessStatus.CONTACT_CREATE, "");
    }
}
