package com.cqu.shixun.tingwoshuo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button rigester_bu;
    private Button certen_bu;
    private Button findpsw_bu;
    private EditText editTextone;//账号
    private EditText editTexttwo;//密码
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //跳转注册界面
        rigester_bu=(Button)findViewById(R.id.rigister_bu_login);
        rigester_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //跳转找回密码界面
        findpsw_bu=(Button)findViewById(R.id.findpsw_bu_login);
        findpsw_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, FindPswActivity.class);
                startActivity(intent);
            }
        });

        //登录操作
        certen_bu=(Button)findViewById(R.id.certen_bu_login);
        editTextone = (EditText) findViewById(R.id.count_tv_login);
        editTexttwo = (EditText) findViewById(R.id.mima_tv_login);
        certen_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //检查账号密码是否正常，连数据库
                if(true){

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    //  intent.putExtra("id",2);
                    startActivity(intent);
                }
                else{
                    Toast t = Toast.makeText(context,"密码或账号，请重新输入！", Toast.LENGTH_LONG);
                    t.show();
                    editTexttwo.setText("");

                }


            }
        });

    }
}
