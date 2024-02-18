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
    public Long createPortfolio(CreatePortfolioDto dto) {
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        //포트폴리오 이름이 이미 존재할 시
        if(portfolioRepository.existsByMakeupName(dto.getMakeupName()))
            throw new GlobalException(ErrorStatus.ALREADY_EXIST_PORTFOLIO);

        List<PortfolioImg> portfolioImgList = dto.getPortfolioImgSrc().stream()
                .map(PortfolioImg::new)
                .toList();

        Portfolio portfolio = Portfolio.from(artist, dto);
        portfolioImgList.forEach(portfolio::addPortfolioImg);

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

        if(portfolio.isBlock())
            throw new GlobalException(ErrorStatus.BLOCKED_PORTFOLIO);

        boolean isFavorite = false;
        Optional<FavoritePortfolio> favoritePortfolio = favoritePortfolioRepository.findByModelAndPortfolio(model, portfolio);
        if(favoritePortfolio.isPresent())
            isFavorite = true;

        return PortfolioDetailDto.from(portfolio, isFavorite);
    }

    // 포트폴리오 수정/삭제
    @Transactional
    public void updatePortfolio(UpdatePortfolioDto dto) {
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_ARTIST));

        Portfolio portfolio = portfolioRepository.findById(dto.getPortfolioId())
                .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO));

        if(portfolio.isBlock() && dto.getIsBlock())
            throw new GlobalException(ErrorStatus.BLOCKED_PORTFOLIO);

        if (!portfolio.getArtist().equals(artist)) {
            throw new GlobalException(ErrorStatus.NOT_AUTHORIZED_PORTFOLIO);
        }

        if(!dto.getPortfolioImgList().isEmpty())
            updatePortfolioImg(portfolio, dto.getPortfolioImgList()); // 수정

        portfolio.updatePortfolio(dto);
    }

    private void updatePortfolioImg(Portfolio portfolio, List<PortfolioImgDto> portfolioImgDtoList) {
        List<PortfolioImg> updatedPortfolioImgs = new ArrayList<>();

        for (PortfolioImgDto portfolioImgDto : portfolioImgDtoList) {
            PortfolioImg portfolioImg = portfolioImgRepository.findById(portfolioImgDto.getPortfolioImgId())
                    .orElseThrow(() -> new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO_IMG));

            if (!portfolio.getPortfolioImgList().contains(portfolioImg)) {
                throw new GlobalException(ErrorStatus.NOT_EXIST_PORTFOLIO_IMG);
            }

            if (portfolioImgDto.isDelete()) {
                // 이미지 삭제
                portfolio.getPortfolioImgList().remove(portfolioImg);
                portfolioImgRepository.delete(portfolioImg);
            } else if (portfolioImgDto.getPortfolioImgSrc() != null) {
                // 이미지 수정 (src 업데이트)
                portfolioImg.updateSrc(portfolioImgDto.getPortfolioImgSrc());
                portfolioImgRepository.save(portfolioImg);

                // 업데이트된 이미지 정보를 리스트에 추가
                updatedPortfolioImgs.add(portfolioImg);
            }
        }

        // 업데이트된 이미지 리스트를 포트폴리오의 이미지 리스트에 반영
        portfolio.getPortfolioImgList().removeAll(updatedPortfolioImgs);
        portfolio.getPortfolioImgList().addAll(updatedPortfolioImgs);
    }

    /**recommend**/
    //리뷰 많은 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendReview(){
        Pageable pageable = setPageRequest(0, "review");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    //최신 등록 순 포트폴리오 추천
    public List<SimplePortfolioDto> recommendRecent(){
        Pageable pageable = setPageRequest(0, "recent");
        Page<Portfolio> portfolioList = portfolioRepository.findAllNotBlocked(pageable);

        return portfolioList.getContent().stream()
                .map(SimplePortfolioDto::from)
                .toList();
    }

    private Pageable setPageRequest(int page, String sortBy){

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

    private Page<Portfolio> getPage(int page, List<Portfolio> list){
        Pageable pageable = PageRequest.of(page, 30);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        //list를 page로 변환
        return new PageImpl<>(list.subList(start, end),
                pageable, list.size());
    }


}