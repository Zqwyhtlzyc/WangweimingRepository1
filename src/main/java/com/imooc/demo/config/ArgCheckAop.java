package com.imooc.demo.config;

import com.imooc.demo.config.Exception.CommException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Aspect
@Component
public class ArgCheckAop {

    @Pointcut("execution(public * com.imooc.demo.controller.*Controller.*(..))")
    public void check(){
    }

    @Before("check()" + "&& args(..,result)")
    public void checkArgs(BindingResult result){
        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()){
            for(ObjectError error:result.getAllErrors()){
                sb.append(error.getDefaultMessage());
                sb.append(" | ");
            }
            throw new CommException("IllageArgument",sb.toString());
        }

    }
}
