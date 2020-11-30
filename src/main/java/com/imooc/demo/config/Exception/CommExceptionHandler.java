package com.imooc.demo.config.Exception;

import com.imooc.demo.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//(basePackages="")  可以指定捕捉那个controller的异常
public class CommExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result HandleException(Exception e){
//        if(e instanceof MethodArgumentNotValidException){
//            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
//
//            StringBuilder sb = new StringBuilder();
//            if (result.hasErrors()){
//                for(ObjectError error:result.getAllErrors()){
//                    sb.append(error.getDefaultMessage());
//                    sb.append(" | ");
//                }
//            }


//            BindException ex
//            List<FieldError> fieldErrors =ex.getFieldErrors();
//            StringBuilder sb = new StringBuilder();
//            for (FieldError fieldError:fieldErrors){
//                sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue())
//                        .append("]").append(",  ").append(fieldError.getDefaultMessage());
//                if(fieldError != fieldErrors.get(fieldErrors.size()-1)){
//                    sb.append(" && ");
//                }
//            }


//            return  Result.failure("FAIL",sb.toString());
//        }
        logger.error("其他未知(未捕捉)异常",e);
        return  Result.fail("FAIL",e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result MethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("参数绑定异常",e);

        BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();

        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()){
            for(ObjectError error:result.getAllErrors()){
                sb.append(error.getDefaultMessage());
                sb.append(" | ");
            }
        }
        return  Result.fail("FAIL",sb.toString());
    }


    @ExceptionHandler(CommException.class)
    @ResponseBody
    public Result HandleCommException(CommException e){
        logger.error("服务异常",e);
        return  Result.fail(e.getCode(),e.getMessage());
    }



}
