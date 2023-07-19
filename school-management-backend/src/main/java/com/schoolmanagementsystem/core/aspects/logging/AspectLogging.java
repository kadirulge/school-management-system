package com.schoolmanagementsystem.core.aspects.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectLogging {

    @Before("execution(* com.schoolmanagementsystem.service.concretes.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature()+" is invoked");
    }

    @After("execution(* com.schoolmanagementsystem.service.concretes.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature()+" is completed");
    }

}
