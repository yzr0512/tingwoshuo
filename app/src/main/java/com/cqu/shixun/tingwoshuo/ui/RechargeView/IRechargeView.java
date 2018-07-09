package com.cqu.shixun.tingwoshuo.ui.RechargeView;

import com.cqu.shixun.tingwoshuo.model.User;

public interface IRechargeView {

    //显示用户的余额
    /*user当前用户
      -phone ：手机号码
      -balance ：当前用户余额
    */
    void rechargeInformation(User user);

    void rechargeSuccess();
    void showMessage(String msg);

}
