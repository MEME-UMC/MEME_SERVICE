package umc.meme.shop.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    //    @Operation(summary = "예약 상태 변경")
    @PatchMapping("/alteration")
    public ApiResponse alteration(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

    //    @Operation(summary = "예약하기")
    @PostMapping("/")
    public ApiResponse reservation(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }
}
