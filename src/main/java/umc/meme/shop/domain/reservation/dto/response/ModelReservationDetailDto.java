package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Category;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelReservationDetailDto {
    private Long reservationId;
    //아티스트 정보
    private String artistNickName;
    private String artistProfileImg;
    //예약 정보
    private String portfolioName;
    private Category category;
    private String location;
    //TODO: Portfolio 관련 정보 추가논의

    public static ModelReservationDetailDto from(Reservation reservation){
        Portfolio portfolio = reservation.getPortfolio();
        Artist artist = portfolio.getArtist();
        return ModelReservationDetailDto.builder()
                .reservationId(reservation.getReservationId())
                .artistNickName(artist.getNickname())
                .artistProfileImg(artist.getProfileImg())
                .portfolioName(portfolio.getMakeupName())
                .category(portfolio.getCategory())
                .location(reservation.getLocation())
                .build();
    }

}

