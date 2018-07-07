package com.cqu.shixun.tingwoshuo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.cqu.shixun.tingwoshuo.R;

public class BecomeExpertActivity extends AppCompatActivity {
    public Button myback_bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_expert);

        myback_bu=(Button)findViewById(R.id.back_bu_becex);

        myback_bu.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BecomeExpertActivity.this, MainActivity.class);
                //  intent.putExtra("id",2);
                startActivity(intent);
            }
        });

    }
}
