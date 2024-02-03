package umc.meme.shop.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.enums.PersonalColor;
import umc.meme.shop.domain.model.entity.enums.SkinType;
import umc.meme.shop.domain.reservation.entity.Reservation;
import umc.meme.shop.domain.review.entity.Review;
import umc.meme.shop.domain.user.User;

import java.util.Date;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model extends User {

    @Column(nullable = true, length = 500)
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkinType skinType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonalColor personalColor;

    private Date inactive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<FavoritePortfolio> favoritePortfolioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<FavoriteArtist> favoriteArtistList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<Reservation> reservationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<Review> reviewList;

    public void updateModel(ModelProfileDto request) {
        if(request.getProfileImg() != null){
            this.profileImg = request.getProfileImg();
        }
        if(request.getNickname() != null){
            this.nickname = request.getNickname();
        }
        if(request.getGender() != null){
            this.gender = request.getGender();
        }
        if(request.getSkinType() != null){
            this.skinType = request.getSkinType();
        }
        if(request.getPersonalColor() != null){
            this.personalColor = request.getPersonalColor();
        }
    }

    public void updateFavoriteArtistList(FavoriteArtist artist){
        this.favoriteArtistList.add(artist);
    }

    public void updateFavoritePortfolioList(FavoritePortfolio portfolio){
        this.favoritePortfolioList.add(portfolio);
    }
    public void updateReservationList(Reservation reservation){
        this.reservationList.add(reservation);
    }

    public void updateReviewList(Review review){
        this.reviewList.add(review);
    }
}
