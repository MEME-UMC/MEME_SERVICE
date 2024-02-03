package umc.meme.shop.domain.mypage.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.mypage.dto.request.MypageInquiryDto;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.mypage.dto.response.MypageTosDto;
import umc.meme.shop.domain.mypage.entity.Inquiry;
import umc.meme.shop.domain.mypage.repository.InquiryRepository;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.mypage.dto.response.MypageTosDto;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.user.UserRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final ModelRepository modelRepository;
    private final ArtistRepository artistRepository;
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    @Transactional
    public MypageDetailDto getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));
        return MypageDetailDto.builder()
                .profileImg(user.getProfileImg())
                .nickname(user.getNickname())
                .name(user.getUserName())
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

    //문의하기
    @Transactional
    public void createInquiry(MypageInquiryDto mypageInquiryDto) {
        Inquiry inquiry = new Inquiry();
        inquiry.updateInquiry(mypageInquiryDto);
        inquiryRepository.save(inquiry);
    }

    // 문의하기 조회
    @Transactional
    public MypageInquiryDto getInquiry(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_INQUIRY));

        return MypageInquiryDto.from(inquiry);
    }

}
