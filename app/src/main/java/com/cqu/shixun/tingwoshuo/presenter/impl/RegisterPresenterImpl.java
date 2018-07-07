package com.cqu.shixun.tingwoshuo.presenter.impl;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IRegisterPresenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IRegisterView;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterPresenterImpl implements IRegisterPresenter {
    IRegisterView iRegisterView;

    public RegisterPresenterImpl(IRegisterView iRegisterView){
        this.iRegisterView = iRegisterView;

    }

    @Override
    public void register(String phone, String pwd, String name) {
//        Log.d("phone", phone);
//        Log.d("pwd", pwd);
//        Log.d("name", name);

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/reg";
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("pwd", pwd);
        params.put("name", name);
//        params.put("phone", "12345678901");
//        params.put("pwd", "123456");
//        params.put("name", "测试01");

        myOkHttp.post().url(url)
                .params(params)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try{
                            String res = response.getString("result");
                            Log.d("res=", res);
                            if(res.equals("true")){
                                iRegisterView.registerSuccess();
                            }else{
                                String description = response.getString("description");
                                iRegisterView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iRegisterView.showMessage(error_msg);
                    }
                });
    }
}
