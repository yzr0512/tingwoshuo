package com.cqu.shixun.tingwoshuo.presenter;
import android.util.Log;

import com.cqu.shixun.tingwoshuo.iView.IFindPswView;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class FindPswPresenterImpl implements IFindPswPresenter {

    IFindPswView iFindpswView;
    public FindPswPresenterImpl(IFindPswView iFindpswView){
        this.iFindpswView = iFindpswView;

    }

    @Override
    public void findpsw(String phone, String pwd) {

        Log.d("phone", phone);
        Log.d("pwd", pwd);


        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/fpsw";
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("pwd", pwd);
        myOkHttp.post().url(url)
                .params(params)
                .enqueue(new JsonResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {


                    }


                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

}
