package com.cqu.shixun.tingwoshuo.ui.LoginView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.FindPwdView.FindPswActivity;
import com.cqu.shixun.tingwoshuo.ui.activity.MainActivity;
import com.cqu.shixun.tingwoshuo.ui.RegisterView.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener{
    private Button btnRegister;
    private Button btnLogin;
    private Button btnFindPwd;
    private EditText editTextPhone;//账号
    private EditText editTextPwd;//密码
    ILoginPresenter loginPresenter; // MVP模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);  // 绑定presenter

        editTextPhone = (EditText) findViewById(R.id.count_tv_login);
        editTextPhone.setOnClickListener(this);
        editTextPwd = (EditText) findViewById(R.id.mima_tv_login);
        editTextPhone.setOnClickListener(this);

        //跳转注册界面
        btnRegister=(Button)findViewById(R.id.rigister_bu_login);
        btnRegister.setOnClickListener(this);

        //跳转找回密码界面
        btnFindPwd=(Button)findViewById(R.id.findpsw_bu_login);
        btnFindPwd.setOnClickListener(this);

        //登录操作
        btnLogin=(Button)findViewById(R.id.certen_bu_login);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d("btn", "loginBtn click");
        switch (view.getId()){
            case R.id.certen_bu_login: {
                Log.d("btn", "loginBtn click");
                loginPresenter.login(editTextPhone.getText().toString(), editTextPwd.getText().toString());
//                loginSuccess(new User(1));
            }
                break;
            case R.id.rigister_bu_login:
            {
                Log.d("btn", "regBtn click");
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.findpsw_bu_login: {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, FindPswActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.count_tv_login:
                editTextPhone.setText("");
                break;
            case R.id.mima_tv_login:
                editTextPwd.setText("");
                break;
        }
    }

    @Override
    public void loginSuccess(User user) {
//        Log.d("Login", "success");
        MyApplication myApp = (MyApplication) getApplication();
        myApp.setCurrUser(user);

        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        //  intent.putExtra("id",2);
        startActivity(intent);
    }


    @Override
    public void showMessage(String msg) {
//        Log.d("Login", msg);    // 待补充
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
//        editTextPwd.setText("");
    }

}
