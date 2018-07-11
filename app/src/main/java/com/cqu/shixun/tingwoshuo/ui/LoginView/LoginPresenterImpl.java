package com.cqu.shixun.tingwoshuo.ui.LoginView;


import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenterImpl implements ILoginPresenter {

    ILoginView iLoginView;

    public LoginPresenterImpl(ILoginView iLoginView){
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String phone, String pwd) {
        Log.d("phone", phone);
        Log.d("pwd", pwd);

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/login";
        Map<String, String> params = new HashMap<>();
        params.put("phone", "18888888811");
        params.put("pwd", "123456");
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
//                            Log.d("res=", res);
                            if(res.equals("true")){
                                JSONObject userInfoJson = response.getJSONObject("description");
                                User user = new User(userInfoJson.getInt("id"));    // 答主
                                user.setMd5(userInfoJson.getString("md5"));
                                user.setPhone(userInfoJson.getString("phone"));
                                user.setName(userInfoJson.getString("name"));
                                user.setTitle(userInfoJson.getString("title"));
                                user.setIntro(userInfoJson.getString("intro"));
                                user.setBalance(Float.valueOf(userInfoJson.get("balance").toString()));
                                user.setResponder(userInfoJson.getBoolean("isResponder"));

                                iLoginView.loginSuccess(user);
                            }else{
                                String description = response.getString("description");
                                iLoginView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        //Log.d("Get失败：", Integer.toString(statusCode));
                        iLoginView.showMessage(error_msg);
                    }
                });
    }
}
