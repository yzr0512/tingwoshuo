package com.cqu.shixun.tingwoshuo.ui.ListenView;

import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;

import java.util.List;

public interface IListenView {

//    void refreshSuccess();//刷新哪个页面,按钮id
//    void listenSuccess();
    void showQuestionList(List<Question> questions);
    Boolean showPayRequest(float price);
    void setAnswer(Answer answer);
    void showMessage(String msg);
}