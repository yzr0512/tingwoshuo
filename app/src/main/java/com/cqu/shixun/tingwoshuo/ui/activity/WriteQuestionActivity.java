package com.cqu.shixun.tingwoshuo.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IAskPresenter;
import com.cqu.shixun.tingwoshuo.presenter.impl.AskPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.iView.IAskView;

public class WriteQuestionActivity extends AppCompatActivity implements IAskView {

    Question currQuestion;  // 当前提的问题   用于余额不足时充值后重新提交
    IAskPresenter iAskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_question);

        iAskPresenter = new AskPresenterImpl(this);
        iAskPresenter.postQuestion(new User(1), new Question(1));
    }

    @Override
    public void askSuccess() {
        // 提问成功进行的操作
        Toast t = Toast.makeText(this, "提问成功", Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void balanceNotEnough(float balance, float required) {
        // 余额不足时进行的操作
        // balance是用户剩余的钱
        // required是提问需要的钱
        Toast t = Toast.makeText(this, "余额不足", Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
