package com.searchsquare.core.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // 1000 - 인증
    EXPIRED_JWT_EXCEPTION("1000", "토큰이 만료되었습니다."),
    UNAUTHORIZED_EXCEPTION("1001", "토큰이 없거나 인증 과정에서 오류가 발생했습니다."),
    UNSUPPORTED_JWT_EXCEPTION("1002", "토큰 발급자가 일치하지 않습니다."),

    // 2000 - 성공
    OK("2000", "성공");


    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
