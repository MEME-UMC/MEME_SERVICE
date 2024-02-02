package umc.meme.shop.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.enums.Status;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;

import java.util.Date;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name="model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private boolean isReview = false;

    @ElementCollection
    @CollectionTable(name = "reservation_time_mapping",
            joinColumns = {@JoinColumn(name = "reservation_id", referencedColumnName = "reservationId")})
    @MapKeyColumn(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Map<DayOfWeek, Times> reservationDayOfWeekAndTime;

    @Column(nullable = false)
    private Date reservationDate;

    @Column(nullable = false)
    private String location; //예약 장소

    public void updateReservation(Status status){
        if(status != null)
            this.status = status;
    }

    public void updateIsReview(boolean bool){
        this.isReview = bool;
    }
}
