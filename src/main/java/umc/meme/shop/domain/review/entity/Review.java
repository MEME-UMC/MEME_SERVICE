package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.review.dto.request.ReviewDto;
import umc.meme.shop.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User model;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<ReviewImg> reviewImgList;

    @Column(nullable = false)
    private int star;

    @Column(nullable = true, length = 200)
    private String comment;

    public static Review from(Model model, Portfolio portfolio, ReviewDto dto){
        return Review.builder()
                .model(model)
                .portfolio(portfolio)
                .star(dto.getStar())
                .comment(dto.getComment())
                .reviewImgList(new ArrayList<ReviewImg>())
                .build();
    }

    public void addReviewImg(ReviewImg reviewImg) {
        this.reviewImgList.add(reviewImg);
        reviewImg.setReview(this);
    }
}
