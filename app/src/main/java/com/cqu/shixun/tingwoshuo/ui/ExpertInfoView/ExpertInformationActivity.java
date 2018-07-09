package com.cqu.shixun.tingwoshuo.ui.ExpertInfoView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public class ExpertInformationActivity extends AppCompatActivity implements IExpertInfoView, View.OnClickListener{

    IExpertInfoPresenter iExpertInfoPresenter;

    private Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_information);

        BtnBack=(Button)findViewById(R.id.back_bu_exim);
        BtnBack.setOnClickListener(this);

        iExpertInfoPresenter = new ExpertInfoPresenterImpl(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_bu_exim){
            ExpertInformationActivity.this.setResult(1);
            ExpertInformationActivity.this.finish();

//            Intent intent=new Intent();
//            intent.setClass(ExpertInformationActivity.this,ExpertInformationActivity.);
        }
        
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
