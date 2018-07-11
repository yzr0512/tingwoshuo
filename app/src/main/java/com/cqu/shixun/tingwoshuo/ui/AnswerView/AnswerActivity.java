package com.cqu.shixun.tingwoshuo.ui.AnswerView;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cqu.shixun.tingwoshuo.MyApplication;
import com.cqu.shixun.tingwoshuo.R;
import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;

import com.czt.mp3recorder.Mp3Recorder;
import com.czt.mp3recorder.Mp3RecorderUtil;

import java.io.IOException;
import java.util.Date;


public class AnswerActivity extends AppCompatActivity implements IAnswerView, View.OnClickListener,View.OnTouchListener{

    private AudioRecoderDialog recoderDialog;
    IAnswerPresenter iAnswerPresenter;
    TextView name_answer;
    TextView money_answer;
    TextView question_answer;
    Button back_bu_answer;
    TextView btnRecord;
    Button btnPlay;
    Button refuse_bu;
    String filePath;
    Question question;
    Mp3Recorder mMp3Recorder;
    private long downT;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            //还有设置View的高度，因为每个型号的手机状态栏高度都不相同
        }

        recoderDialog = new AudioRecoderDialog(this);
        recoderDialog.setShowAlpha(0.98f);

        name_answer=(TextView)findViewById(R.id.name_answer);
        money_answer=(TextView)findViewById(R.id.money_answer);
        question_answer=(TextView)findViewById(R.id.question_answer);
        refuse_bu=(Button)findViewById(R.id.refuse_bu);
        back_bu_answer=(Button)findViewById(R.id.back_bu_answer);
        btnRecord = (TextView)findViewById(R.id.answer_bu);
        btnPlay=(Button)findViewById(R.id.play_bu);
        btnPlay.setVisibility(View.INVISIBLE);


        back_bu_answer.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        refuse_bu.setOnClickListener(this);
        btnRecord.setOnTouchListener(this);
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
                btnRecord.setBackgroundResource(R.drawable.microphone);

            }

            @Override
            public void onReset() {
                // 重置
            }

            @Override
            public void onRecording(double v, double v1) {
                //已经录制的时间v, 实时音量分贝值v1
                if(null != recoderDialog) {
                    int level = (int) v1;
                    recoderDialog.setLevel((int)v1);
                    recoderDialog.setTime(System.currentTimeMillis() - downT);
                }

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
            case R.id.play_bu:
                if(isRecording==0){
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(filePath);
                        mediaPlayer.prepare();
                        mediaPlayer.setLooping(false);
                        mediaPlayer.start();
                        isRecording=1;
                        btnPlay.setText("播放中");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    isRecording=0;
                    mediaPlayer.pause();
                    btnPlay.setText("暂停中");
                }
                break;
            case R.id.back_bu_answer:
                finish();
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

//        filePath = this.getExternalFilesDir("").getAbsolutePath() + "/" + "question_"  + question.getId() + ".mp3";
//        filePath = this.getExternalFilesDir("").getAbsolutePath() + "/" + "question_"  + question.getId() + "_" + new Date().getTime() +".mp3";
//        Log.d("路径",filePath);
        name_answer.setText(question.getQuestionerName());
        question_answer.setText(question.getContent());
        money_answer.setText(Float.toString(question.getPrice()) + "听币");

    }

    @Override
    public void showMessage(String msg) {
        // 出错时调用此函数
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId()==R.id.answer_bu){

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Toast.makeText(AnswerActivity.this,"222",Toast.LENGTH_SHORT).show();
                    mMp3Recorder.setOutputFile(filePath);
                    mMp3Recorder.setMaxDuration(60);
                    mMp3Recorder.start();
                    downT=System.currentTimeMillis();
                    recoderDialog.showAtLocation((View) v.getParent(), Gravity.CENTER,0,0);
                    btnRecord.setBackgroundResource(R.drawable.shape_recoder_btn_recoding);
                    return true;
                case MotionEvent.ACTION_UP:
                    Toast.makeText(AnswerActivity.this,"333",Toast.LENGTH_SHORT).show();
                    mMp3Recorder.stop(Mp3Recorder.ACTION_STOP_ONLY);
                    mMp3Recorder.reset();
                    recoderDialog.dismiss();
                    btnRecord.setBackgroundResource(R.drawable.shape_recoder_btn_normal);

                    btnPlay.setVisibility(View.VISIBLE);

                    // 提交答案
                    Answer answer = new Answer(0);
                    answer.setAnswerPath(filePath);
                    answer.setQuestionID(question.getId());
                    answer.setAppend(false);
                    iAnswerPresenter.postAnswer(((MyApplication)getApplication()).getCurrUser(), answer);

                    return true;
            }


        }
        return false;
    }


}
