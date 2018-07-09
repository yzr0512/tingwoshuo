package com.cqu.shixun.tingwoshuo.ui.ListenRecordView;


import com.cqu.shixun.tingwoshuo.model.Question;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListenRecordPresenterImpl implements IListenRecordPresenter {

    IListenRecordView iListenRecordView;
    public ListenRecordPresenterImpl(IListenRecordView iListenRecordView){
        this.iListenRecordView = iListenRecordView;
    }

    @Override
    public void getListenRecordList(User user) {
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/mylisten")
                .addParam("phone", user.getPhone())
                .addParam("md5", user.getMd5())
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){
//                                JSONObject questionListJson = response.getJSONObject("questionList");
                                List<Question> questions = new ArrayList<>();
                                int n = response.getInt("total");
                                for(int i = 1; i <= n; i++) {
                                    JSONObject questionJson = response.getJSONObject(Integer.toString(i));
                                    Question question = new Question(questionJson.getInt("id")); // 问题的ID
                                    question.setQuestionerName(questionJson.getString("questionerName")); // 提问者名称
                                    question.setContent(questionJson.getString("content")); // 问题的内容
                                    question.setResponderName(questionJson.getString("responderName"));  // 回答者名称
//                                    question.setResponderID(questionJson.getInt("responderID")); // 回答者ID
                                    question.setListenNum(questionJson.getInt("listenNum")); // 偷听人数
                                    question.setCategory(questionJson.getString("category")); // 分类
//                                    question.setListenPrice(Float.valueOf(questionJson.get("listenPrice").toString())); // 暂时全部设置为1元
                                    question.setPrice(Float.valueOf(questionJson.get("price").toString())); // 提问价格
                                    question.setStatus(questionJson.getString("status")); // 问题状态
                                    question.setAnswerID(questionJson.getInt("answerID")); // 回答的ID

                                    questions.add(question);
                                }

                                iListenRecordView.showListenRecordList(questions);

                            }else{
                                String msg = response.getString("description");
                                iListenRecordView.showMessage(msg);
                            }

                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iListenRecordView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iListenRecordView.showMessage(error_msg);
                    }
                });
    }
}
