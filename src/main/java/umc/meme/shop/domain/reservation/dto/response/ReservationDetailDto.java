package umc.meme.shop.domain.reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailDto {
    private Long reservationId;
    private Long portfolioId;
    //아티스트 정보
    private String artistNickName;
    private String artistProfileImg;
    private String artistEmail;
    //예약 정보
    private String portfolioName;
    private Category category;
    private Date reservationDate;
    private Times reservationTime;
    private String location;
    private int price;
    //모델 정보
    private String modelName;
    // TODO- ADD: model contact field
    private Gender gender;
    private SkinType skinType;
    private PersonalColor personalColor;

    public static ReservationDetailDto from(Reservation reservation, Model model){
        Portfolio portfolio = reservation.getPortfolio();
        Artist artist = portfolio.getArtist();
        return ReservationDetailDto.builder()
                .reservationId(reservation.getReservationId())
                .portfolioId(portfolio.getPortfolioId())
                .artistNickName(artist.getNickname())
                .artistProfileImg(artist.getProfileImg())
                .artistEmail(artist.getEmail())
                .portfolioName(portfolio.getMakeupName())
                .category(portfolio.getCategory())
                .reservationDate(reservation.getAvailableTime().getDate())
                .reservationTime(reservation.getAvailableTime().getTimes())
                .location(reservation.getLocation())
                .price(portfolio.getPrice())
                .modelName(model.getUsername())
                .gender(model.getGender())
                .skinType(model.getSkinType())
                .personalColor(model.getPersonalColor())
                .build();
    }

}

