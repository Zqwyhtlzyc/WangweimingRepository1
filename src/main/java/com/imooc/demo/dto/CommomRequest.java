package com.imooc.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel(description = "请求报文")
public class CommomRequest {

    private static final Logger logger = LoggerFactory.getLogger(CommomRequest.class);

    @NotBlank(message = "报文格式不能为空")
    @Pattern(regexp = "^JSON$",message = "请求格式非法，只能是JSON格式")
    @ApiModelProperty(value = "请求报文格式，固定JSON",required = true,example = "JSON")
    private String format;
    @NotBlank(message = "编码格式不能为空")
    private String encoding;
    @NotBlank(message = "签名方式不能为空")
    private String signType;
    @NotBlank(message = "签名密文串不能为空")
    private String sign;
    @NotBlank(message = "时间戳不能为空")
    private String timestamp;
    @NotBlank(message = "版本号不能为空不能为空")
    private String version;
    //@NotBlank(message = "用户编码不能为空")
    private String usrNbr;
    //@NotBlank(message = "调用系统不能为空")
    @ApiModelProperty(value = "调用系统，如物业系统：property",required = true,example = "property")
    private String system;


    public static Logger getLogger() {
        return logger;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsrNbr() {
        return usrNbr;
    }

    public void setUsrNbr(String usrNbr) {
        this.usrNbr = usrNbr;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "CommomRequest{" +
                "format='" + format + '\'' +
                ", encoding='" + encoding + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", version='" + version + '\'' +
                ", usrNbr='" + usrNbr + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
