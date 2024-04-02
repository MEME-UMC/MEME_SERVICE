package umc.meme.shop.domain.artist.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.dto.request.AvailableTimeRequestDto;
import umc.meme.shop.domain.artist.dto.response.ArtistDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.AvailableTime;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.artist.repository.AvailableTimeRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.repository.FavoriteArtistRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Times;
import umc.meme.shop.global.exception.GlobalException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ModelRepository modelRepository;
    private final FavoriteArtistRepository favoriteArtistRepository;
    private final AvailableTimeRepository availableTimeRepository;

    //아티스트 프로필 관리/수정
    @Transactional
    public void updateArtistProfile(ArtistProfileDto profileDto){
        Artist artist = artistRepository.findById(profileDto.getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));
        artist.updateArtist(profileDto);
    }

    //아티스트 프로필 조회 (관리 조회 용)
    public ArtistProfileDto getProfile(Long userId){
        Artist artist = artistRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));
        return ArtistProfileDto.from(artist);
    }

    //아티스트 프로필 조회
    public ArtistDto getArtistProfile(Long userId, Long artistId){
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

    //아티스트 예약 가능 시간 편집
    @Transactional
    public void patchArtistAvailableTime(AvailableTimeRequestDto dto){
        Artist artist = artistRepository.findById(dto.getUserId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //TODO: 기존 예약 시간 테이블 유치 여부 논의

        //새로운 예약 시간 설정
        List<AvailableTime> availableTimeList = dto.getAvailableTimeDtoList().stream()
                .map(AvailableTime::from)
                .peek(availableTime -> availableTime.updateArtist(artist))
                .toList();
        artist.updateAvailableTimeList(availableTimeList);
    }

    //temp method for create Artist
    @Transactional
    public void createArtist(ArtistProfileDto dto){
        Artist artist = new Artist();
        artist.updateArtist(dto);
        artist.tempMethod();

        artistRepository.save(artist);
        AvailableTimeRequestDto.AvailableTimeDto timeDto = AvailableTimeRequestDto.AvailableTimeDto.from(new Date(), DayOfWeek.MON, Times._15_00);
        AvailableTime availableTime = AvailableTime.from(timeDto);
        availableTime.updateArtist(artist);
        availableTimeRepository.save(availableTime);
    }

}
