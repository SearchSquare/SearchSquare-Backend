package com.searchsquare.core.exception.handler;

import static com.searchsquare.core.response.ResponseCode.EXPIRED_JWT_EXCEPTION;
import static com.searchsquare.core.response.ResponseCode.UNAUTHORIZED_EXCEPTION;
import static com.searchsquare.core.response.ResponseCode.UNSUPPORTED_JWT_EXCEPTION;

import com.searchsquare.core.exception.ExpiredJwtException;
import com.searchsquare.core.exception.UnAuthorizedException;
import com.searchsquare.core.exception.UnsupportedJwtException;
import com.searchsquare.core.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<BaseResponse<Object>> expiredJwtException(ExpiredJwtException e) {
        log.info("ExpiredJwtException 발생", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(BaseResponse.ofFail(EXPIRED_JWT_EXCEPTION));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<BaseResponse<Object>> unAuthorizedException(UnAuthorizedException e) {
        log.info("UnAuthorizedException 발생", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(BaseResponse.ofFail(UNAUTHORIZED_EXCEPTION));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> illegalArgumentException(
        IllegalArgumentException e) {
        log.info("IllegalArgumentException 발생", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(BaseResponse.ofFail(UNAUTHORIZED_EXCEPTION));
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<BaseResponse<Object>> unsupportedJwtException(
        UnsupportedJwtException e) {
        log.info("UnsupportedJwtException 발생", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(BaseResponse.ofFail(UNSUPPORTED_JWT_EXCEPTION));
    }
}
