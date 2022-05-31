package com.mbti.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    //target 메소드의 파라미터 등 정보를 출력
    @Before("execution(* com.mbti..*(..))")
    public void startLog(JoinPoint jp) {
        logger.info("------------------------------------------------");
        logger.info("------------------------------------------------");

        /* 전달되는 모든 파라미터들을 object의 배열로 가져옴 */
        logger.info("1:"+ Arrays.toString(jp.getArgs()));

        //해당 advice의 타입을 알아냄
        logger.info("2:"+jp.getKind());

        //실행하는 대상 객체의 메소드에 대한 정보 알아낼때 사용
        logger.info("3:"+jp.getSignature().getName());

        //target 객체를 알아낼때 사용
        logger.info("4:"+jp.getTarget().toString());

        //advice를 행하는 객체를 알아낼때 사용
        logger.info("5:"+jp.getThis().toString());
    }

    @After("execution(* com.mbti..*(..))")
    public void after(JoinPoint jp) {
        logger.info("------------------------------------------------");
        logger.info("------------------------------------------------");

        /* 전달되는 모든 파라미터들을 object의 배열로 가져옴 */
        logger.info("1:"+Arrays.toString(jp.getArgs()));

        //해당 advice의 타입을 알아냄
        logger.info("2:"+jp.getKind());

        //실행하는 대상 객체의 메소드에 대한 정보 알아낼때 사용
        logger.info("3:"+jp.getSignature().getName());

        //target 객체를 알아낼때 사용
        logger.info("4:"+jp.getTarget().toString());

        //advice를 행하는 객체를 알아낼때 사용
        logger.info("5:"+jp.getThis().toString());
    }

    //target 메소드의 동작 시간을 측정
    @Around("execution(* com.mbti..*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info(Arrays.toString(pjp.getArgs()));

        //실제 타겟을 실행하는 부분. 이 부분이 없으면 advice가 적용된 메소드가 동작하지 않음
        Object result = pjp.proceed(); //proceed는 exception보다 상위 throwable을 처리해야 함

        long endTime = System.currentTimeMillis();
        //target메소드의 동작 시간을 출력
        logger.info(pjp.getSignature().getName() + ":" + (endTime -startTime));
        logger.info("==================================");

        //around를 사용할 경우 반드시 object를 리턴해야함
        return result;
    }
}