package umc.meme.shop.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequestDto {
    @NotBlank(message = "modelId를 입력해주세요")
    private Long modelId;
    @NotBlank(message = "portfolioId를 입력해주세요")
    private Long portfolioId;
    @NotBlank(message = "availableTimeId를 입력해주세요")
    private Long availableTimeId;
    @NotNull(message = "예약 장소를 입력해주세요")
    private String location;
}
