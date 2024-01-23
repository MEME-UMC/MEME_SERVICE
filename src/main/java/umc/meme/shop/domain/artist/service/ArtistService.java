package umc.meme.shop.domain.artist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ReservationRepository reservationRepository;

}
