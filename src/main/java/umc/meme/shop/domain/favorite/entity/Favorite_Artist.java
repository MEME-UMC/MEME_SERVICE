package umc.meme.shop.domain.favorite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.model.entity.Model;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Favorite_Artist {

    @ManyToOne
    @JoinColumn(name="model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    private Artist artist;
}
