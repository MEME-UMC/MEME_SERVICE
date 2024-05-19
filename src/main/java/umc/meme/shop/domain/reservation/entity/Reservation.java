package umc.meme.shop.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.dto.request.ReservationRequestDto;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.Date;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User model;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_time_id")
    private AvailableTime availableTime;

    @Column(nullable = false)
    private Long durationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private boolean isReview = false;

    @Column(nullable = false)
    private String location; //예약 장소

    public void updateReservation(Status status){
        if(status != null)
            this.status = status;
    }

    public void updateIsReview(boolean bool){
        this.isReview = bool;
    }

    public boolean isAvailableReview(){
        return !status.equals(Status.COMPLETE);
    }

    public static Reservation from(Model model, Portfolio portfolio, AvailableTime availableTime, String location){
        return Reservation.builder()
                .model(model)
                .portfolio(portfolio)
                .availableTime(availableTime)
                .durationTime(portfolio.getDurationTime())
                .status(Status.PENDING)
                .location(location)
                .build();
    }
}
