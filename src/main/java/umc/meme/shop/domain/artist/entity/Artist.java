package umc.meme.shop.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImg;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private WorkExperience workExperience;

    @Column(nullable = false)
    private Region region;

    @Column(nullable = false)
    private Specialization specialization;

    @Column(nullable = false)
    private MakeupLocation makeupLocation;

    @Column(nullable = true)
    private Date inactiveDate;

    @Column(nullable = true)
    private AvailableTime availableTime;
}
