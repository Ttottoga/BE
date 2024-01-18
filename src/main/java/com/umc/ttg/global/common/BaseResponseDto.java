package com.umc.ttg.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponseDto<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static <T> BaseResponseDto<T> onSuccess(T data) {
        return new BaseResponseDto<>(true, "요청에 성공하였습니다.", "1000", data);
    }

    public static <T> BaseResponseDto<T> onFailure(ResponseCode code, T data) {
        return new BaseResponseDto<>(false, code.getCode(), code.getMessage(), data);
    }
}
