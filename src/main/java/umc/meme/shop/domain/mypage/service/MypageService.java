package umc.meme.shop.domain.mypage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.mypage.dto.response.MypageTosDto;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.user.UserRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;

    @Transactional
    public MypageDetailDto getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));
        return MypageDetailDto.builder()
                .profileImg(user.getProfileImg())
                .nickname(user.getNickname())
                .name(user.getName())
                .gender(user.getGender())
                .email(user.getEmail())
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
