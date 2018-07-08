package com.cqu.shixun.tingwoshuo.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IAskPresenter;
import com.cqu.shixun.tingwoshuo.presenter.impl.AskPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.iView.IAskView;

public class WriteQuestionActivity extends AppCompatActivity implements IAskView {

    IAskPresenter iAskPresenter;

    private Button buask;
    private EditText editcontent;
    IAskPresenter iAskPresenter;
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

       // buask.setText(Float.toString());//显示数据
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

                    Question question=new Question();
                    question.setContent(editcontent.getText().toString());//内容
                    question.setQuestionerID( ((MyApplication) getApplication()).user.getId());//提问者ID

                    //  question.setResponderID(expertID);//回答者ID、价格、分类

                    iAskPresenter.postQuestion(((MyApplication) getApplication()).user,question);

                }

            }
            break;

        }

    }



    @Override
    public void askSuccess() {

    }

    @Override
    public void showMessage(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
