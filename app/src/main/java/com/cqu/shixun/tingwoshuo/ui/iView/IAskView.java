package com.cqu.shixun.tingwoshuo.ui.iView;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public interface IAskView {
    // 提问成功
    void askSuccess();

    // 余额不足     balance当前钱包余额       required最低需要的余额
    void balanceNotEnough(float balance, float required);

    // 提示信息 主要是错误信息
    void showMessage(String msg);

    // 显示答主信息
    public void showExpertInfo(User user);
}
