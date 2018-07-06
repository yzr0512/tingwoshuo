package com.cqu.shixun.tingwoshuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import com.cqu.shixun.tingwoshuo.presenter.IChangePswPresenter;
import com.cqu.shixun.tingwoshuo.presenter.ChangePswPresenterImpl;
import com.cqu.shixun.tingwoshuo.iView.IChangePswView;

public class ChangePswActivity extends AppCompatActivity implements IChangePswView, View.OnClickListener{
    private Button buchangepsw;
    private Button buback;
    private EditText edittextpsw1;
    private EditText edittextpsw2;
    IChangePswPresenter changepswPresenter; // MVP模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psw);

        edittextpsw1 = (EditText) findViewById(R.id.mima_text_changpsw);
        edittextpsw1.setOnClickListener(this);
        edittextpsw2 = (EditText) findViewById(R.id.mima2_text_changpsw);
        edittextpsw2.setOnClickListener(this);
        buchangepsw=(Button)findViewById(R.id.certain_bu_changepsw);
        buchangepsw.setOnClickListener(this);
        buback=(Button)findViewById(R.id.back_bu_changepsw);
        buback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mima_text_changpsw:
            {
                edittextpsw1.setText("");
            }
            break;
            case R.id.mima2_text_changpsw:
            {
                edittextpsw2.setText("");
            }
            break;
            case R.id.certain_bu_changepsw:
            {
                if(edittextpsw1.getText().toString().trim().equals("") || edittextpsw2.getText().toString().trim().equals("")){
                    Toast.makeText(ChangePswActivity.this,"新密码和确认密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!edittextpsw1.getText().toString().equals(edittextpsw2.getText().toString())){
                    Toast.makeText(ChangePswActivity.this,"密码和确认密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                    }else{
                            //如何获得自己的手机号
                   // changepswPresenter.changepsw(editTextPhone.getText().toString(), edittextpsw2.getText().toString());
                }

            }
            break;
            case R.id.back_bu_changepsw:
            {
                Intent intent = new Intent();
                intent.setClass(ChangePswActivity.this, MySettingActivity.class);
                startActivity(intent);
            }

        }

    }

    @Override
    public void changeSuccess() {
        Intent intent = new Intent();
        intent.setClass(ChangePswActivity.this, LoginActivity.class);
        //  intent.putExtra("id",2);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
