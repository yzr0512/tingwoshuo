package com.cqu.shixun.tingwoshuo.ui.LoginView;

import com.cqu.shixun.tingwoshuo.model.User;

public interface ILoginView {
    void loginSuccess(User user);
    void showMessage(String msg);
}
