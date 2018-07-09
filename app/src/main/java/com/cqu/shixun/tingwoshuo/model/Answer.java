package com.cqu.shixun.tingwoshuo.model;

public class Answer {
    int id;

    int questionID; // 回答者的ID

    Boolean isAppend; // 是否追问（）

    String answer; // 回答内容 文件路径

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}
