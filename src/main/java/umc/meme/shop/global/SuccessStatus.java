package umc.meme.shop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus {
    TEMP(200, "TEMP");

    private final int code;
    private final String message;
}
