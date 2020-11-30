package com.imooc.demo.dto;

import com.imooc.demo.valid.DateTime;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class SysIsvConfigDto {

    @NotBlank(message = "用户id不能为空")
    private String id;//

    @NotBlank(message = "用户名不能为空")
    private String userName;//

    @Length(max = 20,message = "notifyType不能超过20个字符")
    private String notifyType;//

    //@Pattern(regexp = "^[0-9]*[1-9][0-9]*$",message = "电话格式不合法")
    @Pattern(regexp = "(EMS)|(SLF)",message = "des只能是EMS和SLF")
    private String desKey;//

    @DateTime(type = DateTime.CheckType.date,pattern = "yyyyMMdd")
    private String createTime;//

    @DecimalMin(value = "-99.99",message = "减幅不能小于99.99")
    @DecimalMax(value = "999.99",message = "增幅不能大于999.99")
    private BigDecimal rat;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysIsvConfigDto{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", notifyType='" + notifyType + '\'' +
                ", desKey='" + desKey + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
