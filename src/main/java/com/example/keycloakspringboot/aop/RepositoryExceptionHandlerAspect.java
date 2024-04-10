package com.example.keycloakspringboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RepositoryExceptionHandlerAspect {

    @Around("execution(* com.example.keycloakspringboot.service.ServiceInterface.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("Сработало AOP");
            return joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
