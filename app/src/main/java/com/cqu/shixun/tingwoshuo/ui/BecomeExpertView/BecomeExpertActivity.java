package com.cqu.shixun.tingwoshuo.ui.BecomeExpertView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.ui.activity.MainActivity;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

public class BecomeExpertActivity extends AppCompatActivity implements IBecomeExpertView,View.OnClickListener{
    public Button myback_bu;
    public Button butcerten;
    public EditText editTextinfo;
    public EditText editTexttitle;
    public EditText editTextprice;
    private TextView view;
    private Spinner spinner;
   // private ArrayAdapter adapter;
    private static final String[] m={"房产","理财","情感","法律"};
    private ArrayAdapter<String> adapter;
    MyApplication myApp = (MyApplication) getApplication();//当前用户APP

    IBecomeExpertPresenter becomeExpertPresenter; // MVP模式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_expert);


       // view = (TextView) findViewById(R.id.jiejietwo);
        spinner = (Spinner) findViewById(R.id.spinner2);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);

        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        spinner.setVisibility(View.VISIBLE);





        becomeExpertPresenter = new BecomeExpertPresentImpl(this);  // 绑定presenter

        editTextinfo = (EditText) findViewById(R.id.Introduction);
        editTextinfo.setOnClickListener(this);

        editTexttitle = (EditText) findViewById(R.id.title_becexp);
        editTexttitle.setOnClickListener(this);

        editTextprice = (EditText) findViewById(R.id.price_becexp);
        editTextprice.setOnClickListener(this);

        butcerten=(Button)findViewById(R.id.certain_bu_becex);
        butcerten.setOnClickListener(this);

        myback_bu=(Button)findViewById(R.id.back_bu_becex);
        myback_bu.setOnClickListener(this);


    }


    //使用数组形式操作
    class SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            //String selectecategory=m[arg2];//获得所选择的值
            arg0.setVisibility(View.VISIBLE);

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }




    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.certain_bu_becex:
            {
                if(editTextinfo.getText().toString().trim().equals("") || editTexttitle.getText().toString().trim().equals("")){
                    Toast.makeText(BecomeExpertActivity.this,"简介和头衔不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    String selectecategory =spinner.getSelectedItem().toString();//获取当前值
                    myApp.getCurrUser().setCategory(selectecategory);//调用全部变量，填写分类

                    myApp.getCurrUser().setIntro(editTextinfo.getText().toString());//简介

                    myApp.getCurrUser().setTitle(editTexttitle.getText().toString());//头衔

                    float  price  =  Float.parseFloat(editTextprice.getText().toString());
                    myApp.getCurrUser().setAskPrice(price);//提问价格

                    becomeExpertPresenter.postUser(myApp.getCurrUser());//传回类
                }


            }
            break;
            case R.id.back_bu_becex:
            {
                Intent intent = new Intent();
                intent.setClass(BecomeExpertActivity.this, MainActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
            break;
            case R.id.Introduction:
                editTextinfo.setText("");
                break;
            case R.id.title_becexp:
                editTexttitle.setText("");
                break;

        }


    }




    //开通成功页面跳转
    @Override
    public void openSuccess() {


        Toast.makeText(BecomeExpertActivity.this,"开通成功！",Toast.LENGTH_SHORT).show();



//
    }


    @Override
    public void showMessage(String msg) {
//  Log.d("Login", msg);    // 待补充
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
//        editTextPwd.setText("");
        }


}
