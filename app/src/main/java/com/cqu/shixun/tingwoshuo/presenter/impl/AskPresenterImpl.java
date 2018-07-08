package com.cqu.shixun.tingwoshuo.presenter.impl;

import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IAskPresenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IAskView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AskPresenterImpl implements IAskPresenter {

    IAskView iAskView;

    public AskPresenterImpl(IAskView iAskView){
        this.iAskView = iAskView;
    }

    // 提交问题
    @Override
    public void postQuestion(User user, Question question) {

        MyOkHttp myOkHttp = new MyOkHttp();
        JSONObject quesObject = new JSONObject();
        Map<String, String> params = new HashMap<>();
        try {
            quesObject.put("questionerID", question.getQuestionerID());
            quesObject.put("responderID", question.getResponderID());
            quesObject.put("category", question.getCategory());
            quesObject.put("content", question.getContent());
            quesObject.put("price", question.getPrice());

            params.put("phone", user.getPhone());
            params.put("md5", user.getMd5());
            params.put("question", quesObject.toString());


//            quesObject.put("questionerID", 2);
//            quesObject.put("responderID", 1);
//            quesObject.put("category", "房产");
//            quesObject.put("content", "这是提问的内容");
//            quesObject.put("price", 2);
//
//            params.put("phone", "18888888881");
//            params.put("md5", "7cc2d38cbeec6ca21026d5a3794d2988");
//            params.put("question", quesObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        myOkHttp.post().url("http://119.29.105.37:8000/ask")
//                .addParam("data", jsonObject.toString())
                .params(params)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String res = response.getString("result");
                            if(res.equals("ture")){
                                int questionID = response.getInt("questionID");
                                iAskView.askSuccess();
                            }else{
                                String description = response.getString("description");
                                if(description.equals("balance not enough")){
                                    float balance = Float.valueOf(response.get("balance").toString());
                                    float required = Float.valueOf(response.get("required").toString());
                                    iAskView.balanceNotEnough(balance, required);
                                }else{
                                    iAskView.showMessage(description);
                                }
                            }

                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iAskView.showMessage("JSONException：" + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        //Log.d("Get失败：", Integer.toString(statusCode));
                        iAskView.showMessage(error_msg);
                    }
                });

    }


    @Override
    public void getExpertInfo(int userID) {

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

                            iAskView.showExpertInfo(user);
                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iAskView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iAskView.showMessage(error_msg);
                    }
                });
    }
}
