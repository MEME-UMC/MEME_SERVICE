package umc.meme.shop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    /**
     *  Error Code
     *  400 : 잘못된 요청
     *  401 : JWT에 대한 오류
     *  403 : 요청한 정보에 대한 권한 없음.
     *  404 : 존재하지 않는 정보에 대한 요청.
     */

    /**
     * TEMP
     */
    TEMP(400, "TEMP"),

    /**
     * Code : 400
     * Bad Request
     */
    INVALID_REQUEST(400,  "유효하지 않은 요청입니다."),
    ALREADY_EXIST_FAVORITE_ARTIST(400, "해당 아티스트는 이미 관심 아티스트로 등록되어있습니다."),
    ALREADY_EXIST_FAVORITE_PORTFOLIO(400, "해당 포트폴리오는 이미 관심 포트폴리오로 등록되어있습니다."),

    /**
     * Code : 404
     * Not Found
     */
    NOT_EXIST_USER(404, "존재하지 않는 유저입니다."),
    NOT_EXIST_PORTFOLIO(404, "존재하지 않는 포트폴리오입니다."),
    NOT_EXIST_RESERVATION(404, "존재하지 않는 예약입니다."),
    PAGE_NOT_FOUND(404,"페이지를 찾을 수 없습니다");


    private final int code;
    private final String message;
}
