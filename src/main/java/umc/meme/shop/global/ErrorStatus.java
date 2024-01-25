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

    //review
    ALREADY_REVIEWED(400, "이미 리뷰 작성이 완료된 예약입니다."),
    INVALID_REVIEW_REQUEST(400, "예약이 완료되지 않아 리뷰를 작성할 수 없습니다."),

    //reservation
    ALREADY_CHANGE_STATUS(400, "이미 예약 상태가 변경되었습니다."),
    INVALID_CHANGE_STATUS(400, "이미 완료된 예약은 취소할 수 없습니다."),


    /**
     * Code : 404
     * Not Found
     */
    NOT_EXIST_MODEL(404, "존재하지 않는 모델입니다."),
    NOT_EXIST_ARTIST(404, "존재하지 않는 아티스트입니다."),
    NOT_EXIST_PORTFOLIO(404, "존재하지 않는 포트폴리오입니다."),
    NOT_EXIST_RESERVATION(404, "존재하지 않는 예약입니다."),
    NOT_EXIST_FAVORITE_ARTIST(404, "존재하지 않는 관심 아티스트입니다."),
    NOT_EXIST_FAVORITE_PORTFOLIO(404, "존재하지 않는 관심 메이크업입니다."),
    PAGE_NOT_FOUND(404,"페이지를 찾을 수 없습니다");


    private final int code;
    private final String message;
}
