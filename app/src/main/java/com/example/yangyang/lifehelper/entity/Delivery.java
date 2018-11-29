package com.example.yangyang.lifehelper.entity;

/**
 * Created by yangyang on 2018/11/6.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.entity
 * 作用：
 */

public class Delivery {
    private String data;
    private String remark;
    private String zone;

    public Delivery() {
    }

    public Delivery(String data, String remark, String zone) {
        this.data = data;
        this.remark = remark;
        this.zone = zone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
