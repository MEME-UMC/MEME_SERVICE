package umc.meme.shop.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

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

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private List<Region> region;

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private List<Category> specialization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MakeupLocation makeupLocation;

    @Column(nullable = true)
    private Date inactiveDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private AvailableTime availableTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Portfolio> portfolioList;
}
