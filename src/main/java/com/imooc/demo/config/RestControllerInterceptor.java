package com.imooc.demo.config;

import com.imooc.demo.config.Exception.CommException;
import com.imooc.demo.server.TokenServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RestControllerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerInterceptor.class);

    private Long begintime = 0L;
    private final String excptUrl = "/file";

    @Resource
    private TokenServer tokenServer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //定义开始时间
        begintime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();
        logger.info("*********%s 接到浏览器的请求：%s",begintime,requestURI);

        String ipAddress;

        String ips = request.getHeader("x-forwarded-for");

        if(StringUtils.trim(ips)==null){
            ipAddress = request.getRemoteHost();
            logger.info(String.format("当前访问Ip->getRemoteHost:%s",ipAddress));
        }else {
            ipAddress = ips.length()>16?ips.split(",")[0]:ips;
            logger.info(String.format("当前访问Ip->getHander:%s",ipAddress));
        }
        //记录请求日志
        logger.info(request.getRequestURI()+",请求到达");
        //3,返回true，才会找下一个拦截器，如果没有下一个拦截器，则去Controller

        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            RequiredToken requiredToken = handlerMethod.getMethod().getAnnotation(RequiredToken.class);

            if(requiredToken == null){
                requiredToken = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredToken.class);
            }

            if(requiredToken != null){
                String token = request.getHeader("X-Access-Token");

                if(StringUtils.isEmpty(token)){
                    logger.info(String.format("用户未登录，无法操作"));
                    throw new CommException("000000","用户未登录，无法操作");
                }

                boolean isPass = tokenServer.verifyToken(token);

                if(!isPass){
                    logger.info(String.format("用户登录已经过期"));
                    throw new CommException("000001","用户登录已经过期，无法操作");
                }else {

                    //用户超时时间延期
                    tokenServer.deferToken(token);
                }
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       Long endTime = System.currentTimeMillis();
       logger.info(String.format("*************%s 处理完请求:%s，花费时间：%s",endTime,request.getRequestURI(),endTime-begintime));
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
