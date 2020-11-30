package com.imooc.demo.config.Exception;

public class CommException extends RuntimeException {
    private String code;
    private String message;

    public CommException(String message){
        super(message);
        this.message=message;
    }

    public CommException(String code,String message){
        super(message);
        this.code=code;
        this.message=message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "CommException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
