package umc.meme.shop.domain.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.enums.Category;
import umc.meme.shop.domain.review.entity.Review;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    private Artist artist;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String makeupName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean isBlock;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio")
    private List<Review> reviewList;

    public void updatePortfolio(UpdatePortfolioDto request) {
        if(request.getCategory() != null){
            this.category = request.getCategory();
        }

        if(request.getPrice() >= 0){
            this.price = request.getPrice();
        }

        if(request.getInfo() != null){
            this.info = request.getInfo();
        }

        if(request.getMakeupName() != null){
            this.makeupName = request.getMakeupName();
        }

        this.isBlock = request.isBlock();
    }

    public void updateReviewList(Review review){
        this.reviewList.add(review);
    }


}
