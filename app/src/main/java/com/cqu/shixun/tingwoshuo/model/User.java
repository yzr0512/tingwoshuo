package com.cqu.shixun.tingwoshuo.model;

public class User {

    int id; // 用户id

    String name;    // 用户名

    String phone;   // 用户手机号码

    String title;   // 用户头衔

    String intro;   // 用户简介

    float balance;  // 用户钱包余额

    Category category;  // 擅长的分类

    String md5;     // 用于http通信

    Boolean isResponder;    // 是否答主

    int ansNum;     // 回答问题个数

    float askPrice; // 提问价格

    public User(int id){
        this.id = id;
    }

    // id不允许前端修改
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnsNum() {
        return ansNum;
    }

    public void setAnsNum(int ansNum) {
        this.ansNum = ansNum;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }
}
