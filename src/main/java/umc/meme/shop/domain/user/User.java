package umc.meme.shop.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import umc.meme.shop.global.enums.Gender;
import umc.meme.shop.domain.mypage.entity.Inquiry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import umc.meme.shop.global.enums.Provider;
import umc.meme.shop.global.enums.UserStatus;

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
    private Long userId;

    @NotNull
    protected String profileImg;

    @NotNull
    @Column(length = 40)
    protected String nickname;

    @NotNull
    @Column(length = 20)
    protected String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    protected Gender gender;

    @NotNull
    @Column(length = 40)
    protected String email;

    @NotNull
    protected String password;

    @NotNull
    protected String role;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    protected UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @NotNull
    protected Provider provider;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    protected List<Inquiry> inquiryList;

    @NotNull
    protected boolean details;

    public void updateInquiryList(Inquiry inquiry) {
        this.inquiryList.add(inquiry);
    }

}
