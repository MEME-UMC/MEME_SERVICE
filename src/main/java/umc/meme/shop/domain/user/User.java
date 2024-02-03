package umc.meme.shop.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.mypage.entity.Inquiry;

import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Long userId;

    @Column(nullable = false)
    protected String profileImg;

    @Column(nullable = false, length = 40)
    protected String nickname;

    @Column(nullable = false, length = 20)
    protected String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false, length = 40)
    protected String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    protected List<Inquiry> inquiryList;

    public void updateInquiryList(Inquiry inquiry) {
        this.inquiryList.add(inquiry);
    }
}
