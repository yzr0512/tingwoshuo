package com.cqu.shixun.tingwoshuo.ui.RechargeView;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RechargePresenterImpl implements IRechargePresenter {

    IRechargeView iRechargeView;

    public RechargePresenterImpl(IRechargeView iRechargeView) {
        this.iRechargeView = iRechargeView;
    }

    @Override
    public void getBalance(final User user) {

        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/login";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        myOkHttp.get().url(url).params(params)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){
                                user.setBalance(Float.valueOf(response.get("balance").toString()));

                                iRechargeView.rechargeInformation(user);
                            }else{
                                String description = response.getString("description");
                                iRechargeView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iRechargeView.showMessage(error_msg);
                    }
                });
    }

    @Override
    public void recharge(final User user, float money) {
        MyOkHttp myOkHttp = new MyOkHttp();
        String url = "http://119.29.105.37:8000/recharge";
        Map<String, String> params = new HashMap<>();
        params.put("phone", user.getPhone());
        params.put("md5", user.getMd5());
        params.put("rechargePrice", Float.toString(money));
        myOkHttp.post().url(url).params(params)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try{
                            String res = response.getString("result");
                            if(res.equals("true")){
                                iRechargeView.rechargeSuccess();
                            }else{
                                String description = response.getString("description");
                                iRechargeView.showMessage(description);
                            }

                        }catch (JSONException e){
                            Log.d("JSONException：", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        iRechargeView.showMessage(error_msg);
                    }
                });
    }


}
