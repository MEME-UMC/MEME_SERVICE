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
import umc.meme.shop.domain.review.dto.response.MyReviewResponseDto;
import umc.meme.shop.domain.review.dto.response.ReviewAvailableListDto;
import umc.meme.shop.domain.review.dto.response.ReviewDetailsDto;
import umc.meme.shop.global.enums.Status;
import umc.meme.shop.domain.reservation.repository.ReservationRepository;
import umc.meme.shop.domain.review.dto.request.DeleteReviewDto;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.review.dto.response.ReviewListPageDto;
import umc.meme.shop.domain.review.entity.Review;
import umc.meme.shop.domain.review.entity.ReviewImg;
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
    private final PortfolioRepository portfolioRepository;

    //리뷰 작성
    @Transactional
    public void createReview(ReviewDto reviewDto) {
        Model model = modelRepository.findById(reviewDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Reservation reservation = reservationRepository.findByReservationIdAndModelId(reviewDto.getReservationId(), reviewDto.getModelId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_RESERVATION));

        // 이미 리뷰 작성 완료
        if (reservation.isReview())
            throw new GlobalException(ErrorStatus.ALREADY_REVIEWED);

        // 예약 미완료
        if (reservation.getStatus() != Status.COMPLETE)
            throw new GlobalException(ErrorStatus.INVALID_REVIEW_REQUEST);

        Portfolio portfolio = reservation.getPortfolio();

        List<ReviewImg> reviewImgList = reviewDto.getReviewImgSrc().stream()
                .map(ReviewImg::new)
                .toList();

        Review review = Review.from(model, portfolio, reviewDto);

        reviewImgList.forEach(review::addReviewImg);

        portfolio.updateReviewList(review);
        model.updateReviewList(review);

        reviewRepository.save(review);
        reservation.updateIsReview(true);
    }

    //리뷰 상세 조회
    public ReviewDetailsDto getReviewDetails(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_REVIEW));
        return ReviewDetailsDto.from(review);
    }

    //내가 쓴 리뷰 조회
    public List<MyReviewResponseDto> getMyReview(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        //리뷰 리스트 조회
        List<Review> reviewList = reviewRepository.findByModel(model);
        return reviewList.stream()
                .map(MyReviewResponseDto::from)
                .toList();
    }

    //리뷰 리스트 조회
    public ReviewListPageDto getReviewList(Long portfolioId, int page) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        // list를 page로 변환
        List<Review> reviewList = portfolio.getReviewList();
        Page<Review> reviewPage = getPage(page, reviewList);

        return ReviewListPageDto.from(reviewPage);
    }

    //리뷰 작성 가능 예약 리스트 조회
    public List<ReviewAvailableListDto> getReviewReservationList(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        List<Reservation> reservationList = model.getReservationList();

        //status != COMPLETE 이면 리스트에서 제거
        reservationList.removeIf(Reservation::isAvailableReview);

        //리뷰 작성 완료시 리스트에서 제거
        reservationList.removeIf(Reservation::isReview);

        return reservationList.stream()
                .map(ReviewAvailableListDto::from)
                .toList();
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

    private Page<Review> getPage(int page, List<Review> list){
        Pageable pageable = PageRequest.of(page, 30);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        //list를 page로 변환
        return new PageImpl<>(list.subList(start, end),
                pageable, list.size());
    }
}
