package com.cqu.shixun.tingwoshuo.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;


import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.ui.ChangePwdView.ChangePswActivity;
import com.cqu.shixun.tingwoshuo.ui.LoginView.LoginActivity;

public class MySettingActivity extends AppCompatActivity {
    public Button myback_bu;
    public Button exit_bu;
    public LinearLayout changephone;
    public LinearLayout changepsw;
    public LinearLayout aboutus;
    public LinearLayout changecount;
    public LinearLayout viewall;


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
                        Intent intent = new Intent();
                        intent.setClass(MySettingActivity.this, LoginActivity.class);
                        startActivity(intent);
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


        //退出账号
        exit_bu=(Button) findViewById(R.id.certain_bu_myset);
        exit_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                builder.setMessage("确认要退出么？");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                        Intent intent = new Intent();
                        intent.setClass(MySettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                        //finish();
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



  /**      viewall=(LinearLayout)findViewById(R.id.layoutall_myset);
        viewall.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.changephone://进入手机
                    {
                        Intent intent = new Intent();
                        intent.setClass(MySettingActivity.this, ChangePhoneActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case R.id.changepsw://修改密码
                    {
                        Intent intent = new Intent();
                        intent.setClass(MySettingActivity.this, ChangePswActivity.class);
                        //  intent.putExtra("id",2);
                        startActivity(intent);
                    }
                    break;
                    case R.id.aboutus://关于我们
                    {
                        Intent intent = new Intent();
                        intent.setClass(MySettingActivity.this, AboutUsActivity.class);
                        //  intent.putExtra("id",2);
                        startActivity(intent);
                    }
                    break;
                    case R.id.changecount://切换账号
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                        builder.setMessage("确认要退出么？");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();

                                Intent intent = new Intent();
                                intent.setClass(MySettingActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
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
                    break;
                    case R.id.certain_bu_myset:
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MySettingActivity.this);
                        builder.setMessage("确认要退出么？");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();

                                Intent intent = new Intent();
                                intent.setClass(MySettingActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
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
                }


            }
        });
**/
    }


}
