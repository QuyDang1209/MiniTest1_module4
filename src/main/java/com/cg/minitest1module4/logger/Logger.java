package com.cg.minitest1module4.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Arrays;

@Aspect
@Component

public class Logger {
    @AfterThrowing(pointcut = "execution ( public * com.cg.minitest1module4.service.impl.ComputerService.findAll(..))",throwing = "e")
    public void logclass(JoinPoint joinPoint, Exception e){
        System.out.println();
    }

    @AfterThrowing(pointcut = "execution(public * com.cg.minitest1module4.service.impl.ComputerService.*(..))", throwing = "e")
    public void logClass(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] co loi xay ra: %s.%s%s: %s%n", className, method, args, e.getMessage());
    }
}
