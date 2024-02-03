package umc.meme.shop.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import umc.meme.shop.domain.user.User;
import umc.meme.shop.domain.artist.dto.request.ArtistProfileDto;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;
import umc.meme.shop.global.enums.DayOfWeek;
import umc.meme.shop.global.enums.Provider;
import umc.meme.shop.global.enums.Times;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist extends User {

    @Column(nullable = false, length = 500)
    private String introduction;

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

    @ElementCollection
    @CollectionTable(name = "available_time_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @MapKeyColumn(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Map<DayOfWeek, Times> availableDayOfWeekAndTime;

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

        //specialization mapping
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
        if (request.getAvailableDayOfWeek() != null)
            this.availableDayOfWeekAndTime = request.getAvailableDayOfWeek();
    }

    public void updatePortfolioList(Portfolio portfolio){
        this.portfolioList.add(portfolio);
    }

    public void tempMethod(String email, String name, Provider provider){
        this.email = email;
        this.userName = name;
        this.provider = provider;
    }
}
