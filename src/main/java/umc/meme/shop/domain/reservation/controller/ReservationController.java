package umc.meme.shop.domain.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.reservation.dto.request.AlterReservationDto;
import umc.meme.shop.domain.reservation.dto.request.ReservationRequestDto;
import umc.meme.shop.domain.reservation.service.ReservationService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;


    @Operation(summary = "예약 상태 변경", description = "예약 상태를 변경하는 API입니다.")
    @Parameters({
            @Parameter(name = "status", description = "Status enum 에 있는 값 (EXPECTED, COMPLETE, CANCEL)")
    })
    @PatchMapping("/{reservationId}/alteration")
    public ApiResponse alteration(@PathVariable Long reservationId, @RequestBody AlterReservationDto alterReservationDto){
        reservationService.updateReservationStatus(reservationId, alterReservationDto);
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_UPDATE);
    }

    @Operation(summary = "예약하기", description = "예약하기 기능을 수행하는 API입니다.")
    @PostMapping("/")
    public ApiResponse reservation(@RequestBody ReservationRequestDto reservationDto){
        reservationService.createReservation(reservationDto);
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_CREATE);
    }

    //아티스트 예약 조회
    @Operation(summary = "아티스트 예약 조회", description = "예약 정보를 조회하는 API입니다.")
    @GetMapping("/{artistId}/artist")
    public ApiResponse getArtistReservation(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_GET, reservationService.getArtistReservation(artistId));
    }

    //모델 예약 조회
    @Operation(summary = "모델 예약 조회", description = "예약 정보를 조회하는 API입니다.")
    @GetMapping("/{modelId}/model")
    public ApiResponse getModelReservation(@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_GET, reservationService.getModelReservation(modelId));
    }
}
