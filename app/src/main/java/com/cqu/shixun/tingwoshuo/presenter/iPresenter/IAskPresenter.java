package com.cqu.shixun.tingwoshuo.presenter.iPresenter;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

public interface IAskPresenter {
    /*
        功能：提交问题
        传入参数说明：
            user是当前登录的用户，需要设置如下内容：
            --phone : 用户手机号
            --md5 : 用户登录信息

            question是提问的问题，需要设置如下内容:
            --questionerID : 提问者id
            --responderID : 回答者id
            --price : 提问价格
            --content : 问题内容
            --category : 问题分类
     */
    void postQuestion(User user, Question question);

}
