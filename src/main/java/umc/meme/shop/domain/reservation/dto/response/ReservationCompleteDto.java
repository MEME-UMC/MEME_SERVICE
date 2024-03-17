package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.sql.Time;
import java.util.Date;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCompleteDto {
    private Long reservationId;
    private String makeupName;
    private String artistNickName;
    private String location; //장소
    private Date reservationDate; //날짜
    private DayOfWeek dayOfWeek; //요일
    private Times times; //시간

    public static ReservationCompleteDto from(Portfolio portfolio, Reservation reservation){
        return ReservationCompleteDto.builder()
                .reservationId(reservation.getReservationId())
                .makeupName(portfolio.getMakeupName())
                .artistNickName(portfolio.getArtist().getNickname())
                .location(reservation.getLocation())
                .reservationDate(reservation.getReservationDate())
                .dayOfWeek(reservation.getDayOfWeek())
                .times(reservation.getTimes())
                .build();
    }

}
