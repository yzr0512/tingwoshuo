package com.cqu.shixun.tingwoshuo.presenter.iPresenter;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IAskPresenter {
    /*
    * 用户:phone
    * md5;
    *
    * Question:
    * QuestionerID:提问者ID，
    * responderID:回答者ID
    * price:提问价格
    * content:回答内容
    * category:问题分类
    * */

    void postQuestion(User user, Question question);

}
