package com.zsq.common;

import java.io.Serializable;
import java.util.List;

public class RetrunInfo implements Serializable {
    private Integer code;
    private String url;
    private String msg;
    private List<String> sid;

    public RetrunInfo() {
    }

    public List<String> getSid() {
        return sid;
    }

    public void setSid(List<String> sid) {
        this.sid = sid;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetrunInfo(Integer code, String url, String msg) {
        this.code = code;
        this.url = url;
        this.msg = msg;
    }
}
