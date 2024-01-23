package umc.meme.shop.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import umc.meme.shop.domain.reservation.entity.enums.ReservationTime;

import java.util.Date;

@Data
public class ReservationRequestDto {
    @NotBlank(message = "modelId를 입력해주세요")
    private Long modelId;
    @NotBlank(message = "portfolioId를 입력해주세요")
    private Long portfolioId;
    @NotBlank(message = "예약날짜를 입력해주세요")
    private Date reservationDate;
    @NotBlank(message = "예약시간을 입력해주세요")
    private ReservationTime reservationTime;
}
