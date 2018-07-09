package com.cqu.shixun.tingwoshuo.ui.BecomeExpertView;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BecomeExpertPresentImpl extends JsonResponseHandler implements IBecomeExpertPresenter{

    IBecomeExpertView iBecomeExpertView;

    public BecomeExpertPresentImpl(IBecomeExpertView iBecomeExpertView){
        this.iBecomeExpertView = iBecomeExpertView;
    }
    @Override
    public void postUser(User user) {

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/beExpert";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        params.put("title", user.getTitle());
        params.put("intro", user.getIntro());
        params.put("category", user.getCategory());
        params.put("askPrice", Float.toString(user.getAskPrice()));
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

                iBecomeExpertView.success();
            }else{
                String description = response.getString("description");
                iBecomeExpertView.showMessage(description);
            }

        }catch (JSONException e){
            Log.d("JSONException：", e.getMessage());
        }
    }

    @Override
    public void onFailure(int statusCode, String error_msg) {
        //Log.d("Get失败：", Integer.toString(statusCode));
        iBecomeExpertView.showMessage(error_msg);
    }
}
