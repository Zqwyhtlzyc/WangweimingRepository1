package com.imooc.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.imooc.demo.config.Exception.CommException;
import com.imooc.demo.dto.Request;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ControllerAround {
    private Logger logger = LoggerFactory.getLogger(ControllerAround.class);

    @Pointcut("@annotation(com.imooc.demo.config.LogAround)")
    public void LogPointcut(){
    }

    /**
     * 环绕通知，自由定义
     */
    @Around("LogPointcut()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable {
        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogAround annotation = method.getAnnotation(LogAround.class);

        String description = annotation.description();
        if (args != null && args.length > 0) {
            if (args[0] instanceof Request) {
                Request request = (Request) args[0];
                logger.info("************收到外部的请求，description：[{}],请求报文内容：[{}]", description, request);
            } else {
                logger.error("************收到外部的请求，description：[{}],请求格式错误", description);
                logger.error("************具体内容为：", JSONObject.toJSON(args[0]));
                throw new CommException("FAIL", "请求报文格式错误");
            }
        }
        Object proceed = joinPoint.proceed();
        if (null != proceed) {
            logger.info("************请求响应结果：[{}]", proceed);
        }
        return proceed;

    }

}
