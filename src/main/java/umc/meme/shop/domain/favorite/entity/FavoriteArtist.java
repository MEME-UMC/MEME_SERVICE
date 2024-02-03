package umc.meme.shop.domain.favorite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.model.entity.Model;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavoriteArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteArtistId;

    //TODO: field fix
    @ManyToOne
//    @MapsId
    @JoinColumn(name="user_id", nullable = false)
    private Model model;

    @ManyToOne
//    @MapsId
    @JoinColumn(name="user_id", nullable = false)
    private Artist artist;
}
