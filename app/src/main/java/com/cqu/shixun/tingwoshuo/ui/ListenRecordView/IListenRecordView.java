package com.cqu.shixun.tingwoshuo.ui.ListenRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;

import java.util.List;

public interface IListenRecordView {

    void showListenRecordList(List<Question> questions);

    void showMessage(String msg);
}
