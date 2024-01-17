package umc.meme.shop.domain.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import umc.meme.shop.domain.model.service.ModelService;

@RestController
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
}
