package com.example.yangyang.lifehelper.entity;

/**
 * Created by yangyang on 2018/11/11.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.entity
 * 作用：
 */

public class Chat {
    // 文本
    private String text;
    // type
    private int type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
