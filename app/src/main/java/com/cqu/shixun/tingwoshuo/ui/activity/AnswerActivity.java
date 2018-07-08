package com.cqu.shixun.tingwoshuo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IAnswerPresenter;
import com.cqu.shixun.tingwoshuo.presenter.impl.AnswerPresenterImpl;
import com.cqu.shixun.tingwoshuo.ui.iView.IAnswerView;

import com.czt.mp3recorder.Mp3Recorder;
import com.czt.mp3recorder.Mp3RecorderUtil;

import java.util.Date;


public class AnswerActivity extends AppCompatActivity implements IAnswerView, View.OnClickListener{

    IAnswerPresenter iAnswerPresenter;

    Button btnRecord;

    String filePath;
    Question question;
    Mp3Recorder mMp3Recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);



        btnRecord = (Button)findViewById(R.id.answer_bu);
        btnRecord.setOnClickListener(this);
        iAnswerPresenter = new AnswerPresenterImpl(this);
        // 从前一个界面传递问题的id过来
        int questionID = getIntent().getIntExtra("questionID", 0);
        iAnswerPresenter.getQuestionInfo(questionID);

        Mp3RecorderUtil.init(this , true);
        mMp3Recorder = new Mp3Recorder().setCallback(new Mp3Recorder.Callback() {
            // 界面效果若要根据录音来响应就在下面这几个方法添加实现代码
            @Override
            public void onStart() {
                // 开始
            }

            @Override
            public void onPause() {
                // 暂停
            }

            @Override
            public void onResume() {
                // 恢复
            }

            @Override
            public void onStop(int i) {
                // 停止
            }

            @Override
            public void onReset() {
                // 重置
            }

            @Override
            public void onRecording(double v, double v1) {
                //已经录制的时间v, 实时音量分贝值v1

            }

            @Override
            public void onMaxDurationReached() {

            }
        });

        // 动态获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermisssion = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            if (checkCallPhonePermisssion != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                    ActivityCompat.requestPermissions(AnswerActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 111);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]  {Manifest.permission.RECORD_AUDIO}, 111);
                }

            } else {

            }
        }

    }

    int isRecording = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.answer_bu:
                if(isRecording == 0){
                    // 下面两句要替换效果
                    Toast t = Toast.makeText(this, "Start", Toast.LENGTH_LONG);
                    t.show();

                    isRecording = 1;
                    mMp3Recorder.setOutputFile(filePath);
                    mMp3Recorder.setMaxDuration(60);
                    mMp3Recorder.start();

                }else{
                    // 下面两句要替换效果
                    Toast t = Toast.makeText(this, "Stop", Toast.LENGTH_LONG);
                    t.show();

                    isRecording = 0;
                    mMp3Recorder.stop(Mp3Recorder.ACTION_STOP_ONLY);
                    mMp3Recorder.reset();

                    // 提交答案
                    Answer answer = new Answer(0);
                    answer.setAnswer(filePath);
                    answer.setQuestionID(question.getId());
                    answer.setAppend(false);
                    iAnswerPresenter.postAnswer(((MyApplication)getApplication()).getCurrUser(), answer);
                }
                break;
        }
    }

    @Override
    public void answerSuccess() {
        // 回答上传成功时调用此函数
    }

    @Override
    public void showQuestionInfo(Question question) {
        // 获取到问题时调用此函数
        this.question = question;
        filePath = this.getExternalFilesDir("").getAbsolutePath() + "/" + "question_"  + question.getId() + "_" + new Date().getTime() +".mp3";

    }

    @Override
    public void showMessage(String msg) {
        // 出错时调用此函数
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
