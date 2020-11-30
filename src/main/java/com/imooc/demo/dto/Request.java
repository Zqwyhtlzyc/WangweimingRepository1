package com.imooc.demo.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Request<T> implements Serializable {

    @Valid
    @NotNull(message = "报文head字段信息不能为空")
    private Head head;
    @Valid
    @NotNull(message = "报文data字段信息不能为空")
    private T data;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "head=" + head +
                ", data=" + data +
                '}';
    }

    public static class Head {
        private String sequenceId;

        private String appId;

        private String version;

        private String timestamp;

        public Head(){

        }

        public String getSequenceId() {
            return sequenceId;
        }

        public void setSequenceId(String sequenceId) {
            this.sequenceId = sequenceId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Head{" +
                    "sequenceId='" + sequenceId + '\'' +
                    ", appId='" + appId + '\'' +
                    ", version='" + version + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    '}';
        }
    }
}
