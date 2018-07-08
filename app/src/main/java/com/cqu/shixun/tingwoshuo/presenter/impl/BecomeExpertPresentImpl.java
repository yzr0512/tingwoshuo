package com.cqu.shixun.tingwoshuo.presenter.impl;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IBecomeExpertPresenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IBecomeExpertView;
public class BecomeExpertPresentImpl implements IBecomeExpertPresenter {

    IBecomeExpertView iBecomeExpertView;

    public BecomeExpertPresentImpl(IBecomeExpertView iBecomeExpertView){
        this.iBecomeExpertView = iBecomeExpertView;
    }
    @Override
    public void getUser(User user) {

    }


}
