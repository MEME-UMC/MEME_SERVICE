package umc.meme.shop.domain.artist.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    //아티스트 프로필 관리/수정
    @Transactional
    public void updateArtistProfile(Long artistId, ArtistProfileDto profileDto){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));
        artist.updateArtist(profileDto);
    }
}
