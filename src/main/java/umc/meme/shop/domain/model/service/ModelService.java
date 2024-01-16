package umc.meme.shop.domain.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.model.repository.ModelRepository;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
}
