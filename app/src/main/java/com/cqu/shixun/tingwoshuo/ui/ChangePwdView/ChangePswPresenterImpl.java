package com.cqu.shixun.tingwoshuo.ui.ChangePwdView;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePswPresenterImpl extends JsonResponseHandler implements IChangePswPresenter {

    IChangePswView iChangePswView;
    public ChangePswPresenterImpl(IChangePswView iChangePswView){
        this.iChangePswView = iChangePswView;
    }

    @Override
    public void changePsw(User user) {

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/changePwd";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        params.put("pwd", user.getPwd());
//        params.put("askPrice", "111.1");
        myOkHttp.post().url(url)
                .params(params).enqueue(this);

    }

    @Override
    public void onSuccess(int statusCode, JSONObject response) {
        super.onSuccess(statusCode, response);
        try{
            String res = response.getString("result");
            if(res.equals("true")){
                iChangePswView.changeSuccess();
            }else{
                String description = response.getString("description");
                iChangePswView.showMessage(description);
            }

        }catch (JSONException e){
            Log.d("JSONException：", e.getMessage());
        }
    }

    @Override
    public void onFailure(int statusCode, String error_msg) {
        iChangePswView.showMessage(error_msg);
    }
}
