package com.cqu.shixun.tingwoshuo.ui.RegisterView;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.ui.LoginView.LoginActivity;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView{
    private Button btnRegister;
    private Button btnGetCode;//获取验证码
    private EditText editTextName;//名字
    private EditText editTextPhone;//手机号
    private EditText editTextCode;//验证码
    private EditText editTextPsw1;//密码
    private EditText editTextPsw2;//确认密码
    private Context context;

    public EventHandler eh;
    private TimeCount mTimeCount;
    IRegisterPresenter iRegisterPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iRegisterPresenter = new RegisterPresenterImpl(this);

        // 定时器 用于限制验证码获取频率
        mTimeCount = new TimeCount(60000, 1000);

        btnGetCode=(Button)findViewById(R.id.getinf_bu_rge);
        btnGetCode.setOnClickListener(this);

        context=this;
        editTextName = (EditText) findViewById(R.id.name_text_reg);
        editTextPhone = (EditText) findViewById(R.id.phone_text_rge);
        editTextPsw1 = (EditText) findViewById(R.id.mima_text_rge);
        editTextPsw2 = (EditText) findViewById(R.id.mimaconform_text_rge);
        editTextCode = (EditText) findViewById(R.id.conform_text_reg);
        btnRegister=(Button)findViewById(R.id.rigister_bu_reg);
        btnRegister.setOnClickListener(this);
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text1 = editTextPsw1.getText().toString();//新密码
//                String text2 = editTextPsw2.getText().toString();//确认密码
//                String text3 = editTextCode.getText().toString();//验证码
//                //这里面有密码判断和有验证码判断
//                if(true) {
//                    //实现数据库修改和界面跳转
//
//                    Intent intent = new Intent();
//                    intent.setClass(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
//
//                }
//                else {
//
//                    if(!(text1.equals(text2))){//密码不相同
//                        Toast t = Toast.makeText(context,"密码不相同，请重新输入！", Toast.LENGTH_LONG);
//                        t.show();
//                        editTextPsw2.setText("");
//                    }
//                    else{//验证码不正确
//                        Toast t = Toast.makeText(context,"验证码不正确，请重新输入！", Toast.LENGTH_LONG);
//                        t.show();
//                        editTextCode.setText("");
//                    }
//
//                }
//
//
//            }
//        });

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
                        iRegisterPresenter.register(editTextPhone.getText().toString(), editTextPsw1.getText().toString(), editTextName.getText().toString());

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
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

    @Override
    public void onClick(View view) {
//        Toast.makeText(RegisterActivity.this,"111",Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.getinf_bu_rge:

                if(!editTextPhone.getText().toString().trim().equals("")){
                    if (checkTel(editTextPhone.getText().toString().trim())){
                        SMSSDK.getVerificationCode("+86",editTextPhone.getText().toString());
                        mTimeCount.start();
                    }else {
                        Toast.makeText(RegisterActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rigister_bu_reg:
                if(editTextPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkTel(editTextPhone.getText().toString().trim())){
                    Toast.makeText(RegisterActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextCode.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextPsw1.getText().toString().trim().equals("") || editTextPsw2.getText().toString().trim().equals("")){
                    Toast.makeText(RegisterActivity.this,"密码和确认密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!editTextPsw1.getText().toString().equals(editTextPsw2.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"密码和确认密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                // 测试阶段不做短信验证
                SMSSDK.submitVerificationCode("+86",editTextPhone.getText().toString().trim(),editTextCode.getText().toString().trim());

//                iRegisterPresenter.register(editTextPhone.getText().toString(), editTextPsw1.getText().toString(), editTextName.getText().toString());
                break;
        }
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
    public void registerSuccess() {

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class)); //页面跳转
    }

    @Override
    public void showMessage(String msg) {
        Log.d("Register", msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }
}
