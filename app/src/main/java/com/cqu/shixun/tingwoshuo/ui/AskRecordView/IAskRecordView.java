package com.cqu.shixun.tingwoshuo.ui.AskRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;

import java.util.List;

public interface IAskRecordView {

    void showAskRecordList(List<Question> questions);

    void showMessage(String msg);

}
