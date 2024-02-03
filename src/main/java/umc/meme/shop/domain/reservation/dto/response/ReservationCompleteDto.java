package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String artist;
    private String location; //장소
    private Date reservationDate; //날짜
    private Map<DayOfWeek, Times> reservationDayOfWeekAndTime; //요일과 시간

}
