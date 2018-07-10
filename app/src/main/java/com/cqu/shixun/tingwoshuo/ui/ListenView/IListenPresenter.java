package com.cqu.shixun.tingwoshuo.ui.ListenView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IListenPresenter {
    //刷新内容
    void getQuestionList(String category);

    void getQuestionAnswer(Question question, User user, String path);

}
