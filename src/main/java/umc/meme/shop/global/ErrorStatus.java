package umc.meme.shop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    TEMP(500, "TEMP"),

    /**model**/
    MODEL_NOT_FOUND(500, "모델을 찾을 수 없습니다");

    private final int code;
    private final String message;
}
