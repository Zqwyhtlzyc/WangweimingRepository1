package com.imooc.demo.config.error;

import com.imooc.demo.constant.CommonCodes;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * 错误详情
 *
 * @author Vincent
 * @version 1.0 2017-06-27 09:33
 */
public class ErrorCode implements Serializable {

    private static final long serialVersionUID = 1L;

    // 状态码
    private String code;

    // 国际化信息
    private String message;

    // 参数, 用于传递动态数据
    private Object[] args;

    public static ErrorCode success() {
        return create(CommonCodes.SUCCESS);
    }

    public static ErrorCode error() {
        return create(CommonCodes.SERVER_BUSY);
    }

    public static ErrorCode create(ErrorCodeType errorCode) {
        return create(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static ErrorCode create(ErrorCodeType errorCode, Object[] args) {
        return create(errorCode.getCode(), errorCode.getMessage(), args);
    }

    public static ErrorCode create(String code, String message) {
        return new ErrorCode(code, message, null);
    }

    public static ErrorCode create(String code, String message, Object[] args) {
        return new ErrorCode(code, message, args);
    }

    public ErrorCode() {
        this.code = CommonCodes.SERVER_BUSY.getCode();
        this.message = CommonCodes.SERVER_BUSY.getMessage();
    }

    public ErrorCode(String code, String message, Object[] args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        if (StringUtils.isBlank(this.message)) return this.message;
        return MessageFormat.format(this.message, this.args);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
