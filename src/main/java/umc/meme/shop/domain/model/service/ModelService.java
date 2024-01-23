package umc.meme.shop.domain.model.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.response.ArtistDto;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.repository.FavoriteArtistRepository;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final FavoriteArtistRepository favoriteArtistRepository;

    //모델 프로필 관리
    @Transactional
    public void updateModel(Long userId, ModelProfileDto request){
        Model model = modelRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.MODEL_NOT_FOUND));
        model.updateModel(request);
    }

    //관심 아티스트 조회
    @Transactional
    public List<ArtistDto> getFavoriteArtist(Long userId){
        Model model = modelRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.MODEL_NOT_FOUND));
        List<FavoriteArtist> favoriteArtistList = favoriteArtistRepository.findByModel(model);
        return favoriteArtistList.stream()
                .map(ArtistDto::from)
                .collect(Collectors.toList());
    }

}
