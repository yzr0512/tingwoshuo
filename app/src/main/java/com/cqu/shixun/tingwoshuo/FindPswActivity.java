package com.cqu.shixun.tingwoshuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;
public class FindPswActivity extends AppCompatActivity {
    private Button findpsw_bu;
    private Button getinf_bu;//获取验证码
    private EditText editTextphone;//手机号
    private EditText editTextcomf;//验证码
    private EditText editTextpsw1;//密码
    private EditText editTextpsw2;//确认密码
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);

        //获取验证码
        getinf_bu=(Button)findViewById(R.id.getinf_bu_fsw);
        getinf_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证码操作、发送

            }
        });


        //找回密码操作
        context=this;
        editTextphone = (EditText) findViewById(R.id.phone_text_fsw);
        editTextpsw1 = (EditText) findViewById(R.id.newmima_text_fsw);
        editTextpsw2 = (EditText) findViewById(R.id.newmimaconform_text_fsw);
        editTextcomf = (EditText) findViewById(R.id.conform_text_fsw);
        findpsw_bu=(Button)findViewById(R.id.certain_bu_fsw);
        findpsw_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = editTextpsw1.getText().toString();//新密码
                String text2 = editTextpsw2.getText().toString();//确认密码
                String text3 = editTextcomf.getText().toString();//验证码
                //这里面有验证码和密码的判断
                if(true) {
                    //实现数据库修改和界面跳转


                    Intent intent = new Intent();
                    intent.setClass(FindPswActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                else {

                    if(!(text1.equals(text2))){//密码不相同
                        Toast t = Toast.makeText(context,"密码不相同，请重新输入！", Toast.LENGTH_LONG);
                        t.show();
                        editTextpsw2.setText("");
                    }
                    else{//验证码不正确
                        Toast t = Toast.makeText(context,"验证码不正确，请重新输入！", Toast.LENGTH_LONG);
                        t.show();
                        editTextcomf.setText("");
                    }

                }

            }
        });

    }
}
