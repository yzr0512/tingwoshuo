package com.cqu.shixun.tingwoshuo.ui.AnswerView;

import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IAnswerPresenter {

    /*
        功能：获得问题的信息
        传入参数说明：
            questionID : 问题的ID
     */
    void getQuestionInfo(int questionID);

    public void postAnswer(User user, Answer answer);

}
