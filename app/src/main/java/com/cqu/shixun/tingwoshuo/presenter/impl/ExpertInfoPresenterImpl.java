package com.cqu.shixun.tingwoshuo.presenter.impl;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.Category;
import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IExpertInfoPresenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IExpertInfoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExpertInfoPresenterImpl implements IExpertInfoPresenter {

    IExpertInfoView iExpertInfoView;

    public void ExpertListPresentImpl(IExpertInfoView iExpertInfoView){
        this.iExpertInfoView = iExpertInfoView;
    }

    @Override
    public void getExpertInfo(final int userID) {

        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/expertInfo").addParam("userID", Integer.toString(userID))
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            JSONObject userInfoJson = response.getJSONObject("userInfo");
                            User user = new User(userInfoJson.getInt("id"));    // 答主
                            user.setName(userInfoJson.getString("name"));
                            user.setTitle(userInfoJson.getString("title"));
                            user.setAnsNum(userInfoJson.getInt("ansNum"));
                            user.setAskPrice(Float.valueOf(userInfoJson.get("askPrice").toString()));
                            user.setCategory(userInfoJson.getString("categoryName"));

                            JSONObject questionListJson = response.getJSONObject("questionList");
                            List<Question> questions = new ArrayList<Question>();
                            int n = response.getInt("total");
                            for(int i = 1; i <= n; i++) {
                                JSONObject questionJson = questionListJson.getJSONObject(Integer.toString(i));
                                Question question = new Question();
                                question.setId(questionJson.getInt("id"));
                                question.setQuestionerName(questionJson.getString("questionName"));
                                question.setContent(questionJson.getString("content"));
                                question.setResponderName(user.getName());
                                question.setResponderID(user.getId());
                                question.setListenNum(questionJson.getInt("listenNum"));
                                question.setCategory(questionJson.getString("category"));

                                questions.add(question);
                            }
                            iExpertInfoView.showExpertInfo(user, questions);
                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iExpertInfoView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iExpertInfoView.showMessage(error_msg);
                    }
                });


    }
}
