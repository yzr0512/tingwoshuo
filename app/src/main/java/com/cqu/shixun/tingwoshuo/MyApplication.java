package com.cqu.shixun.tingwoshuo;

import android.app.Application;

import com.cqu.shixun.tingwoshuo.model.User;

public class MyApplication extends Application {

    // 在这里可以定义一些全局的变量
    User currUser;      // 当前登录的用户

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
}
