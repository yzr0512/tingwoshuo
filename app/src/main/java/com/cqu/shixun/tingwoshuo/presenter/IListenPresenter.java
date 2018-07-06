package com.cqu.shixun.tingwoshuo.presenter;

public interface IListenPresenter {
    //刷新内容，即分类
    void refresh(String butten_id);
    //偷听者id，回答者id，金钱，问题内容(音频)
    void ask(String askerid,String answerid,String money,String msg);

}
