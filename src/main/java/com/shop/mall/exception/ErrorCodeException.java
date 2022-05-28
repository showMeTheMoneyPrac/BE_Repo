package com.shop.mall.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorCodeException extends RuntimeException{
    private final ErrorCode errorCode;

    public ErrorCodeException(String s, String message, ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
