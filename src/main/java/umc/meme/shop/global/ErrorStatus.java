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
     * Code : 403
     * Not Authorized
     */

    //portfoilo
    NOT_AUTHORIZED_PORTFOLIO(403, "아티스트가 해당 포트폴리오에 수정 권한이 없습니다."),



    /**
     * Code : 400
     * Bad Request
     */
    INVALID_REQUEST(400,  "유효하지 않은 요청입니다."),
    ALREADY_EXIST_FAVORITE_ARTIST(400, "해당 아티스트는 이미 관심 아티스트로 등록되어있습니다."),
    ALREADY_EXIST_FAVORITE_PORTFOLIO(400, "해당 포트폴리오는 이미 관심 포트폴리오로 등록되어있습니다."),

    //portfolio
    ALREADY_EXIST_PORTFOLIO(400, "해당 포트폴리오 제목이 이미 존재합니다"),
    INVALID_SORT_CRITERIA(400, "잘못된 정렬 기준입니다"),
    BLOCKED_PORTFOLIO(400, "숨김 처리된 포트폴리오입니다"),
    NOT_EXIST_PORTFOLIO_IMG(400, "포트폴리오 이미지를 찾을 수 없습니다."),

    //review
    ALREADY_REVIEWED(400, "이미 리뷰 작성이 완료된 예약입니다."),
    INVALID_REVIEW_REQUEST(400, "예약이 완료되지 않아 리뷰를 작성할 수 없습니다."),
    INVALID_MODEL_FOR_REVIEW(400,  "사용자가 작성하지 않은 리뷰입니다."),

    //reservation
    INVAILD_AVAILABLE_TIME(400, "잘못된 예약 시간입니다."),
    ALREADY_CHANGE_STATUS(400, "이미 예약 상태가 변경되었습니다."),
    INVALID_CHANGE_STATUS(400, "이미 완료된 예약은 취소할 수 없습니다."),
    NOT_ALLOW_DUPLICATED_RESERVATION(400, "중복된 예약 시간입니다."),

    NOT_ALLOW_OVER_ONE_RESERVATION(400,"한 번에 하나의 예약만 가능합니다." ),
    INVALID_CHANGE_COMPLETE(400,"취소된 예약을 완료 상태로 변경할 수 없습니다." ),


    /**
     * Code : 404
     * Not Found
     */
    NOT_EXIST_USER(404, "존재하지 않는 유저입니다."),
    NOT_EXIST_MODEL(404, "존재하지 않는 모델입니다."),
    NOT_EXIST_ARTIST(404, "존재하지 않는 아티스트입니다."),
    NOT_EXIST_PORTFOLIO(404, "존재하지 않는 포트폴리오입니다."),
    NOT_EXIST_RESERVATION(404, "존재하지 않는 예약입니다."),
    NOT_EXIST_FAVORITE_ARTIST(404, "존재하지 않는 관심 아티스트입니다."),
    NOT_EXIST_FAVORITE_PORTFOLIO(404, "존재하지 않는 관심 메이크업입니다."),
    NOT_EXIST_REVIEW(404, "존재하지 않는 리뷰입니다."),
    NOT_EXIST_REVIEW_IMG(404, "존재하지 않는 리뷰 이미지입니다."),
    NOT_EXIST_INQUIRY(404, "존재하지 않는 문의입니다."),
    SEARCH_NOT_FOUNT(404, "검색 결과가 존재하지 않습니다."),
    PAGE_NOT_FOUND(404, "페이지를 찾을 수 없습니다");


    private final int code;
    private final String message;
}
