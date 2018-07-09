package com.cqu.shixun.tingwoshuo.ui.ExpertInfoView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public class ExpertInformationActivity extends AppCompatActivity implements IExpertInfoView, View.OnClickListener{

    IExpertInfoPresenter iExpertInfoPresenter;

    private Button BtnBack;
    private Button BtnAsk_bu_exim;
    private TextView TexImform_exim;
    private TextView TexName_exim;
    private TextView  TexKeyword_exim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_information);

        BtnBack=(Button)findViewById(R.id.back_bu_exim);
        BtnAsk_bu_exim=(Button)findViewById(R.id.ask_bu_exim);
        TexImform_exim=(TextView)findViewById(R.id.imform_exim);
        TexName_exim=(TextView)findViewById(R.id.name_exim);
        TexKeyword_exim=(TextView)findViewById(R.id.keyword_exim);


        BtnAsk_bu_exim.setOnClickListener(this);

        BtnBack.setOnClickListener(this); Intent intent=getIntent();
        String data=intent.getStringExtra("item_id");


        TexName_exim.setText(data);

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
        if(v.getId()==R.id.ask_bu_exim){
            Intent intent=new Intent();
            intent.setClass(ExpertInformationActivity.this,WriteQuestionActivity.class);
            startActivity(intent);
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
