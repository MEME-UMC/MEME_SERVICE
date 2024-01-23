package umc.meme.shop.global.exception;

import umc.meme.shop.global.ErrorStatus;

public class GlobalExceptionHandler extends GlobalException{
    public GlobalExceptionHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
