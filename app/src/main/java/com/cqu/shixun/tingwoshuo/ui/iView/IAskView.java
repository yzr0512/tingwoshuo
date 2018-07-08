package com.cqu.shixun.tingwoshuo.ui.iView;

public interface IAskView {
    // 提问成功
    void askSuccess();

    // 余额不足     balance当前钱包余额       required最低需要的余额
    void balanceNotEnough(float balance, float required);

    // 提示信息 主要是错误信息
    void showMessage(String msg);
}
