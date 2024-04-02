package umc.meme.shop.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.*;

import java.util.List;
import java.util.Map;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist extends User {

    @Column(length = 500, nullable = true)
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private WorkExperience workExperience;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private List<Region> region;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private List<Category> specialization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private MakeupLocation makeupLocation;

    @Column(nullable = true)
    private String shopLocation; //샵의 위치

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<AvailableTime> availableTimeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Portfolio> portfolioList;


    public void updateArtist(ArtistProfileDto request) {
        if (request.getProfileImg() != null)
            this.profileImg = request.getProfileImg();
        if (request.getNickname() != null)
            this.nickname = request.getNickname();
        if (request.getGender() != null)
            this.gender = request.getGender();
        if (request.getIntroduction() != null)
            this.introduction = request.getIntroduction();
        if (request.getWorkExperience() != null)
            this.workExperience = request.getWorkExperience();
        if (request.getRegion() != null)
            this.region = request.getRegion();
        if (request.getSpecialization() != null)
            this.specialization = request.getSpecialization();
        if (request.getMakeupLocation() != null)
            this.makeupLocation = request.getMakeupLocation();
        if (request.getShopLocation() != null)
            this.shopLocation = request.getShopLocation();
    }

    public void updatePortfolioList(Portfolio portfolio){
        this.portfolioList.add(portfolio);
    }

    public void updateAvailableTimeList(List<AvailableTime> availableTimeList){this.availableTimeList = availableTimeList;}

    public void tempMethod(){
        this.username = "name";
        this.email="";
        this.password="";
        this.role="ARTIST";
        this.userStatus = UserStatus.ACTIVE;
        this.provider = Provider.KAKAO;
        this.details = false;
    }
}
