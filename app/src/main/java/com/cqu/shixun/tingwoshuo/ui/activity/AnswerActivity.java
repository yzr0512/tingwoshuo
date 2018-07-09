package com.cqu.shixun.tingwoshuo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cqu.shixun.tingwoshuo.R;

public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView name_answer;
    private TextView money_answer;
    private TextView question_answer;
    private Button refuse_bu;
    private Button answer_bu;
    private Button back_bu_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        PopupWindow mPop=new PopupWindow(this,null);

        name_answer=(TextView)findViewById(R.id.name_answer);
        money_answer=(TextView)findViewById(R.id.money_answer);
        question_answer=(TextView)findViewById(R.id.question_answer);
        refuse_bu=(Button)findViewById(R.id.refuse_bu);
        answer_bu=(Button)findViewById(R.id.answer_bu);
        back_bu_answer=(Button)findViewById(R.id.back_bu_answer);

        back_bu_answer.setOnClickListener(this);
        refuse_bu.setOnClickListener(this);
        answer_bu.setOnClickListener(this);
        Intent intent=getIntent();
        int expertID = intent.getIntExtra("AnswerQuesitionID", 0);


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.refuse_bu){

        }
        if(v.getId()==R.id.answer_bu){

        }
        if(v.getId()==R.id.back_bu_answer){
            AnswerActivity.this.setResult(3);
            AnswerActivity.this.finish();
        }
    }


}
