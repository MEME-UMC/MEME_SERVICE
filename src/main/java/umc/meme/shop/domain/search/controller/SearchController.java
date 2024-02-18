package umc.meme.shop.domain.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.meme.shop.domain.search.service.SearchService;
import umc.meme.shop.global.SuccessStatus;
import umc.meme.shop.global.enums.Category;
import umc.meme.shop.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "메이크업 검색", description = "메이크업을 검색/최근 검색어로 검색하는 API입니다.")
    @GetMapping("")
    public ApiResponse search(@RequestParam(value = "query") String query,
                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(value = "sort", defaultValue = "desc") String sort){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, searchService.search(query, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 관심 아티스트", description = "관심 아티스트로 검색하는 API입니다.")
    @GetMapping("/artist")
    public ApiResponse searchArtist(@RequestParam(value = "artistId") Long artistId,
                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(value = "sort", defaultValue = "desc") String sort
    ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, searchService.searchArtist(artistId, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 카테고리", description = "메이크업 카테고리로 검색하는 API입니다.")
    @GetMapping("/category")
    public ApiResponse searchCategory(@RequestParam(value = "category") Category category,
                                      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                      @RequestParam(value = "sort", defaultValue = "desc") String sort
    ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, searchService.searchCategory(category, page, sort));
    }

    @Operation(summary = "메이크업 검색 - 전체", description = "메이크업 전체를 검색하는 API입니다.")
    @GetMapping("/all")
    public ApiResponse searchAll( @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort
    ){
        return ApiResponse.SuccessResponse(SuccessStatus.SEARCH_GET, searchService.searchAll(page, sort));
    }

}
