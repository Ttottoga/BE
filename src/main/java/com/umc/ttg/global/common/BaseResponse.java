package com.umc.ttg.global.common;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    // 필수로 들어가야 하는 요소들은 final 붙임
    private final int code;
    private final String message;
    private T result; // 응답에 데이터가 포함 안될 수도 있으므로 final 뺌

    // code, message, data 모두 넘겨주는 Response
    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }

    // code, message 만 넘겨주는 Response
    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
