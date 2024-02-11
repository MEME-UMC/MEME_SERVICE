package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.meme.shop.domain.common.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    public ReviewImg(String src) {
        this.src = src;
    }
}
