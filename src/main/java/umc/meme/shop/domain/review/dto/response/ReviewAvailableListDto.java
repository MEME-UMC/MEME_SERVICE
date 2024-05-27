package umc.meme.shop.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.reservation.entity.Reservation;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewAvailableListDto {
    private Long reservationId;
    private Long portfolioId;
    private String artistNickName;
    private String makeupName;
    private String portfolioImg;
    private LocalDate reservationDate;
    private String shopLocation; //샵 위치

    public static ReviewAvailableListDto from(Reservation reservation){
        return ReviewAvailableListDto.builder()
                .reservationId(reservation.getReservationId())
                .portfolioId(reservation.getReservationId())
                .artistNickName(reservation.getPortfolio().getArtist().getNickname())
                .makeupName(reservation.getPortfolio().getMakeupName())
                .portfolioImg(reservation.getPortfolio().getPortfolioImgList().get(0).getSrc())
                .reservationDate(reservation.getAvailableTime().getDate())
                .shopLocation(reservation.getLocation())
                .build();
    }
}
