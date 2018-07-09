package com.cqu.shixun.tingwoshuo.ui.AnswerView;

import com.cqu.shixun.tingwoshuo.model.Question;

public interface IAnswerView {
    // 显示问题的信息
    void showQuestionInfo(Question question);

    void answerSuccess();

    // 显示错误信息
    void showMessage(String msg);
}
