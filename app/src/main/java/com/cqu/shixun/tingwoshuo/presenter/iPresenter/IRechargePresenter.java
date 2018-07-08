package com.cqu.shixun.tingwoshuo.presenter.iPresenter;

import com.cqu.shixun.tingwoshuo.model.User;

public interface IRechargePresenter {

    //phone,money 手机号和充值的钱
    void recharge(User user, float money);


}
