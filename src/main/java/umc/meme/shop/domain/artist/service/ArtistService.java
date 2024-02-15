package umc.meme.shop.domain.artist.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDetailDto;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.dto.response.ArtistDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.repository.FavoriteArtistRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ModelRepository modelRepository;
    private final FavoriteArtistRepository favoriteArtistRepository;

    //아티스트 프로필 관리/수정
    @Transactional
    public void updateArtistProfile(ArtistProfileDto profileDto){
        Artist artist = artistRepository.findById(profileDto.getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));
        artist.updateArtist(profileDto);
    }

    //아티스트 프로필 조회
    public ArtistDto getArtistProfile(ArtistProfileDetailDto profileDetailDto){
        Long userId = profileDetailDto.getUserId();
        Long artistId = profileDetailDto.getArtistId();

        Model model = modelRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        boolean isFavorite = false;
        Optional<FavoriteArtist> favoriteArtist = favoriteArtistRepository.findByModelAndArtistId(model, artistId);
        if(favoriteArtist.isPresent())
            isFavorite = true;

        return ArtistDto.from(artist, isFavorite);
    }

    //temp method for create Artist
    @Transactional
    public void createArtist(ArtistProfileDto dto){
        Artist artist = new Artist();
        artist.updateArtist(dto);
        artist.tempMethod();
        artistRepository.save(artist);
    }

}
