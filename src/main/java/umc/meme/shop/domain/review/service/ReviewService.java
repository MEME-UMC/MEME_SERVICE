package umc.meme.shop.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.domain.reservation.entity.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.response.ReviewResponseDto;
import umc.meme.shop.domain.review.entity.Review;
import umc.meme.shop.domain.review.repository.ReviewRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ModelRepository modelRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    //리뷰 작성
    @Transactional
    public void createReview(Long modelId, ReviewDto reviewDto){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Reservation reservation = reservationRepository.findByReservationIdAndModelId(reviewDto.getReservationId(), modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));

        //이미 리뷰 작성 완료
        if(reservation.isReview())
            throw new GlobalException(ErrorStatus.ALREADY_REVIEWED);

        //예약 미완료
        if(reservation.getStatus() != Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_REVIEW_REQUEST);

        Portfolio portfolio = reservation.getPortfolio();

        Review review = Review.builder()
                .model(model)
                .portfolio(portfolio)
                .star(reviewDto.getStar())
                .comment(reviewDto.getComment())
                .build();

        portfolio.updateReviewList(review);
        model.updateReviewList(review);

        reviewRepository.save(review);
        reservation.updateIsReview(true);
    }

    //내가 쓴 리뷰 조회
    public List<ReviewResponseDto> getMyReview(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        List<Review> reviewList = reviewRepository.findByModel(model);
        return reviewList.stream()
                .map(ReviewResponseDto::from)
                .toList();
    }
}
