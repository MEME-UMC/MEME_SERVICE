package umc.meme.shop.domain.artist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import umc.meme.shop.domain.artist.service.ArtistService;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
}
