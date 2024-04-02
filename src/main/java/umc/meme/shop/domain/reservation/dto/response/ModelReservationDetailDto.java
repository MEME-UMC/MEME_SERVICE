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
    private Long portfolioId;
    //아티스트 정보
    private String artistNickName;
    private String artistProfileImg;
    //예약 정보
    private String portfolioName;
    private String portfolioImg;
    private Category category;
    private String location;
    private int price;

    public static ModelReservationDetailDto from(Reservation reservation){
        Portfolio portfolio = reservation.getPortfolio();
        Artist artist = portfolio.getArtist();
        return ModelReservationDetailDto.builder()
                .reservationId(reservation.getReservationId())
                .portfolioId(portfolio.getPortfolioId())
                .artistNickName(artist.getNickname())
                .artistProfileImg(artist.getProfileImg())
                .portfolioName(portfolio.getMakeupName())
                .portfolioImg(portfolio.getPortfolioImgList().get(0).getSrc())
                .category(portfolio.getCategory())
                .location(reservation.getLocation())
                .price(portfolio.getPrice())
                .build();
    }

}

