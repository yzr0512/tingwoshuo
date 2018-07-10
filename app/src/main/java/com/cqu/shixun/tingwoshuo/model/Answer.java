package com.cqu.shixun.tingwoshuo.model;

public class Answer {
    int id;

    int questionID; // 问题的ID

    Boolean isAppend; // 是否追问（）

    String answerPath; // 回答内容 文件路径

    public Answer(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Boolean getAppend() {
        return isAppend;
    }

    public void setAppend(Boolean append) {
        isAppend = append;
    }

    public String getAnswerPath() {
        return answerPath;
    }

    public void setAnswerPath(String answerPath) {
        this.answerPath = answerPath;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
