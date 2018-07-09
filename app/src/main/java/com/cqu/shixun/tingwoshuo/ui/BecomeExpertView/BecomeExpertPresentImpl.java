package com.cqu.shixun.tingwoshuo.ui.BecomeExpertView;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.BecomeExpertView.IBecomeExpertPresenter;
import com.cqu.shixun.tingwoshuo.ui.BecomeExpertView.IBecomeExpertView;
public class BecomeExpertPresentImpl implements IBecomeExpertPresenter {

    IBecomeExpertView iBecomeExpertView;

    public BecomeExpertPresentImpl(IBecomeExpertView iBecomeExpertView){
        this.iBecomeExpertView = iBecomeExpertView;
    }
    @Override
    public void postUser(User user) {

    }


}
