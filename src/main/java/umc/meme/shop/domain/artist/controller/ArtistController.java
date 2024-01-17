package umc.meme.shop.domain.artist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.meme.shop.domain.artist.service.ArtistService;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.exception.GlobalException;
import umc.meme.shop.global.exception.GlobalExceptionHandler;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    //ApiResponse TEST
    @GetMapping("/temp/success")
    public ApiResponse tempSuccess(){
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }
    @GetMapping("/temp/failure")
    public ApiResponse tempFailure(){
        return ApiResponse.FailureResponse(ErrorStatus.TEMP);
    }

    @GetMapping("/temp/exception")
    public ApiResponse tempException(){
        throw new GlobalException(ErrorStatus.TEMP);
//        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }
}
