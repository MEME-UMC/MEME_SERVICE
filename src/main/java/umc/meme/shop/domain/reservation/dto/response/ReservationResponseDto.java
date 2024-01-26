package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.exception.GlobalException;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private String modelName;
    private String artistName;
    private String makeupName;
    private int price;

    private Date reservationDate;
    private DayOfWeek reservationDayOfWeek;
    private String shopLocation; //샵 위치

    public static ReservationResponseDto from(Reservation reservation){
        Portfolio portfolio = reservation.getPortfolio();
        if(portfolio == null)
            throw new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO);

        Artist artist = reservation.getPortfolio().getArtist();

        return ReservationResponseDto.builder()
                .modelName(reservation.getModel().getName())
                .artistName(artist.getName())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .reservationDayOfWeek(reservation.getDayOfWeek())
                .reservationDate(reservation.getReservationDate())
                .shopLocation(artist.getShopLocation())
                .build();
    }

}
