package com.cqu.shixun.tingwoshuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.cqu.shixun.tingwoshuo.R;

public class RechargeActivity extends AppCompatActivity {
    public Button myset_bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        myset_bu=(Button)findViewById(R.id.button_backward);

        myset_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RechargeActivity.this, MainActivity.class);
              //  intent.putExtra("id",2);
               startActivity(intent);
            }
        });
    }
}
