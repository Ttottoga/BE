package com.umc.ttg.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BaseException {
    private final int code;
    private final String message;
}
