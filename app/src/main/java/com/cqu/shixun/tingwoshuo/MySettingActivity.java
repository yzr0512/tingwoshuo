package com.cqu.shixun.tingwoshuo;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class MySettingActivity extends AppCompatActivity {
    public Button myback_bu;
    public LinearLayout changephone;
    public LinearLayout changepsw;
    public LinearLayout aboutus;
    public LinearLayout changecount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        //返回
        myback_bu=(Button)findViewById(R.id.button_backward);
        myback_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MySettingActivity.this, MainActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        //进入更换手机
        changephone=(LinearLayout)findViewById(R.id.changephone);
        changephone.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MySettingActivity.this, ChangePhoneActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        //进入更换密码
        changepsw=(LinearLayout)findViewById(R.id.changepsw);
        changepsw.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MySettingActivity.this, ChangePswActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });

        //进入关于我们
        aboutus=(LinearLayout)findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MySettingActivity.this, AboutUsActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        //进入切换账号
        changecount=(LinearLayout)findViewById(R.id.changecount);
        changecount.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                builder.setMessage("确认要退出么？");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                        //跳转到登录窗口

                       // finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                builder.show();


            }
        });


    }
}
