package com.imooc.demo.constant;

import com.imooc.demo.config.error.ErrorCode;
import com.imooc.demo.config.error.ErrorCodeType;

/**
 * 公共异常信息
 *
 * @author Vincent
 * @version 1.0 2017-06-27 09:45
 */
public enum CommonCodes implements ErrorCodeType {
	SAVE_SUCCESS("0","保存成功!"),
	UPDATE_SUCCESS("0","更新成功!"),
	UPDATE_PWD_SUCCESS("0","密码修改成功,请重新登录!"),
	SUBMIT_SUCCESS("0","提交成功!"),
	RESET_SUCCESS("0","重置成功!"),
	SAVE_FAILED("-1","保存失败!"),
	UPDATE_FAILED("-1","更新失败!"),
	SUBMIT_FAILED("-1","提交失败!"),
	RESET_FAILED("-1","重置失败!"),
	SAVE_EXCEPTION("-1","保存失败,请刷新重试!"),
	UPDATE_EXCEPTION("-1","更新失败,请刷新重试!"),
	SUBMIT_EXCEPTION("-1","提交失败,请刷新重试!"),
	RESET_EXCEPTION("-1","重置失败,请刷新重试!"),
	NO_PERMISSION_VISIT("401","无权限操作!"),
	
    SUCCESS("success", null),

    INTERNAL_ERROR("internal error","内部错误"),
    SERVER_BUSY("common.server.busy", "服务器繁忙,请稍后再试"),
    PARAMS_ERROR("common.params.error", "参数错误"),
    OPERATION_ERROR("common.operation.error", "操作失败，请稍后重试"),
    OPERATION_BUSY("common.operation.busy","您操作过于频繁，请稍后访问"),
    VISITOR_BUSY("common.visitor.busy","当前访问人数太多，请稍后访问"),

    FILE_UPLOAD_ERROR("common.file.upload.error", "文件上传失败"),
    FILE_NOT_EXIST("common.file.not.exist", "文件不存在"),
    NOT_LOGIN("common.not.login", "请先去登录"),
    LOGIN_SUCCESS("common.login.success","登录成功"),
    LOGIN_FAILED("common.login.failed", "登录失败，请联系管理员"),
    SIGN_ERROR("common.sign.error", "签名失败"),
    VIEW_ERROR("common.view.error", "视图渲染失败"),
    VALID_PARAMS_NOT_NULL("common.valid.params.not.null", "{0}不能为空"),
    VERIFY_CODE_ERROR("common.verify.code.error", "短信验证码不正确"),
    VERIFY_CODE_SENDED("common.verify.code.sended", "短信验证码已经发送，请稍后再试"),
    CAPTCHA_CODE_ERROR("common.captcha.code.error", "验证码不正确"),

    OBJECT_NOT_FOUND("common.obj.not.found", "没有获取到数据"),
    OBJECT_COPY_ERROR("common.obj.copy.error", "数据获取失败"),
    OBJECT_CREATE_ERROR("common.obj.create.error", "创建对象错误"),

    HTTP_CLIENT_PROTOCOL_ERROR("common.http.client.protocol.error", "HTTP协议错误"),
    HTTP_API_SIGN_ERROR("common.http.api.sign.error", "签名验证失败"),

    IO_EXCEPTION("common.io.error", "IO异常"),
    AUTH_EXCEPTION("common.auth.error", "您无权访问该资源，请联系管理员"),
    CLIENT_EXCEPTION("common.client.error", "客户端不匹配"),
    INVALID_URL("common.invalid.url", "无效的访问链接"),
    INVALID_REDIRECT_URL("common.invalid.redirect.url", "重定向失败"),
    THIRD_SERVER_EXCEPTION("e.common.third.server.error", "第三方服务器异常"),
    THIRD_SERVER_FAIL("common.third.server.fail", "第三方处理失败"),
    
    ID_SEQUENCE_ERROR("id.sequence.error","ID值错误!");

    private final String code;

    private final String message;

    CommonCodes(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ErrorCode toErrorCode(Object... args) {
        return new ErrorCode(this.getCode(), this.getMessage(), args);
    }
}
