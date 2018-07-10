package com.cqu.shixun.tingwoshuo.ui.AskView;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.ui.RechargeView.IRechargePresenter;
import com.cqu.shixun.tingwoshuo.ui.ExpertInfoView.ExpertInformationActivity;

import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

public class WriteQuestionActivity extends AppCompatActivity implements IAskView,View.OnClickListener{

    IAskPresenter iAskPresenter;
    IRechargePresenter rechargePresenter; // MVP模式
    private Button buask;
    private EditText editcontent;
    int responderID;    // 回答者ID
    String category;    // 分类
    float price;    // 问题价格
    MyApplication myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_question);

        Intent intent = getIntent();
        //intent.getStringExtra("userID");
        int expertID = intent.getIntExtra("userID", 0);//专家id

        buask=(Button)findViewById(R.id.writequestion_bu_writeq);
        buask.setOnClickListener(this);
        editcontent=(EditText)findViewById(R.id.question_writeq);
        editcontent.setOnClickListener(this);

        iAskPresenter = new AskPresenterImpl(this);
        iAskPresenter.getExpertInfo(expertID);

        myApp = (MyApplication) getApplication();//当前用户APP
    }


    @Override
    public void showExpertInfo(User user) {

        //显示信息
        float askprice = user.getBalance();
        buask.setText(Float.toString(askprice));
        responderID=user.getId();
        category=user.getCategory();
        price=user.getAskPrice();
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_bu_writeq:
            {
                Intent intent = new Intent();
                intent.setClass(WriteQuestionActivity.this, ExpertInformationActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
            break;
            case R.id.writequestion_bu_writeq:
            {
                if(editcontent.getText().toString().trim().equals("") ){
                    Toast.makeText(WriteQuestionActivity.this,"问题内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    //MyApplication myApp = (MyApplication) getApplication();

                    Question question=new Question(1);
                    question.setContent(editcontent.getText().toString());//内容
                    question.setQuestionerID(myApp.getCurrUser().getId());//提问者ID
                    //回答者ID、价格、分类
                    question.setResponderID(responderID);
                    question.setPrice(price);
                    question.setCategory(category);

                    //  question.setResponderID(expertID);

                    iAskPresenter.postQuestion(myApp.getCurrUser(),question);

                }

            }
            break;

        }

    }



    float temp;//差额
    @Override
    public void askSuccess() {
        Toast.makeText(WriteQuestionActivity.this,"提问成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void balanceNotEnough(float balance, float required) {

        temp=required-balance;
        AlertDialog.Builder builder = new AlertDialog.Builder(WriteQuestionActivity.this);
        builder.setMessage("余额不足，请问是否充值"+Float.toString(temp)+"个听币?");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                rechargePresenter.recharge(myApp.getCurrUser(),temp);//充值
                dialog.dismiss();
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

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }


}
