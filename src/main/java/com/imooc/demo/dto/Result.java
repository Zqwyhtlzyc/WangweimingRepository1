package com.imooc.demo.dto;

import com.imooc.demo.config.error.ErrorCode;
import com.imooc.demo.constant.CommonCodes;
import com.imooc.demo.constant.Constant;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias(Constant.CONSTANT_XML_ROOT_ELE)
public class Result implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MSG="操作成功";


	//执行结果
	private boolean success = true;
	//执行状态状态码
	private String code="0";
	private String msg = DEFAULT_MSG;
    private Object data;
    private ErrorCode error;
    
    
    public Result() {	}
    
    public Result(Object obj) {
        if(obj instanceof ErrorCode){
        	ErrorCode errorCode = (ErrorCode) obj;
            this.setError(errorCode);
            this.msg= errorCode.getMessage();
        } else {
            this.data = obj;
        }
    }
	public Result(String code,String msg) {
		this.code=code;
		this.msg = msg;
	}
    public Result(boolean isSuccess,String code,String msg) {
    	this.success=isSuccess;
    	this.code=code;
    	this.msg = msg;
    }
    
    public Result(String msg) {
    	this.msg = msg;
    }
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object obj) {
		this.data = obj;
	}
	public ErrorCode getError() {
		return error;
	}
	public void setError(ErrorCode error) {
		this.error = error;
        if (error != null) {
            success = CommonCodes.SUCCESS.getCode().equalsIgnoreCase(error.getCode());
            code=error.getCode();
        }
	}

	public static Result ok() {
        return new Result();
    }
	public static Result okCode(String code){
		return new Result(true, code, DEFAULT_MSG);
	}
	public static Result okCode(String code,String msg){
		return new Result(true, code, msg);
	}
	public static Result okCode(String code,String msg,Object obj){
		Result result=new Result(true, code, msg);
		result.setData(obj);
		return result;
	}
	public static Result ok(String msg) {
		return new Result(msg);
	}

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result fail(String message) {
        CommonCodes error = CommonCodes.SERVER_BUSY;
        String code = error.getCode();
        return failure(code, message);
    }


	public static Result fail(String code,String message) {
		return new Result(false,code,message);
	}

    public static Result failure(ErrorCode errorCode) {
        return new Result(errorCode);
    }

    public static Result failure(String code, String message) {
        return failure(ErrorCode.create(code, message));
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
