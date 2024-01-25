package umc.meme.shop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    TEMP(200, "TEMP"),

    /**artist**/
    ARTIST_PROFILE_UPDATE(200, "아티스트 프로필 수정이 완료되었습니다"),

    PORTFOLIO_CREATE(200, "포트폴리오 생성이 완료되었습니다"),
    PORTFOLIO_GET(200, "포트폴리오 조회가 완료되었습니다"),
    PORTFOLIO_UPDATE(200, "포트폴리오 수정이 완료되었습니다"),

    /**model**/
    MODEL_PROFILE_UPDATE(200, "모델 프로필 수정이 완료되었습니다"),

    FAVORITE_ARTIST_GET(200, "관심 아티스트 조회가 완료되었습니다"),
    FAVORITE_PORTFOLIO_GET(200, "관심 메이크업 조회가 완료되었습니다"),
    FAVORITE_ARTIST_POST(200, "관심 아티스트 추가가 완료되었습니다."),
    FAVORITE_PORTFOLIO_POST(200, "관심 메이크업 추가가 완료되었습니다."),
    FAVORITE_ARTIST_DELETE(200, "관심 아티스트 삭제가 완료되었습니다."),
    FAVORITE_PORTFOLIO_DELETE(200, "관심 메이크업 삭제가 완료되었습니다."),


    SEARCH_GET(200, "조회가 완료되었습니다"),

    /**review**/
    REVIEW_CREATE(200, "리뷰 작성이 완료되었습니다"),
    REVIEW_GET(200, "리뷰 조회가 완료되었습니다"),
    REVIEW_UPDATE(200, "리뷰 관리가 완료되었습니다"),

    /**reservation**/
    RESERVATION_CREATE(200, "예약이 완료되었습니다"),
    RESERVATION_GET(200, "예약 조회가 완료되었습니다"),
    RESERVATION_UPDATE(200, "예약 상태 변경이 완료되었습니다"),

    /**mypage**/
    MYPAGE_GET(200, "마이페이지 조회가 완료되었습니다"),
    DETAILS_GET(200, "내 정보 조회가 완료되었습니다"),
    TOS_GET(200, "약관 및 정책 조회가 완료되었습니다"),
    CONTACT_CREATE(200, "문의 작성이 완료되었습니다");


    private final int code;
    private final String message;
}
