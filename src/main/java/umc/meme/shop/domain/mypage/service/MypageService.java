package umc.meme.shop.domain.mypage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.mypage.dto.request.MypageInquiryDto;
import umc.meme.shop.domain.mypage.dto.response.*;
import umc.meme.shop.domain.mypage.entity.Inquiry;
import umc.meme.shop.domain.mypage.repository.InquiryRepository;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.user.UserRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    @Transactional
    public MypageDetailDto getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));
        return MypageDetailDto.from(user);
    }

    //문의하기
    @Transactional
    public void createInquiry(MypageInquiryDto mypageInquiryDto) {
        User user = userRepository.findById(mypageInquiryDto.getUserId())
                        .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        Inquiry inquiry = Inquiry.from(mypageInquiryDto, user);
        user.updateInquiryList(inquiry);
        inquiryRepository.save(inquiry);
    }

    // 문의하기 조회
    @Transactional
    public List<MypageInquiryResponseDto> getInquiry(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_USER));

        List<Inquiry> inquiryList = user.getInquiryList();

        return inquiryList.stream()
                .map(MypageInquiryResponseDto::from)
                .toList();
    }

}
