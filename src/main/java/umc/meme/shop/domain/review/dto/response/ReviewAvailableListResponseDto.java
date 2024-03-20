package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.global.enums.Times;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewAvailableListResponseDto {
    private Long reservationId;
    private Long portfolioId;
    private String artistNickName;
    private String makeupName;
    private Date reservationDate;
    private DayOfWeek dayOfWeek;
    private Times times;
    private String shopLocation; //샵 위치

    public static ReviewAvailableListResponseDto from(Reservation reservation){
        AvailableTime availableTime = reservation.getAvailableTime();
        return ReviewAvailableListResponseDto.builder()
                .reservationId(reservation.getReservationId())
                .portfolioId(reservation.getReservationId())
                .artistNickName(reservation.getPortfolio().getArtist().getNickname())
                .makeupName(reservation.getPortfolio().getMakeupName())
                .reservationDate(availableTime.getDate())
                .dayOfWeek(availableTime.getDayOfWeek())
                .times(availableTime.getTimes())
                .shopLocation(reservation.getLocation())
                .build();
    }
}
