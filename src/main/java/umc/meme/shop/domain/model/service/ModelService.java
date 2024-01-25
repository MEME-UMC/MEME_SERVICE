package umc.meme.shop.domain.model.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.dto.response.ArtistDto;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.favorite.entity.FavoriteArtist;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.favorite.repository.FavoriteArtistRepository;
import umc.meme.shop.domain.favorite.repository.FavoritePortfolioRepository;
import umc.meme.shop.domain.model.dto.request.ModelProfileDto;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.mypage.dto.response.MypageDetailDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final ArtistRepository artistRepository;
    private final FavoriteArtistRepository favoriteArtistRepository;
    private final FavoritePortfolioRepository favoritePortfolioRepository;
    private final PortfolioRepository portfolioRepository;


    //모델 프로필 관리
    @Transactional
    public void updateModelProfile(Long modelId, ModelProfileDto request){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        model.updateModel(request);
    }

    @Transactional
    public MypageDetailDto getModelProfile(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        return MypageDetailDto.builder()
                .profileImg(model.getProfileImg())
                .nickname(model.getNickname())
                .name(model.getName())
                .gender(model.getGender())
                .email(model.getEmail())
                .build();
    }

    //관심 아티스트 조회
    @Transactional
    public List<ArtistDto> getFavoriteArtist(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        List<FavoriteArtist> favoriteArtistList = model.getFavoriteArtistList();
        return favoriteArtistList.stream()
                .map(ArtistDto::from)
                .collect(Collectors.toList());
    }

    //관심 메이크업 조회
    @Transactional
    public List<PortfolioDto> getFavoritePortfolio(Long modelId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        List<FavoritePortfolio> favoritePortfolioList = model.getFavoritePortfolioList();
        return favoritePortfolioList.stream()
                .map(PortfolioDto::from)
                .collect(Collectors.toList());
    }

    //관심 아티스트 추가
    @Transactional
    public void addFavoriteArtist(Long modelId, Long artistId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //이미 관심 아티스트가 존재하는 경우
        if (favoriteArtistRepository.existsByModelAndArtist(model, artist)) {
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_FAVORITE_ARTIST);
        }

        FavoriteArtist favoriteArtist = FavoriteArtist.builder()
                        .artist(artist)
                        .model(model)
                        .build();
        model.updateFavoriteArtistList(favoriteArtist);
        favoriteArtistRepository.save(favoriteArtist);
    }

    //관심 메이크업 추가
    @Transactional
    public void addFavoritePortfolio(Long modelId, Long portfolioId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        //이미 관심 포트폴리오가 존재하는 경우
        if (favoritePortfolioRepository.existsByModelAndPortfolio(model,portfolio)) {
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_FAVORITE_PORTFOLIO);
        }

        FavoritePortfolio favoritePortfolio = FavoritePortfolio.builder()
                .model(model)
                .portfolio(portfolio)
                .build();
        model.updateFavoritePortfolioList(favoritePortfolio);
        favoritePortfolioRepository.save(favoritePortfolio);
    }

    //관심 아티스트 삭제
    @Transactional
    public void deleteFavoriteArtist(Long modelId, Long artistId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //        FavoriteArtist favoriteArtist = favoriteArtistRepository.findById(favoriteArtistId)
        FavoriteArtist favoriteArtist = favoriteArtistRepository.findByModelAndArtist(model, artist)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_FAVORITE_ARTIST));
        favoriteArtistRepository.delete(favoriteArtist);
    }

    //관심 메이크업 삭제
    @Transactional
    public void deleteFavoritePortfolio(Long modelId, Long portfolioId){
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        FavoritePortfolio favoritePortfolio = favoritePortfolioRepository.findByModelAndPortfolio(model, portfolio)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_FAVORITE_PORTFOLIO));
        favoritePortfolioRepository.delete(favoritePortfolio);
    }


}
