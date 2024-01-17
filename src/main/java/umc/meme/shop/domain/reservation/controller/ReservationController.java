package umc.meme.shop.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.reservation.dto.request.AlterReservationDto;
import umc.meme.shop.domain.reservation.dto.request.ReservationDto;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    //    @Operation(summary = "예약 상태 변경")
    @PatchMapping("/alteration")
    public ApiResponse alteration(@RequestBody AlterReservationDto alterReservationDto){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_UPDATE);
    }

    //    @Operation(summary = "예약하기")
    @PostMapping("/")
    public ApiResponse reservation(@RequestBody ReservationDto reservationDto){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_CREATE);
    }
}
