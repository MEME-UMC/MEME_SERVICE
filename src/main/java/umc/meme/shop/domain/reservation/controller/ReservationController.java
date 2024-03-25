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
    @PatchMapping("/alteration")
    public ApiResponse alteration(@RequestBody AlterReservationDto alterReservationDto){
        reservationService.updateReservationStatus(alterReservationDto);
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_UPDATE);
    }

    @Operation(summary = "예약가능 장소 조회", description = "예약가능 장소 조회 기능을 수행하는 API입니다.")
    @GetMapping("/{artistId}/location")
    public ApiResponse getArtistLocation(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_LOCATION_GET, reservationService.getArtistLocation(artistId));
    }

    @Operation(summary = "예약가능 시간 조회", description = "예약가능 시간 조회 기능을 수행하는 API입니다.")
    @GetMapping("/{artistId}/time")
    public ApiResponse getArtistTime(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_TIME_GET, reservationService.getArtistTime(artistId));
    }


    @Operation(summary = "예약하기", description = "예약하기 기능을 수행하는 API입니다.")
    @PostMapping()
    public ApiResponse createReservation(@RequestBody ReservationRequestDto reservationDto){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_CREATE, reservationService.createReservation(reservationDto));
    }

    //아티스트 예약 조회
    @Operation(summary = "아티스트 예약 조회", description = "예약 정보를 조회하는 API입니다.")
    @GetMapping("/{artistId}/artist")
    public ApiResponse getArtistReservation(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_GET, reservationService.getArtistReservation(artistId));
    }

    //아티스트 예약 상세 조회
    @Operation(summary = "아티스트 예약 상세 조회", description = "Artist ver. 예약 정보를 상세조회하는 API입니다.")
    @GetMapping("/{reservationId}/artist/details")
    public ApiResponse getArtistReservationDetails(@PathVariable(name = "reservationId") Long reservationId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_DETAILS_GET, reservationService.getArtistDetailsReservation(reservationId));
    }

    //모델 예약 조회
    @Operation(summary = "모델 예약 조회", description = "예약 정보를 조회하는 API입니다.")
    @GetMapping("/{modelId}/model")
    public ApiResponse getModelReservation(@PathVariable Long modelId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_GET, reservationService.getModelReservation(modelId));
    }

    //모델 예약 상세 조회
    @Operation(summary = "모델 예약 상세 조회", description = "Model ver. 예약 정보를 상세조회하는 API입니다.")
    @GetMapping("/{reservationId}/model/details")
    public ApiResponse getModelReservationDetails(@PathVariable(name = "reservationId") Long reservationId){
        return ApiResponse.SuccessResponse(SuccessStatus.RESERVATION_DETAILS_GET, reservationService.getModelDetailsReservation(reservationId));
    }
}
