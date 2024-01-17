package umc.meme.shop.domain.reservation.dto.request;

import lombok.Data;
import umc.meme.shop.domain.reservation.entity.enums.Status;

@Data
public class AlterReservationDto {
    private Status status;
}
