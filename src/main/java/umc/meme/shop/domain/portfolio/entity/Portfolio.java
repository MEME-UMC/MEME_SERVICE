package umc.meme.shop.domain.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio")
    private List<PortfolioImg> portfolioImgList;

    @Column
    private String averageStars;

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

        this.isBlock = request.getIsBlock();
    }

    public void updateReviewList(Review review){
        this.reviewList.add(review);
        //별점 업데이트
        updateAverageStars();
    }

    private void updateAverageStars(){
        int count = this.reviewList.size();
        if(count == 0) {
            this.averageStars = "0.00";
            return;
        }

        double stars = 0;
        for(Review review : reviewList){
            stars += review.getStar();
        }
        this.averageStars = String.format("%.2f", stars/count);
    }

    public static Portfolio from(Artist artist, CreatePortfolioDto dto){
        return Portfolio.builder()
                .artist(artist)
                .category(dto.getCategory())
                .makeupName(dto.getMakeupName())
                .info(dto.getInfo())
                .price(dto.getPrice())
                .portfolioImgList(new ArrayList<PortfolioImg>())
                .averageStars("0.00")
                .isBlock(false)
                .build();
    }

    public void addPortfolioImg(String src) {
        PortfolioImg portfolioImg = new PortfolioImg();
        portfolioImg.setPortfolio(this);
        portfolioImg.setSrc(src);
        this.portfolioImgList.add(portfolioImg);
    }
}
