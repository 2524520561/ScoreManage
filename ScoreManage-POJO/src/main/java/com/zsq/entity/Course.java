package com.zsq.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String couId;

    private String couName;

    public String getCouId() {
        return couId;
    }

    public void setCouId(String couId) {
        this.couId = couId == null ? null : couId.trim();
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName == null ? null : couName.trim();
    }
}