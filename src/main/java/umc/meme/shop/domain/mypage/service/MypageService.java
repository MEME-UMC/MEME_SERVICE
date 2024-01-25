package umc.meme.shop.domain.mypage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.mypage.dto.response.MypageTosDto;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final ModelRepository modelRepository;
    private final ArtistRepository artistRepository;

    // 모델 마이페이지 조회
    @Transactional
    public MypageDetailDto getModelProfile(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        return MypageDetailDto.builder()
                .profileImg(model.getProfileImg())
                .nickname(model.getNickname())
                .name(model.getName())
                .gender(model.getGender())
                .email(model.getEmail())
                .build();
    }

    //아티스트 마이페이지 조회
    @Transactional
    public MypageDetailDto getArtistProfile(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        return MypageDetailDto.builder()
                .profileImg(artist.getProfileImg())
                .nickname(artist.getNickname())
                .name(artist.getName())
                .gender(artist.getGender())
                .email(artist.getEmail())
                .build();
    }

    //약관 조회
    @Transactional
    public MypageTosDto getTos() {
        return MypageTosDto.builder()
                .tos("Example")
                .build();
    }
}
