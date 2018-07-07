package com.cqu.shixun.tingwoshuo.presenter.iPresenter;

public interface IAskPresenter {
    //提问者id，回答者id，金钱，问题内容
    void ask(String askerid,String answerid,String money,String msg);

}
