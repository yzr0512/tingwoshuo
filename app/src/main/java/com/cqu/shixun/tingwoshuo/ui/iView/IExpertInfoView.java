package com.cqu.shixun.tingwoshuo.ui.iView;
// 答主页

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public interface IExpertInfoView {
    // 显示答主页
    public void showExpertInfo(User user, List<Question> questions);

    // 显示信息 主要是错误信息
    public void showMessage(String msg);

}
