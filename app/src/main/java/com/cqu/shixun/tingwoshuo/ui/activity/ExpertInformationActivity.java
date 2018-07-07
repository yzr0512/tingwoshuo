package com.cqu.shixun.tingwoshuo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IExpertInfoPresenter;
import com.cqu.shixun.tingwoshuo.presenter.impl.ExpertInfoPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.iView.IExpertInfoView;

import java.util.List;

public class ExpertInformationActivity extends AppCompatActivity implements IExpertInfoView{

    IExpertInfoPresenter iExpertInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_information);

        iExpertInfoPresenter = new ExpertInfoPresenterImpl();
    }

    // 显示答主页
    @Override
    public void showExpertInfo(User user, List<Question> questions) {

    }

    // 显示信息 主要是错误信息
    @Override
    public void showMessage(String msg) {

    }
}
