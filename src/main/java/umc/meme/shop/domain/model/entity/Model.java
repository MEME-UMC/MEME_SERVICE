package umc.meme.shop.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.enums.Gender;
import umc.meme.shop.domain.model.entity.enums.PersonalColor;
import umc.meme.shop.domain.model.entity.enums.SkinType;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

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
    private SkinType skinType;

    @Column(nullable = false)
    private PersonalColor personalColor;

    private Date inactive;
}
