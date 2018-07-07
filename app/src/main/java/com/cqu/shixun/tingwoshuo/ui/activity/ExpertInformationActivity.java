package com.cqu.shixun.tingwoshuo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.cqu.shixun.tingwoshuo.R;

public class ExpertInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_information);

        BtnBack=(Button)findViewById(R.id.back_bu_exim);
        BtnBack.setOnClickListener(this);
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



}
