package com.imooc.demo.dto;

import java.util.Date;

public class SysIsvConfig {
    private Long id;

    private String userName;

    private String notifyUrl;

    private Byte notifyType;

    private String desKey;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public Byte getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Byte notifyType) {
        this.notifyType = notifyType;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey == null ? null : desKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}