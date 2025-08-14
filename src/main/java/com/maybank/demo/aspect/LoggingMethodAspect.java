package com.maybank.demo.aspect;

import com.maybank.demo.controller.AccountController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.mysql.cj.conf.PropertyKey.logger;

@Aspect
public class LoggingMethodAspect {

    Logger logger = LoggerFactory.getLogger(LoggingMethodAspect.class);


   @Around(
            "execution(* com.maybank.demo.service..*(..)) || execution(* com.maybank.demo.controller..*(..))"
    )
    public Object log ( ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();

        Object [] arguments = joinPoint.getArgs();

        logger.info("------- "+methodName + " started--------- ");
        logger.info("------- Parameters :"+ Arrays.asList(arguments) );


       Object result = joinPoint.proceed();

        logger.info("------- "+methodName + " completed------ ");

      return result;
    }
}
