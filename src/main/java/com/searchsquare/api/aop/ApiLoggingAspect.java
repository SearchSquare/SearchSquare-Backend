package com.searchsquare.api.aop;

import com.searchsquare.api.repository.ApiLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class ApiLoggingAspect {

    private final ApiLogRepository apiLogRepository;

    @AfterReturning("@annotation(com.searchsquare.api.aop.ApiLogging)")
    public void logAfterReturning(JoinPoint jp) {
        String serviceKey = String.valueOf(jp.getArgs()[0]);
        apiLogRepository.save(serviceKey);
        log.info("[API Call] method signature {}", jp.getSignature());
    }
}