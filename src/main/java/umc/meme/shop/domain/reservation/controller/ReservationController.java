package umc.meme.shop.domain.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "예약 상태 변경", description = "예약 상태를 변경하는 API입니다.")
    @Parameters({
            @Parameter(name = "status", description = "Status enum 에 있는 값 (EXPECTED, COMPLETE, CANCEL)")
    })
    @PatchMapping("/alteration")
    public ApiResponse alteration(@RequestBody AlterReservationDto alterReservationDto){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_UPDATE);
    }

    @Operation(summary = "예약하기", description = "예약하기 기능을 수행하는 API입니다.")
    @PostMapping("/")
    public ApiResponse reservation(@RequestBody ReservationDto reservationDto){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_CREATE);
    }
}
