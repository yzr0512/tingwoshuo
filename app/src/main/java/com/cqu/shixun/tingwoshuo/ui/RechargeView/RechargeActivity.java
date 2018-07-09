package com.cqu.shixun.tingwoshuo.ui.RechargeView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.activity.MainActivity;

public class RechargeActivity extends AppCompatActivity implements IRechargeView,View.OnClickListener{
    public Button myset_bu;
    public Button buTen;//10
    public Button buTwenty;//20
    public Button buFifty;//50
    public Button buHundred;//100
    private TextView textBalance;//余额
    MyApplication myApp;
    String phone;

    MyApplication myApp;

    IRechargePresenter iRechargePresenter; // MVP模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        myApp = (MyApplication) getApplication();
       // phone=(myApp.getCurrUser().getPhone());//获取用户手机
        myApp = (MyApplication) getApplication();

        myset_bu=(Button)findViewById(R.id.button_backward);
        myset_bu.setOnClickListener(this);

        buTen=(Button)findViewById(R.id.tenTB);
        buTen.setOnClickListener(this);

        buTwenty=(Button)findViewById(R.id.twentyTB);
        buTwenty.setOnClickListener(this);

        buFifty=(Button)findViewById(R.id.fiftyTB);
        buFifty.setOnClickListener(this);

        buHundred=(Button)findViewById(R.id.hundredTB);
        buHundred.setOnClickListener(this);

        textBalance=(TextView)findViewById(R.id.money_count);
        textBalance.setOnClickListener(this);

        iRechargePresenter = new RechargePresenterImpl(this);
        iRechargePresenter.getBalance(((MyApplication) getApplication()).getCurrUser());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_backward:
            {

                Intent intent = new Intent();
                intent.setClass(RechargeActivity.this, MainActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);

            }
            break;
            case R.id.tenTB:
            {
                //支付密码判断什么的，不知道有没有

                iRechargePresenter.recharge(myApp.getCurrUser(),10);

            }
            break;
            case R.id.twentyTB:
            {
                iRechargePresenter.recharge(myApp.getCurrUser(),20);
            }
            break;
            case R.id.fiftyTB:
            {
                iRechargePresenter.recharge(myApp.getCurrUser(),50);
            }
            break;
            case R.id.hundredTB:
            {
                iRechargePresenter.recharge(myApp.getCurrUser(),100);
            }
            break;

        }




    }

    @Override
    public void rechargeInformation(User user) {

        float balance=user.getBalance();

        textBalance.setText(Float.toString(balance));

    }

    @Override
    public void rechargeSuccess() {

        Toast.makeText(RechargeActivity.this,"充值成功！",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(String msg) {

        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();

    }
}
