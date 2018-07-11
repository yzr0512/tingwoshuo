package com.cqu.shixun.tingwoshuo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.cqu.shixun.tingwoshuo.R;

public class AboutUsActivity extends AppCompatActivity {
    public Button btnMyback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //返回
        btnMyback=(Button)findViewById(R.id.button_backward);
        btnMyback.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent();
//                intent.setClass(AboutUsActivity.this, MySettingActivity.class);
//                //  intent.putExtra("id",2);
//                startActivity(intent);
            }
        });
    }
}
