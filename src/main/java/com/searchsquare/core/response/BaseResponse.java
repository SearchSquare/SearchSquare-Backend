package com.searchsquare.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseResponse<E> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private final String code;
    @JsonProperty(value = "isSuccess")
    private final boolean success;
    private final String message;
    private final E response;

    @Builder
    private BaseResponse(String code, String message, boolean success, E response) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.response = response;
    }

    /**
     * 반환 데이터가 없는 성공 메시지 템플릿
     *
     * @param code    상태코드
     * @param message 응답 메시지
     * @param <E>     null
     * @return 자기 자신을 반환
     */
    public static <E> BaseResponse<E> ofSuccess() {
        return BaseResponse.<E>builder()
            .code(ResponseCode.OK.getCode())
            .message(ResponseCode.OK.getMessage())
            .success(true)
            .response(null)
            .build();
    }

    /**
     * 반환 데이터가 있는 성공 메시지 템플릿
     *
     * @param code    상태 코드
     * @param message 응답 메시지
     * @param data    반환 데이터
     * @param <E>     반환되는 객체
     * @return 자기 자신을 반환
     */
    public static <E> BaseResponse<E> ofSuccess(E response) {
        return BaseResponse.<E>builder()
            .code(ResponseCode.OK.getCode())
            .message(ResponseCode.OK.getMessage())
            .success(true)
            .response(response)
            .build();
    }

    /**
     * 예외를 반환하는 실패 메시지 템플릿
     *
     * @param code    상태코드
     * @param message 응답 메시지
     * @param <E>     null
     * @return 자기 자신을 반환
     */
    public static <E> BaseResponse<E> ofFail(ResponseCode responseCode) {
        return BaseResponse.<E>builder()
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .success(false)
            .response(null)
            .build();
    }
}
