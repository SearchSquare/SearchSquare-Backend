package com.searchsquare.core.interceptor;

import com.searchsquare.core.exception.UnAuthorizedException;
import com.searchsquare.core.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final String HEADER_AUTHORIZATION = "Authorization";

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            log.info("PreFlight 요청");
            return true;
        }
        final String token = request.getHeader(HEADER_AUTHORIZATION);
        if (token != null && jwtUtil.checkToken(token)) {
            log.info("토큰 사용 가능 : {}", token);
            return true;
        } else {
            log.info("토큰 사용 불가능 : {}", token);
            throw new UnAuthorizedException();
        }
    }
}
