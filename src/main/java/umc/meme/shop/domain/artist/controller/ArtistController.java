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
    @PatchMapping("/mypage/profile/artist")
    public ApiResponse updateProfile(@RequestBody ArtistProfileDto profileDto){
        artistService.updateArtistProfile(profileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_PROFILE_UPDATE);
    }

    @Operation(summary = "아티스트 프로필 관리 조회(수정 전 정보 불러오기 용)")
    @GetMapping("/mypage/profile/artist/{userId}")
    public ApiResponse getProfile (@PathVariable(name = "userId") Long userId){
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_PROFILE_GET, artistService.getProfile(userId));
    }

    @Operation(summary = "아티스트 프로필 조회")
    @GetMapping("/profile/{userId}/{artistId}")
    public ApiResponse getArtistProfile(@PathVariable(name = "userId") Long userId, @PathVariable(name = "artistId") Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.ARTIST_PROFILE_GET, artistService.getArtistProfile(userId, artistId));
    }

    //temp method for Artist create
    @Operation(summary = "temp 아티스트 생성(프론트랑 상관X)")
    @PostMapping("/artist")
    public ApiResponse createArtist(@RequestBody ArtistProfileDto profileDto){
        artistService.createArtist(profileDto);
        return ApiResponse.SuccessResponse(SuccessStatus.TEMP);
    }

}
