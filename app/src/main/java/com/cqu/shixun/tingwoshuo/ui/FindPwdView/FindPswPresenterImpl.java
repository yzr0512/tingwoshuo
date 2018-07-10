package com.cqu.shixun.tingwoshuo.ui.FindPwdView;
import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.User;
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

//        Log.d("phone", phone);
//        Log.d("pwd", pwd);

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/resetPwd";
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("pwd", pwd);
        myOkHttp.post().url(url)
                .params(params)
                .enqueue(new JsonResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){

                                iFindpswView.findpswSuccess();
                            }else{
                                String description = response.getString("description");
                                iFindpswView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iFindpswView.showMessage(Integer.toString(statusCode) + ":" + error_msg);
                    }
                });

    }

}
