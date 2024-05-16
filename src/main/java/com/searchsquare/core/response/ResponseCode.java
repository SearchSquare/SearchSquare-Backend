package com.searchsquare.core.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // 2000 - 성공
    OK("2000", "성공");

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
