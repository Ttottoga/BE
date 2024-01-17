package com.umc.ttg.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseErrorResponse {

    private final int code;
    private final String message;

    public BaseErrorResponse(BaseException baseException) {
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }

}
