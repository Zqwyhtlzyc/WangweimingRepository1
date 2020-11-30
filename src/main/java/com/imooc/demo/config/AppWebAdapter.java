package com.imooc.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class AppWebAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private RestControllerInterceptor restControllerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        //意思是所有的请求都拦截
        interceptorRegistry.addInterceptor(restControllerInterceptor).addPathPatterns("/**");

        //意思是不拦截(去除) XXX的路径
        interceptorRegistry.addInterceptor(restControllerInterceptor).excludePathPatterns("/XXXXX/**");
    }



}
