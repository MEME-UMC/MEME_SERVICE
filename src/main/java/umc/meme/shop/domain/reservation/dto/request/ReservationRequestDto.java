package umc.meme.shop.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.Date;
import java.util.Map;

@Data
public class ReservationRequestDto {
    @NotBlank(message = "modelId를 입력해주세요")
    private Long modelId;
    @NotBlank(message = "portfolioId를 입력해주세요")
    private Long portfolioId;
    @NotBlank(message = "예약날짜를 입력해주세요")
    private Date reservationDate;
    @NotNull(message = "예약시간을 입력해주세요")
    private Map<DayOfWeek, Times> reservationDayOfWeekAndTime;
    @NotNull(message = "예약 장소를 입력해주세요")
    private String location;
}
