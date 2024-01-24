package umc.meme.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.entity.enums.*;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.portfolio.entity.enums.Category;

import java.util.List;

@SpringBootTest
public class ArtistTest {

    private ArtistRepository artistRepository;

    @Test
    void createArtist(){

        List<Category> categories = List.of(new Category[]{Category.DAILY, Category.PARTY});
        List<Region> regions = List.of(new Region[]{Region.DONGJAK, Region.GANGNAM});
        Artist artist1 = Artist.builder()
                .availableTime(AvailableTime.FRI)
                .gender(Gender.FEMALE)
                .specialization(categories)
                .email("")
                .name("testName")
                .nickname("testNickName")
                .makeupLocation(MakeupLocation.SHOP)
                .region(regions)
                .introduction("Artist1")
                .profileImg("")
                .workExperience(WorkExperience.EIGHT)
                .build();
        artistRepository.save(artist1);

        List<Category> categories2 = List.of(new Category[]{Category.STUDIO, Category.ACTOR});
        List<Region> regions2 = List.of(new Region[]{Region.GURO, Region.GANGNAM});
        Artist artist2 = Artist.builder()
                .availableTime(AvailableTime.THU)
                .gender(Gender.FEMALE)
                .profileImg("")
                .specialization(categories)
                .email("")
                .name("test2Name")
                .nickname("test2NickName")
                .makeupLocation(MakeupLocation.SHOP)
                .region(regions)
                .introduction("Artist2")
                .workExperience(WorkExperience.FIVE)
                .build();
        artistRepository.save(artist2);

        List<Category> categorie3 = List.of(new Category[]{Category.WEDDING, Category.PROSTHETIC});
        List<Region> regions3 = List.of(new Region[]{Region.DONGDAEMUN, Region.SONGPA});
        Artist artist3 = Artist.builder()
                .profileImg("")
                .availableTime(AvailableTime.MON)
                .gender(Gender.FEMALE)
                .specialization(categories)
                .email("")
                .name("test3Name")
                .nickname("test3NickName")
                .makeupLocation(MakeupLocation.SHOP)
                .region(regions)
                .introduction("Artist3")
                .workExperience(WorkExperience.ONE)
                .build();
        artistRepository.save(artist3);

    }
}
