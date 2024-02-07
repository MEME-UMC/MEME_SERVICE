package umc.meme.shop.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.domain.review.converter.ReviewConverter;
import umc.meme.shop.domain.review.dto.request.DeleteReviewDto;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.response.ReviewListPageDto;
import umc.meme.shop.domain.review.dto.response.ReviewResponseDto;
import umc.meme.shop.domain.review.entity.Review;
import umc.meme.shop.domain.review.entity.ReviewImg;
import umc.meme.shop.domain.review.repository.ReviewImgRepository;
import umc.meme.shop.domain.review.repository.ReviewRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ModelRepository modelRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final PortfolioRepository portfolioRepository;
    private final ReviewImgRepository reviewImgRepository;

    //리뷰 작성
    @Transactional
    public void createReview(ReviewDto reviewDto){
        Model model = modelRepository.findById(reviewDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Reservation reservation = reservationRepository.findByReservationIdAndModelId(reviewDto.getReservationId(), reviewDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));

        //이미 리뷰 작성 완료
        if(reservation.isReview())
            throw new GlobalException(ErrorStatus.ALREADY_REVIEWED);

        //예약 미완료
        if(reservation.getStatus() != Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_REVIEW_REQUEST);

        Portfolio portfolio = reservation.getPortfolio();

        List<ReviewImg> reviewImgList = new ArrayList<>();
        for (String src : reviewDto.getReviewImgSrc()) {
            ReviewImg reviewImg = new ReviewImg();
            reviewImg.setSrc(src);
            reviewImgList.add(reviewImg);
        }

        Review review = Review.from(model, portfolio, reviewDto);

        for (ReviewImg reviewImg : reviewImgList) {
            reviewImg.setReview(review);
            review.getReviewImgList().add(reviewImg);
        }

        portfolio.updateReviewList(review);
        portfolio.updateAverageStars();
        model.updateReviewList(review);

        reviewRepository.save(review);
        reservation.updateIsReview(true);
    }

    //내가 쓴 리뷰 조회
    public List<ReviewResponseDto> getMyReview(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        //리뷰 리스트 조회
        List<Review> reviewList = reviewRepository.findByModel(model);
        return reviewList.stream()
                .map(ReviewResponseDto::from)
                .toList();
    }

    //리뷰 리스트 조회
    public ReviewListPageDto getReviewList(Long portfolioId, int page) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        List<Review> reviewList = portfolio.getReviewList();

        // page로 mapping
        Pageable pageable = PageRequest.of(page, 30);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reviewList.size());

        // list를 page로 변환
        List<Review> pagedReviewList = reviewList.subList(start, end);
        Page<Review> reviewPage = new PageImpl<>(pagedReviewList, pageable, reviewList.size());

        // 별점 현황
        Map<Integer, Integer> starStatus = new HashMap<>(Map.of(5, 0, 4, 0, 3, 0, 2, 0, 1, 0));
        for (Review review : pagedReviewList) {
            int star = review.getStar();
            starStatus.put(star, starStatus.get(star) + 1);
        }

        // ReviewConverter를 사용하여 ReviewListPageDto 생성
        ReviewListPageDto pageDto = ReviewConverter.reviewPageConverter(reviewPage);

        // 별점 현황 추가
        pageDto.setStarStatus(starStatus);

        return pageDto;
    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(DeleteReviewDto reviewDto){
        Model model = modelRepository.findById(reviewDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Review review = reviewRepository.findById(reviewDto.getReviewId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_REVIEW));
        if(!review.getModel().equals(model))
            throw new GlobalException(ErrorStatus.INVALID_MODEL_FOR_REVIEW);

        reviewRepository.delete(review);
    }
}
