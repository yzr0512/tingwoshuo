package com.cqu.shixun.tingwoshuo.ui.AnswerRecordView;

import com.cqu.shixun.tingwoshuo.model.Question;

import java.util.List;

public interface IAnswerRecordView {

    void showAnswerRecordList(List<Question> questions);

    void showMessage(String msg);
}
