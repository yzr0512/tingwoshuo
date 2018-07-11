package com.cqu.shixun.tingwoshuo.ui.AnswerRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IAnswerRecordAdapterPresenter {
    void getQuestionAnswer(final Question question, final User user);
}
