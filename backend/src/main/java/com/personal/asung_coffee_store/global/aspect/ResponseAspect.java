package com.personal.asung_coffee_store.global.aspect;

import com.personal.asung_coffee_store.global.dto.RsData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ResponseAspect {
    private final HttpServletResponse response;

    @Around("execution(* com.personal.asung_coffee_store..*RestController.*(..))")
    public Object responseAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof RsData<?> rsData) {
            int statusCode = rsData.getStatusCode();
            response.setStatus(statusCode);
        }

        return result;
    }
}
