package com.example.internet_market_backend.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * com.example.internet_market_backend.service.*(..))")
    public void allServiceMethods(){}

    @Before("allServiceMethods()")
    public void beforeServiceMethodLoggingAdvice(JoinPoint joinPoint){
        log.info("Method " + joinPoint.getSignature().getName() + " in "
                + joinPoint.getSignature().getDeclaringType().getSimpleName() + " started");
    }

    @AfterThrowing("allServiceMethods()")
    public void afterThrowingServiceMethodLoggingAdvice(JoinPoint joinPoint){
        log.info("Method " + joinPoint.getSignature().getName() + " in "
                + joinPoint.getSignature().getDeclaringType().getSimpleName() + " failed");
    }

    @AfterReturning("allServiceMethods()")
    public void afterReturningServiceMethodLoggingAdvice(JoinPoint joinPoint){
        log.info("Method " + joinPoint.getSignature().getName() + " in "
                + joinPoint.getSignature().getDeclaringType().getSimpleName() + " worked correctly");
    }
}
