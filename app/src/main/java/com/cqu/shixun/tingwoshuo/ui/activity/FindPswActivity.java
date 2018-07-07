package com.cqu.shixun.tingwoshuo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;
import android.os.CountDownTimer;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.ui.iView.IFindPswView;
import com.cqu.shixun.tingwoshuo.presenter.impl.FindPswPresenterImpl;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IFindPswPresenter;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FindPswActivity extends AppCompatActivity implements IFindPswView, View.OnClickListener{
    private Button findpsw_bu;
    private Button btnGetCode;//获取验证码
    private EditText editTextphone;//手机号
    private EditText editTextcomf;//验证码
    private EditText editTextpsw1;//密码
    private EditText editTextpsw2;//确认密码

    public EventHandler eh;
    private TimeCount mTimeCount;
    IFindPswPresenter iFindPswPresenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);


        iFindPswPresenter = new FindPswPresenterImpl(this);//绑定
        // 定时器 用于限制验证码获取频率
        mTimeCount = new TimeCount(60000, 1000);



        btnGetCode=(Button)findViewById(R.id.getinf_bu_fsw);
        btnGetCode.setOnClickListener(this);
        //找回密码操作
        context=this;
        editTextphone = (EditText) findViewById(R.id.phone_text_fsw);
        editTextphone.setOnClickListener(this);
        editTextpsw1 = (EditText) findViewById(R.id.newmima_text_fsw);
        editTextpsw1.setOnClickListener(this);
        editTextpsw2 = (EditText) findViewById(R.id.newmimaconform_text_fsw);
        editTextpsw2.setOnClickListener(this);
        editTextcomf = (EditText) findViewById(R.id.conform_text_fsw);
        editTextcomf.setOnClickListener(this);
        findpsw_bu=(Button)findViewById(R.id.certain_bu_fsw);
        findpsw_bu.setOnClickListener(this);

      /*  findpsw_bu.setOnClickListener(new View.OnClickListener() {
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

        */

// 初始化SDK
        SMSSDK.initSDK(this, "269d5abf647fa", "f8cfecdcc56965d22f12c86e14b028f0");
        init();
    }

    private void init(){
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功

                        //startActivity(new Intent(RegisterView.this, MainActivity.class)); //页面跳转

                        iFindPswPresenter.findpsw(editTextphone.getText().toString(), editTextpsw2.getText().toString());

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //短信回调
    }

    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer{

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            btnGetCode.setClickable(false);
            btnGetCode.setText(l/1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            btnGetCode.setClickable(true);
            btnGetCode.setText("获取验证码");
        }
    }



    @Override
    public void onClick(View view) {
        Toast.makeText(FindPswActivity.this,"111",Toast.LENGTH_SHORT).show();
        switch (view.getId()){

            case R.id.getinf_bu_fsw://验证码
            {
                if (!editTextphone.getText().toString().trim().equals("")) {
                    if (checkTel(editTextphone.getText().toString().trim())) {
                        SMSSDK.getVerificationCode("+86", editTextphone.getText().toString());
                        mTimeCount.start();
                    } else {
                        Toast.makeText(FindPswActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(FindPswActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.certain_bu_fsw://确定找回
            {
                if(editTextphone.getText().toString().trim().equals("")) {
                    Toast.makeText(FindPswActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextpsw1.getText().toString().trim().equals("") || editTextpsw2.getText().toString().trim().equals("")){
                    Toast.makeText(FindPswActivity.this,"新密码和确认密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!editTextpsw1.getText().toString().equals(editTextpsw2.getText().toString())){
                    Toast.makeText(FindPswActivity.this,"密码和确认密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextcomf.getText().toString().trim().equals("")) {
                    Toast.makeText(FindPswActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            SMSSDK.submitVerificationCode("+86",editTextphone.getText().toString().trim(),editTextcomf.getText().toString().trim());
            break;
        }





    }

    @Override
    public void findpswSuccess() {
        Intent intent = new Intent();
        intent.setClass(FindPswActivity.this, LoginActivity.class);
        //  intent.putExtra("id",2);
        startActivity(intent);


    }

    @Override
    public void showMessage(String msg) {

        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();

    }
}
