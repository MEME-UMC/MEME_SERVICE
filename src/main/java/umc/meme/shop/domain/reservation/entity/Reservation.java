package umc.meme.shop.domain.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.enums.ReservationTime;
import umc.meme.shop.domain.reservation.entity.enums.Status;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @OneToOne
    @JoinColumn(name="model_id", nullable = false)
    private Model model;

    @OneToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private boolean isReview = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationTime reservationTime;

    @Column(nullable = false)
    private Date reservationDate;
}
