package umc.meme.shop.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.reservation.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.portfolio.artist = :artist")
    List<Reservation> findByArtist(@Param("artist") Artist artist);}
