package com.cqu.shixun.tingwoshuo.ui.AnswerView;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.Answer;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.cqu.shixun.tingwoshuo.ui.AnswerView.IAnswerPresenter;
import com.cqu.shixun.tingwoshuo.ui.AnswerView.IAnswerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class AnswerPresenterImpl implements IAnswerPresenter {

    IAnswerView iAnswerView;

    public AnswerPresenterImpl(IAnswerView iAnswerView){
        this.iAnswerView = iAnswerView;
    }
    @Override
    public void getQuestionInfo(int questionID) {

        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/questionBrief").addParam("questionID", Integer.toString(questionID))
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String res = response.getString("result");
                            if(res.equals("false")){
                                String msg = response.getString("description");
                                iAnswerView.showMessage(msg);
                            }else{
                                Question question = new Question(response.getInt("questionID"));
                                question.setPrice(Float.valueOf(response.get("price").toString()));
                                question.setQuestionerName(response.getString("questionerName"));
                                question.setQuestionerID(response.getInt("questionerID"));
                                question.setResponderName(response.getString("responderName"));
                                question.setResponderID(response.getInt("responderID"));
                                question.setContent(response.getString("content"));
                                question.setCategory(response.getString("categoryName"));
                                question.setStatus(response.getString("status"));

                                iAnswerView.showQuestionInfo(question);
                            }
                        }catch (JSONException e){
                            iAnswerView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iAnswerView.showMessage(error_msg);
                    }
                });
    }

    @Override
    public void postAnswer(User user, Answer answer) {

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/answer";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        JSONObject answerObject = new JSONObject();

        try {
            answerObject.put("questionID", answer.getQuestionID());
            answerObject.put("isAppend", answer.getAppend());
            answerObject.put("answer", answer.getAnswer());
            answerObject.put("userID", user.getId());

            params.put("phone", user.getPhone());
            params.put("md5", user.getMd5());
            params.put("answer", answerObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        myOkHttp.upload().url(url)
                .params(params)
                .addFile("answer", new File(answer.getAnswer()))
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String res = response.getString("result");
//                            Log.d("res=", res);
                            if(res.equals("true")){
                                int ansID = response.getInt("answerID");    // 回答的id
                                int consumeRecord = response.getInt("consumeRecordID"); // 消费记录的id

                                iAnswerView.answerSuccess();
                            }else{
                                String description = response.getString("description");
                                iAnswerView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        //Log.d("Get失败：", Integer.toString(statusCode));
                        iAnswerView.showMessage(error_msg);
                    }
                });

    }
}
