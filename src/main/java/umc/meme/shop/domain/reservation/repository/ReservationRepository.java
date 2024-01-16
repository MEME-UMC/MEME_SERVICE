package umc.meme.shop.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
