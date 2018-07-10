package com.cqu.shixun.tingwoshuo.ui.ListenView;

import com.cqu.shixun.tingwoshuo.model.Answer;

/**
 * Created by legendpeng on 2018/7/10.
 */

public interface IListenAdapterView {
    void play(Answer answer);
    void showMessage(String msg);
    void showPayRequest(float price);
}
