package umc.meme.shop.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import umc.meme.shop.domain.common.BaseEntity;
import umc.meme.shop.global.enums.Gender;
import umc.meme.shop.domain.mypage.entity.Inquiry;

import java.time.LocalDateTime;
import java.util.List;
import umc.meme.shop.global.enums.Provider;

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
    protected String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false, length = 40)
    protected String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    protected List<Inquiry> inquiryList;

    public void updateInquiryList(Inquiry inquiry) {
        this.inquiryList.add(inquiry);
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Provider provider;
}
