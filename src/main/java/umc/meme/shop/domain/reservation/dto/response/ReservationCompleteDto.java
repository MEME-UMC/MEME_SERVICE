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
    private String makeupName;
    private String artistNickName;
    private String location; //장소
    private Date reservationDate; //날짜
    private Map<DayOfWeek, Times> reservationDayOfWeekAndTime; //요일과 시간

    public static ReservationCompleteDto from(Portfolio portfolio, Reservation reservation){
        return ReservationCompleteDto.builder()
                .makeupName(portfolio.getMakeupName())
                .artistNickName(portfolio.getArtist().getNickname())
                .location(reservation.getLocation())
                .reservationDate(reservation.getReservationDate())
                .reservationDayOfWeekAndTime(reservation.getReservationDayOfWeekAndTime())
                .build();
    }

}
