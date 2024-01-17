package com.umc.ttg.global.common;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private final int code;
    private final String message;
    private T result;

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
