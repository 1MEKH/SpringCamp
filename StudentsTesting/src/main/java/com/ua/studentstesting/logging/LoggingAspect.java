package com.ua.studentstesting.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("within(com.ua.studentstesting.service..*)")
    public void loggerBefore(JoinPoint joinPoint){
        System.out.println("Class :: " + joinPoint.getTarget().getClass().getName());
        System.out.println("Method:: " + joinPoint.getSignature().getName());
    }
}
