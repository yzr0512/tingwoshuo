package com.cqu.shixun.tingwoshuo.ui.ListenRecordView;

import android.content.Context;
import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.BytesResponseHandler;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.cqu.shixun.tingwoshuo.ui.AskRecordView.IAskRecordAdapterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ListenRecordAdapterPresenterImpl implements IListenRecordAdapterPresenter {
    IListenRecordAdapterView iListenRecordAdapterView;
    Context context;
    User currUser;

    public ListenRecordAdapterPresenterImpl(IListenRecordAdapterView iListenRecordAdapterView, Context context){
        this.iListenRecordAdapterView = iListenRecordAdapterView;
        this.context = context;
    }


    @Override
    public void getQuestionAnswer(final Question question, final User user) {
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/answer")
                .addParam("phone", user.getPhone())
                .addParam("md5", user.getMd5())
                .addParam("questionID", Integer.toString(question.getId()))
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){
                                Answer answer = new Answer(response.getInt("id"));
//                                answer.setAppend(false);
                                answer.setQuestionID(response.getInt("questionID"));
                                answer.setAnswerPath(context.getExternalFilesDir("").getAbsolutePath() + "/" + "question_" + answer.getQuestionID() + ".mp3");
                                downloadAnswer(answer);

                            }else{

                                iListenRecordAdapterView.showMessage("未知错误");
                            }

                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iListenRecordAdapterView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iListenRecordAdapterView.showMessage(error_msg);
                    }
                });
    }

    void downloadAnswer(final Answer answer) {
        File file = new File(answer.getAnswerPath());
        if (file.exists()) {
            Log.d("debug", "文件存在");
//            iListenRecordAdapterView.play(answer);
//            return;
            file.delete();
        }

        String url = "http://119.29.105.37:8000/answerFile";
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url(url)
                .addParam("answerID", Integer.toString(answer.getId()))
                .enqueue(new BytesResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, byte[] response) {

                        FileOutputStream outputStream = null;
                        BufferedOutputStream bufferedOutputStream = null;
                        try {

                            File file = new File(answer.getAnswerPath());
                            file.createNewFile();
                            // 创建FileOutputStream对象
                            outputStream = new FileOutputStream(file);
                            // 创建BufferedOutputStream对象
                            bufferedOutputStream = new BufferedOutputStream(outputStream);
                            // 往文件所在的缓冲输出流中写byte数据
                            bufferedOutputStream.write(response);
                            // 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
                            bufferedOutputStream.flush();

                            iListenRecordAdapterView.play(answer);
//                            iListenView.showMessage("Succsess");

                        } catch (FileNotFoundException e) {
                            iListenRecordAdapterView.showMessage("FileNotFoundException：" + e.getMessage());
                        } catch (IOException e) {
                            iListenRecordAdapterView.showMessage("IOException：" + e.getMessage());
                        } finally {
                            // 关闭创建的流对象
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iListenRecordAdapterView.showMessage(error_msg);
                    }
                });
    }
}
