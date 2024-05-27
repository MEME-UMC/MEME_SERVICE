package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;
import umc.meme.shop.global.exception.GlobalException;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private Long reservationId ;
    private Long portfolioId;
    private String modelNickName;
    private String artistNickName;
    private String makeupName;
    private int price;

    private LocalDate reservationDate;
    private DayOfWeek dayOfWeek;
    private Times times;
    private String shopLocation; //샵 위치
    private Status status;

    public static ReservationResponseDto from(Reservation reservation){
        Portfolio portfolio = reservation.getPortfolio();
        if(portfolio == null)
            throw new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO);

        Artist artist = reservation.getPortfolio().getArtist();
        AvailableTime availableTime = reservation.getAvailableTime();

        return ReservationResponseDto.builder()
                .reservationId(reservation.getReservationId())
                .portfolioId(portfolio.getPortfolioId())
                .modelNickName(reservation.getModel().getNickname())
                .artistNickName(artist.getNickname())
                .makeupName(portfolio.getMakeupName())
                .price(portfolio.getPrice())
                .reservationDate(availableTime.getDate())
                .dayOfWeek(availableTime.getDayOfWeek())
                .times(availableTime.getTimes())
                .shopLocation(artist.getShopLocation())
                .status(reservation.getStatus())
                .build();
    }

}
