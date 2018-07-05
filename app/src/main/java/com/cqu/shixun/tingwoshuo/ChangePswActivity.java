package com.cqu.shixun.tingwoshuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
public class ChangePswActivity extends AppCompatActivity {
    public Button myback_bu;
    public Button certain_bu;
    private EditText editTextone;
    private EditText editTexttwo;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psw);

        //返回
        myback_bu=(Button)findViewById(R.id.button_backward);
        myback_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChangePswActivity.this, MySettingActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        context=this;
        certain_bu=(Button)findViewById(R.id.certain_bu3);
        editTextone = (EditText) findViewById(R.id.newpsw1_text);
        editTexttwo = (EditText) findViewById(R.id.cnewpsw2_text);
        certain_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = editTextone.getText().toString();//新密码
                String text2 = editTexttwo.getText().toString();//确认密码

                if(text1.equals(text2)) {
                    //实现数据库修改和界面跳转



                }
                else {
                    Toast t = Toast.makeText(context,"密码不相同，请重新输入！", Toast.LENGTH_LONG);
                    t.show();
                    editTexttwo.setText("");
                }
            }
        });
    }
}
