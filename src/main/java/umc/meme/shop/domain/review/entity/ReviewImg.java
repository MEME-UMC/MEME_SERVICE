package umc.meme.shop.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.common.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReviewImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewImgId;

    @ManyToOne
    @JoinColumn(name="review_id", nullable = false)
    private Review review;

    @Column(nullable = false)
    private String src;
}
