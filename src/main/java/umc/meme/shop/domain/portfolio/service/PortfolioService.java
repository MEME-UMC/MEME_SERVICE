package umc.meme.shop.domain.portfolio.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.artist.repository.ArtistRepository;
import umc.meme.shop.domain.favorite.entity.FavoritePortfolio;
import umc.meme.shop.domain.favorite.repository.FavoritePortfolioRepository;
import umc.meme.shop.domain.model.entity.Model;
import umc.meme.shop.domain.model.repository.ModelRepository;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioDetailDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioImgDto;
import umc.meme.shop.domain.portfolio.dto.response.PortfolioPageDto;
import umc.meme.shop.domain.portfolio.dto.response.SimplePortfolioDto;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.domain.portfolio.entity.PortfolioImg;
import umc.meme.shop.domain.portfolio.repository.PortfolioImgRepository;
import umc.meme.shop.domain.portfolio.repository.PortfolioRepository;
import umc.meme.shop.global.ErrorStatus;
import umc.meme.shop.global.exception.GlobalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final ArtistRepository artistRepository;
    private final PortfolioRepository portfolioRepository;
    private final PortfolioImgRepository portfolioImgRepository;
    private final ModelRepository modelRepository;
    private final FavoritePortfolioRepository favoritePortfolioRepository;

    //포트폴리오 생성
    @Transactional
    public Long createPortfolio(CreatePortfolioDto portfolioDto) {
        Artist artist = artistRepository.findById(portfolioDto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //포트폴리오 이름이 이미 존재할 시
        if (portfolioRepository.existsByMakeupName(portfolioDto.getMakeupName()))
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_PORTFOLIO);

        // 포트폴리오 이미지 리스트 생성
        List<PortfolioImg> portfolioImgList = portfolioDto.getPortfolioImgSrc().stream()
                .map(PortfolioImg::from)
                .toList();

        // 포트폴리오 entity 생성
        Portfolio portfolio = Portfolio.from(artist, portfolioDto);

        // 포트폴리오 이미지, 포트폴리오 연관관계 설정
        portfolioImgList.forEach(portfolio::addPortfolioImg);

        // 포트폴리오 연관관계 설정
        artist.updatePortfolioList(portfolio);
        portfolioRepository.save(portfolio);
        return portfolio.getPortfolioId();
    }

    // 포트폴리오 전체 조회
    @Transactional
    public PortfolioPageDto getPortfolio(Long artistId, int page) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        List<Portfolio> portfolioList = artist.getPortfolioList();

        //isblock이면 리스트에서 제거
        portfolioList.removeIf(Portfolio::isBlock);

        //list를 page로 변환
        Page<Portfolio> portfolioPage = getPage(page, portfolioList);

        return PortfolioPageDto.from(portfolioPage);
    }

    // 포트폴리오 하나만 조회
    public PortfolioDetailDto getPortfolioDetails(Long userId, Long portfolioId) {
        Model model = modelRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_MODEL));
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        if (portfolio.isBlock())
            throw new GlobalException(ErrorStatus.BLOCKED_PORTFOLIO);

        boolean isFavorite = false;
        Optional<FavoritePortfolio> favoritePortfolio = favoritePortfolioRepository.findByModelAndPortfolio(model, portfolio);
        if (favoritePortfolio.isPresent())
            isFavorite = true;

        return PortfolioDetailDto.from(portfolio, isFavorite);
    }

    // 포트폴리오 수정/삭제
    @Transactional
    public void updatePortfolio(UpdatePortfolioDto updatePortfolioDto) {
        Artist artist = artistRepository.findById(updatePortfolioDto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        Portfolio portfolio = portfolioRepository.findById(updatePortfolioDto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        if (portfolio.isBlock() && updatePortfolioDto.getIsBlock())
            throw new GlobalException(ErrorStatus.BLOCKED_PORTFOLIO);

        if (!portfolio.getArtist().equals(artist))
            throw new GlobalException(ErrorStatus.NOT_AUTHORIZED_PORTFOLIO);

        // 포트폴리오 이미지 수정
        if (!updatePortfolioDto.getPortfolioImgSrcList().isEmpty())
            updatePortfolioImgList(portfolio, updatePortfolioDto.getPortfolioImgSrcList());

        // 포트폴리오 수정
        portfolio.updatePortfolio(updatePortfolioDto);
    }

    @Transactional
    public void updatePortfolioImgList(Portfolio portfolio, List<String> portfolioImgDtoList) {
        List<PortfolioImg> updatedPortfolioImgList = new ArrayList<>();

        for (String portfolioImgSrc : portfolioImgDtoList) {
            if (portfolioImgSrc == null)
                throw new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO_IMG);

            Optional<PortfolioImg> portfolioImg = portfolioImgRepository.findBySrcAndPortfolio(portfolioImgSrc, portfolio);
            if (portfolioImg.isEmpty()) {
                // 새로운 이미지 추가
                PortfolioImg newPortfolioImg = PortfolioImg.from(portfolioImgSrc);
                newPortfolioImg.setPortfolio(portfolio);
                portfolioImgRepository.save(newPortfolioImg);
                updatedPortfolioImgList.add(newPortfolioImg);
            } else {
                // 기존 이미지 보존
                updatedPortfolioImgList.add(portfolioImg.get());
            }
        }

        // 기존 리뷰 이미지 리스트와 새로운 리뷰 이미지 리스트 비교
        List<PortfolioImg> existedPortfolioImgList = portfolio.getPortfolioImgList();
        for (PortfolioImg portfolioImg : existedPortfolioImgList) {
            if (!updatedPortfolioImgList.contains(portfolioImg)) {
                // 이미지 삭제
                portfolioImgRepository.delete(portfolioImg);
            }
        }

        // 포트폴리오 이미지 리스트 - 포트폴리오 연관관계 설정
        portfolio.updatePortfolioImgList(updatedPortfolioImgList);
    }

    /**
     * recommend
     **/
    //리뷰 많은 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendReview() {
        Pageable pageable = setPageRequest(0, "review");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    //최신 등록 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendRecent() {
        Pageable pageable = setPageRequest(0, "recent");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    private Pageable setPageRequest(int page, String sortBy) {

        Sort sort = switch (sortBy) {
            case "desc" -> Sort.by("price").descending();
            case "asc" -> Sort.by("price").ascending();
            case "review" -> Sort.by("averageStars").descending();
            case "recent" -> Sort.by("createdAt").descending();
            default -> throw new GlobalException(ErrorStatus.INVALID_SORT_CRITERIA);
        };

        //별점 높은 순 정렬 추가
        Sort finalSort = sort.and(Sort.by("averageStars").descending());
        return PageRequest.of(page, 30, finalSort);
    }

    private Page<Portfolio> getPage(int page, List<Portfolio> list) {
        Pageable pageable = PageRequest.of(page, 30);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        //list를 page로 변환
        return new PageImpl<>(list.subList(start, end),
                pageable, list.size());
    }


}