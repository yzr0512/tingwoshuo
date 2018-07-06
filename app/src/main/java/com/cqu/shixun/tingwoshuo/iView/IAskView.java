package com.cqu.shixun.tingwoshuo.iView;

public interface IAskView {
    void refreshSuccess();
    void refreshFailure(String msg);

    void searchSuccess();
    void searchFailure(String msg);

    void paySuccess();
    void payFailure(String msg);
}
