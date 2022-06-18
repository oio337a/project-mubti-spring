package com.mubti.global.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mubti.global.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    @Before("execution(* com.mubti.domain..*Controller.*(..))")
    public void beforeLog(JoinPoint joinPoint) throws Throwable{
        JSONObject jsonObject = new JSONObject();
        Class clazz = joinPoint.getTarget().getClass();

        log.info(getRequestUrl(joinPoint, clazz));
        log.info("parameters" + jsonObject.toJSONString(params(joinPoint)));
    }

    @AfterReturning(pointcut = "execution(* com.mubti.domain..*Controller.*(..))",
                    returning = "result")
    public void afterReturningLog(JoinPoint joinPoint, Object result) throws Throwable{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> responseMap = new HashMap<>();

        ResponseEntity responseEntity = (ResponseEntity) result;

        header.put("statusCode", responseEntity.getStatusCode().toString());
        Object body = responseEntity.getBody();

        responseMap.put("header", header);
        responseMap.put("body", body);

        log.info("response: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseMap));
    }

    @AfterThrowing(pointcut = "execution(* com.mubti.domain..*Controller.*(..))",
                   throwing = "ex")
    public void afterThrowingLog(JoinPoint joinPoint, Throwable ex) {
        String signatureString = joinPoint.getSignature().getName();
        log.info("[" + signatureString + "] 메서드 실행 중 예외 발생");
        log.info("[" + signatureString + "] 예외 = " + ex);
    }

    @Around("execution(* com.mubti.domain..*Controller.*(..))")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.info("execution time: " + (endTime - startTime) + "ms");
        return result;
    }

    private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        String baseUrl = requestMapping.value()[0];

        String url = Stream.of( GetMapping.class, PutMapping.class, PostMapping.class,
                        PatchMapping.class, DeleteMapping.class, RequestMapping.class)
                .filter(mappingClass -> method.isAnnotationPresent(mappingClass))
                .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
                .findFirst().orElse(null);
        return url;
    }

    private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
        Annotation annotation = method.getAnnotation(annotationClass);
        String[] value;
        String httpMethod = null;

        try {
            value = (String[])annotationClass.getMethod("value").invoke(annotation);
            httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
        return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "") ;
    }

    private Map params(JoinPoint joinPoint) {
        Map<String, Object> params = new HashMap<>();

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i].toString());
        }
        return params;
    }

}