package com.cqu.shixun.tingwoshuo.ui.RechargeView;

import com.cqu.shixun.tingwoshuo.model.User;

public interface IRechargePresenter {

    void getBalance(User user);

    //phone,money 手机号和充值的钱
    void recharge(User user, float money);


}
