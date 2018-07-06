package com.cqu.shixun.tingwoshuo.iView;

public interface IListenView {

    void refreshSuccess();//刷新哪个页面,按钮id
    void listenSuccess();
    void showMessage(String msg);
}
