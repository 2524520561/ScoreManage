package com.zsq.common;

import java.io.Serializable;

public class StudentScore implements Serializable {
    private String name;
    private String stuId;
    private String couName;
    private Integer score;
    private String couId;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouId() {
        return couId;
    }

    public void setCouId(String couId) {
        this.couId = couId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "name='" + name + '\'' +
                ", stuId='" + stuId + '\'' +
                ", couName='" + couName + '\'' +
                ", score=" + score +
                ", couId='" + couId + '\'' +
                '}';
    }
}
