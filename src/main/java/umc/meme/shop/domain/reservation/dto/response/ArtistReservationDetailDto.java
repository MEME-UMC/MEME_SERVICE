package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistReservationDetailDto {
    private Long reservationId;
    //모델 정보
    private String modelNickName;
    private String modelProfileImg;
    private Gender gender;
    private SkinType skinType;
    private PersonalColor personalColor;
    //예약 정보
    private String portfolioName;
    private Date date;
//    private DayOfWeek dayOfWeek;
//    private Times times;
    private String location;

    public static ArtistReservationDetailDto from(Reservation reservation, Model model){
        return ArtistReservationDetailDto.builder()
                .reservationId(reservation.getReservationId())
                .modelNickName(model.getNickname())
                .modelProfileImg(model.getProfileImg())
                .gender(model.getGender())
                .skinType(model.getSkinType())
                .personalColor(model.getPersonalColor())
                .portfolioName(reservation.getPortfolio().getMakeupName())
                .date(reservation.getReservationDate())
//                .dayOfWeek(reservation.getReservationDayOfWeekAndTime())
//                .times(reservation.getAvailableTime().getTimes())
                .location(reservation.getLocation())
                .build();
    }

}
