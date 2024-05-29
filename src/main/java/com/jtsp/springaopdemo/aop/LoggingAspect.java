package com.jtsp.springaopdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.jtsp.springaopdemo.controller.*.*(..))")
    public Object logControllerExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Execution time of {} with args {} :: {} ms",
                joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), elapsedTime);

        return result;
    }

    @Around("execution(* org.springframework.web.client.RestTemplate.*(..))")
    public Object logRestTemplateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Execution time of RestTemplate method {} with args {} :: {} ms",
                joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), elapsedTime);

        return result;
    }
}
