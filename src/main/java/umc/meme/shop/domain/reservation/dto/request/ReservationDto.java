package umc.meme.shop.domain.reservation.dto.request;

import lombok.Data;
import umc.meme.shop.domain.reservation.entity.enums.ReservationTime;

import java.util.Date;

@Data
public class ReservationDto {
    private Long modelId;
    private Long portfolioId;
    private Date reservationDate;
    private ReservationTime reservationTime;
}
