package com.cqu.shixun.tingwoshuo.ui.ListenView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

/**
 * Created by legendpeng on 2018/7/10.
 */

public interface IListenAdapterPresenter {

    void getQuestionAnswer(Question question, User user);

    void confirmPayRequest();
}
