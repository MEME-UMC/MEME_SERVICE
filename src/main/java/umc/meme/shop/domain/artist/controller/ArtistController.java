package umc.meme.shop.domain.artist.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.service.ArtistService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArtistController {
    private final ArtistService artistService;

    @Operation(summary = "아티스트 프로필 관리")
    @PatchMapping("/mypage/{artistId}/profile/artist")
    public ApiResponse updateProfile(@PathVariable Long artistId, @RequestBody ArtistProfileDto profileDto){
        artistService.updateArtistProfile(artistId, profileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_PROFILE_UPDATE);
    }

    //temp method for Artist create
    @PostMapping("/artist")
    public ApiResponse createArtist(@RequestBody ArtistProfileDto profileDto){
        artistService.createArtist(profileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

}
