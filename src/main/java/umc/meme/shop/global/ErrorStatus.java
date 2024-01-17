package umc.meme.shop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    TEMP(500, "TEMP");

    private final int code;
    private final String message;
}
