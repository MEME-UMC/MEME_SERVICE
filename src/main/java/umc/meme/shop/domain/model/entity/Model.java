package umc.meme.shop.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.enums.PersonalColor;
import umc.meme.shop.domain.model.entity.enums.SkinType;
import umc.meme.shop.domain.reservation.entity.Reservation;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Column(nullable = false)
    private String profileImg;

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

    public void updateReservationList(Reservation reservation){
        this.reservationList.add(reservation);
    }
}
