package umc.meme.shop.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationTimeRequestDto {
    @NotBlank(message = "artistId를 입력해주세요")
    private Long artistId;
    @NotBlank(message = "날짜를 입력해주세요")
    private Date date;
}
