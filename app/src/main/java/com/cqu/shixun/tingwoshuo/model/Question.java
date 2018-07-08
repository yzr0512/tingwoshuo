package com.cqu.shixun.tingwoshuo.model;

public class Question {

    int id; // 问题的id

    int questionerID;   // 提问者ID

    String questionerName;  // 提问者名称

    int responderID;    // 回答者ID

    String responderName;   // 回答者名字

    String content;     // 问题内容 250字以内

    int listenNum;  // 偷听人数

    String category;    // 分类

    float price;    // 问题价格

    float listenPrice;  // 偷听价格

    String status;  // 状态

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Question(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuestionerID() {
        return questionerID;
    }

    public void setQuestionerID(int questionerID) {
        this.questionerID = questionerID;
    }

    public String getQuestionerName() {
        return questionerName;
    }

    public void setQuestionerName(String questionerName) {
        this.questionerName = questionerName;
    }

    public int getResponderID() {
        return responderID;
    }

    public void setResponderID(int responderID) {
        this.responderID = responderID;
    }

    public String getResponderName() {
        return responderName;
    }

    public void setResponderName(String responderName) {
        this.responderName = responderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getListenNum() {
        return listenNum;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getListenPrice() {
        return listenPrice;
    }

    public void setListenPrice(float listenPrice) {
        this.listenPrice = listenPrice;
    }
}
