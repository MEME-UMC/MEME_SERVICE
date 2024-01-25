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
    @PostMapping("/{artistId}")
    public ApiResponse createPortfolio(@PathVariable Long artistId, @RequestBody CreatePortfolioDto portfolioDto){
        // TODO: PortfolioImg 추가
        portfolioService.createPortfolio(artistId, portfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_CREATE);
    }

    @Operation(summary = "포트폴리오 조회", description = "포트폴리오를 조회하는 API입니다.")
    @GetMapping("/{artistId}")
    public ApiResponse getPortfolio(@PathVariable Long artistId){
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_GET, portfolioService.getPortfolio(artistId));
    }

    @Operation(summary = "포트폴리오 수정/삭제", description = "포트폴리오를 수정/삭제하는 API입니다.")
    @PatchMapping("/{artistId}/{portfolioId}")
    public ApiResponse updatePortfolio(@PathVariable Long portfolioId, @RequestBody UpdatePortfolioDto portfolioDto){
        // TODO: PortfolioImg 추가
        portfolioService.updatePortfolio(portfolioId, portfolioDto);
        return ApiResponse.SuccessResponse(SuccessStatus.PORTFOLIO_UPDATE);
    }
}
