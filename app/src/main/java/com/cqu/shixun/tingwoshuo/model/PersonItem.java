package com.cqu.shixun.tingwoshuo.model;

/**
 * Created by legendpeng on 2018/7/5.
 */

public class PersonItem {

    private int id;
    private String avatar;
    private String name;
    private String title;
    private int ansNum;
    private int listenNum;
    private float askPrice;

    public PersonItem(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return avatar;
    }

    public void setImg(String img) {
        this.avatar = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getListenNum() {
        return listenNum;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }
}
