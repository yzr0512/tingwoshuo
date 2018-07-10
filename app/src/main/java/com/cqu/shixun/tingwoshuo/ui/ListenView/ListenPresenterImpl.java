package com.cqu.shixun.tingwoshuo.ui.ListenView;

import android.content.Context;
import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.BytesResponseHandler;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.IResponseHandler;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.RawResponseHandler;
import com.cqu.shixun.tingwoshuo.model.myokhttp.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListenPresenterImpl implements IListenPresenter {

    IListenView iListenView;
    Context context;
    public ListenPresenterImpl(IListenView iListenView, Context context){
        this.iListenView = iListenView;
        this.context = context;
    }

    @Override
    public void getQuestionList(String category) {
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/questionList")
                .addParam("categoryName", category)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String result = response.getString("result");
                            if(result.equals("true")) {
                                List<Question> questions = new ArrayList<Question>();
                                int n = response.getInt("total");
                                for (int i = 1; i <= n; i++) {
                                    JSONObject questionJson = response.getJSONObject(Integer.toString(i));
                                    Question question = new Question(questionJson.getInt("id"));

                                    question.setQuestionerID(questionJson.getInt("questionerID"));
                                    question.setQuestionerName(questionJson.getString("questionerName"));
                                    question.setResponderID(questionJson.getInt("responderID"));
                                    question.setResponderName(questionJson.getString("responderName"));

                                    question.setContent(questionJson.getString("content"));
                                    question.setListenNum(questionJson.getInt("listenNum"));
                                    question.setCategory(questionJson.getString("category"));
                                    question.setListenPrice(Float.valueOf(questionJson.get("listenPrice").toString())); // 暂时全部设置为1元
                                    question.setPrice(Float.valueOf(questionJson.get("price").toString()));

                                    questions.add(question);
                                }
                                iListenView.showQuestionList(questions);
                            }else{
                                String msg = response.getString("description");
                                iListenView.showMessage(msg);
                            }


                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iListenView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iListenView.showMessage(error_msg);
                    }
                });





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
                                String msg = response.getString("description");
                                if(msg.equals("偷听支付")){
                                    // 此处通知前端让用户支付
                                    int price = response.getInt("price");
                                    if(iListenView.showPayRequest(price)){
                                        addListenRecord(question, user);
                                    }
                                }else{
                                    iListenView.showMessage(msg);
                                }
                            }
                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iListenView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iListenView.showMessage(error_msg);
                    }
                });
    }


    void downloadAnswer(final Answer answer) {
        File file = new File(answer.getAnswerPath());
        if (file.exists()) {
            Log.d("debug", "文件存在");
            iListenView.setAnswer(answer);
            return;
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

                            iListenView.setAnswer(answer);
//                            iListenView.showMessage("Succsess");

                        } catch (FileNotFoundException e) {
                            iListenView.showMessage("FileNotFoundException：" + e.getMessage());
                        } catch (IOException e) {
                            iListenView.showMessage("IOException：" + e.getMessage());
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
                        iListenView.showMessage(error_msg);
                    }
                });
    }

    // 添加偷听消费记录
    void addListenRecord(final Question question, final User user){
        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/addLstRcrd";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        params.put("questionID", Integer.toString(question.getId()));
//        params.put("phone", phone);
//        params.put("pwd", pwd);

        myOkHttp.post().url(url)
                .params(params)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){
                                // 支付成功
//                                String msg = response.getString("description");
                                getQuestionAnswer(question, user);  // 重新执行获取过程
                            }else{
                                String description = response.getString("description");
                                iListenView.showMessage(description);
                            }

                        }catch (JSONException e){
                            iListenView.showMessage("JSONException：" + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iListenView.showMessage(error_msg);
                    }
                });
    }

}
