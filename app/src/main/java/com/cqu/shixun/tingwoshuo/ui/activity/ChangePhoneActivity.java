package com.cqu.shixun.tingwoshuo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.cqu.shixun.tingwoshuo.R;

public class ChangePhoneActivity extends AppCompatActivity {
    public Button myback_bu;
    public Button yanzhenma_bu;
    private Button certen_bu;
    private EditText phonenumber;
    private EditText yanzhenmanumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);

        //返回
        myback_bu=(Button)findViewById(R.id.button_backward);
        myback_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent();
//                intent.setClass(ChangePhoneActivity.this, MySettingActivity.class);
//                //  intent.putExtra("id",2);
//                startActivity(intent);
            }
        });

        yanzhenma_bu=(Button)findViewById(R.id.getinf_bu);
        phonenumber = (EditText) findViewById(R.id.phone_text);//获取手机号
        yanzhenma_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //发送验证码

            }
        });

        certen_bu=(Button)findViewById(R.id.certain_bu3);
        yanzhenmanumber = (EditText) findViewById(R.id.conform_text);//获取验证码
        certen_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现数据库连接，存号码，检查验证码

            }
        });
    }
}
