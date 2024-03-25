package umc.meme.shop.domain.portfolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.meme.shop.domain.portfolio.dto.request.CreatePortfolioDto;
import umc.meme.shop.domain.portfolio.dto.request.UpdatePortfolioDto;
import umc.meme.shop.domain.portfolio.service.PortfolioService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @Operation(summary = "포트폴리오 생성", description = "포트폴리오를 생성하는 API입니다.")
    @PostMapping()
    public ApiResponse createPortfolio(@RequestBody CreatePortfolioDto portfolioDto){
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_CREATE, portfolioService.createPortfolio(portfolioDto));
    }

    @Operation(summary = "포트폴리오 전체 조회", description = "포트폴리오 전체를 조회하는 API입니다.")
    @GetMapping("/{artistId}")
    public ApiResponse getPortfolio(@PathVariable(name = "artistId") Long artistId,
                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page
                                    ){
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_GET, portfolioService.getPortfolio(artistId, page));
    }

    @Operation(summary = "포트폴리오 조회", description = "특정 포트폴리오를 조회하는 API입니다.")
    @GetMapping("/details/{userId}/{portfolioId}")
    public ApiResponse getPortfolioDetails(@PathVariable(name = "portfolioId") Long userId, @PathVariable Long portfolioId) {
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_GET, portfolioService.getPortfolioDetails(userId, portfolioId));
    }

    @Operation(summary = "포트폴리오 수정/삭제", description = "포트폴리오를 수정/삭제하는 API입니다.")
    @PatchMapping()
    public ApiResponse updatePortfolio(@RequestBody UpdatePortfolioDto portfolioDto){
        // TODO: PortfolioImg 추가
        portfolioService.updatePortfolio(portfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_UPDATE);
    }

    /**recommend**/
    @Operation(summary = "포트폴리오 추천 - 리뷰 순", description = "리뷰가 많은 순으로 포트폴리오를 추천하는 API입니다.")
    @GetMapping("/recommend/review")
    public ApiResponse recommendReview(){
        return ApiResponse.SuccessResponse(SuccessStatus.RECOMMEND_REVIEW_GET, portfolioService.recommendReview());
    }

    @Operation(summary = "포트폴리오 추천 - 최신 순", description = "최근 등록된 순으로 포트폴리오를 추천하는 API입니다.")
    @GetMapping("/recommend/recent")
    public ApiResponse recommendRecent(){
        return ApiResponse.SuccessResponse(SuccessStatus.RECOMMEND_RECENT_GET, portfolioService.recommendRecent());
    }
}
