package umc.meme.shop.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

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

    @Column(nullable = false, length = 500)
    private String introduction = "안녕하세요! 저는 ___입니다!";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkExperience workExperience;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> region;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> specialization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MakeupLocation makeupLocation;

    @Column(nullable = true)
    private String shopLocation; //샵의 위치

    @Column(nullable = true)
    private Date inactiveDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private AvailableTime availableTime;

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

        //region mapping
        if (request.getRegion() != null){
            List<String> regionList = new ArrayList<>();
            for(Region region : request.getRegion())
                regionList.add(region.getValue());
            this.region = regionList;
        }

        //sepecialization mapping
        if (request.getSpecialization() != null){
            List<String> specialization = new ArrayList<>();
            for(Category category : request.getSpecialization())
                specialization.add(category.getValue());
            this.specialization = specialization;
        }
        if (request.getMakeupLocation() != null)
            this.makeupLocation = request.getMakeupLocation();
        if (request.getShopLocation() != null)
            this.shopLocation = request.getShopLocation();
        if (request.getAvailableTime() != null)
            this.availableTime = request.getAvailableTime();
    }

    public void updatePortfolioList(Portfolio portfolio){
        this.portfolioList.add(portfolio);
    }

    public void tempMethod(String email, String name){
        this.email = email;
        this.name = name;
    }
}
