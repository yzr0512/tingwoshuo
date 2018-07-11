package com.cqu.shixun.tingwoshuo.ui.AskRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IAskRecordAdapterPresenter {
    void getQuestionAnswer(final Question question, final User user);
}
