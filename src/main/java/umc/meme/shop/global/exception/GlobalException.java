package umc.meme.shop.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.meme.shop.global.ErrorStatus;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private final ErrorStatus errorStatus;
}
