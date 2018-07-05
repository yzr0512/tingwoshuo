package com.cqu.shixun.tingwoshuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
public class AboutUsActivity extends AppCompatActivity {
    public Button myback_bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //返回
        myback_bu=(Button)findViewById(R.id.button_backward);
        myback_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(AboutUsActivity.this, MySettingActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });
    }
}
