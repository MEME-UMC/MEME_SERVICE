package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.meme.shop.domain.common.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter @Setter
public class ReviewImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImgId;

    @ManyToOne
    @JoinColumn(name="review_id", nullable = false)
    private Review review;

    @Column(nullable = false)
    private String src;

    public static ReviewImg from(String src){
        return ReviewImg.builder()
                .src(src)
                .build();
    }

    public void updateSrc(String src){this.src = src;}
}
