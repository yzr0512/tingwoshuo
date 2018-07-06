package com.cqu.shixun.tingwoshuo.iView;

public interface IListenView {

    void refreshSuccess(String id);//刷新哪个页面
    void refreshFailure(String msg);

    void listenSuccess(String id);
    void listenFailure(String msg);
}
