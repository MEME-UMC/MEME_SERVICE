package umc.meme.shop.domain.model.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    //모델 프로필 관리
    @Transactional
    public void updateModel(Long userId, ModelProfileDto request){
        Model model = modelRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.MODEL_NOT_FOUND));
        model.updateModel(request);
    }
}
