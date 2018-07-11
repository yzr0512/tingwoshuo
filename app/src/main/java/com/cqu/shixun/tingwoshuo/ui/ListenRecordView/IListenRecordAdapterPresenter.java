package com.cqu.shixun.tingwoshuo.ui.ListenRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IListenRecordAdapterPresenter {
    void getQuestionAnswer(final Question question, final User user);

}
