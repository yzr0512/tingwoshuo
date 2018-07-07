package com.cqu.shixun.tingwoshuo.presenter.impl;

import android.util.Log;

import com.cqu.shixun.tingwoshuo.model.Category;
//import com.cqu.shixun.tingwoshuo.model.PersonItem;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IExpertListPrsenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IExpertListView;

import java.util.ArrayList;
import java.util.List;

import com.cqu.shixun.tingwoshuo.model.myokhttp.MyOkHttp;
import com.cqu.shixun.tingwoshuo.model.myokhttp.response.JsonResponseHandler;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpertListPresentImpl implements IExpertListPrsenter {

    IExpertListView iExpertListView;

    public ExpertListPresentImpl(IExpertListView iExpertListView){
        this.iExpertListView = iExpertListView;
    }

    @Override
    public void getCategoryList() {

        // 后端完成此处的实现
        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/category")
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            List<Category> categories = new ArrayList<Category>();
                            int n = response.getInt("total");
                            Log.d("Total:", Integer.toString(n));
                            JSONObject jsonList = response.getJSONObject("list");
                            for(int i = 1; i <= n; i++) {
                                JSONObject jsonObject = jsonList.getJSONObject(Integer.toString(i));
                                Category category = new Category(jsonObject.getInt("id"), jsonObject.getString("name"));
                                Log.d(Integer.toString(i), category.getName());
                                categories.add(category);
                            }
                            iExpertListView.showCategoryList(categories);
                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iExpertListView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iExpertListView.showMessage(error_msg);
                    }
                });

//        for(int i = 0; i < 10; i++){ // 测试用的数据
//            Category category = new Category(i, "板块" + Integer.toString(i));
//            categories.add(category);
//        }

    }

    @Override
    public void getExpertList(String categoryName) {

//        Log.d("Category", Integer.toString(categoryID));
        // 后端完成此处的实现

//        for(int i = 0; i < 10; i++){ // 测试用的数据
//            User user = new User(i);
//            user.setName("专家" + Integer.toString(i));
////            user.setTitle(category.getName() + "板块的专家");
//            user.setAnsNum(100+i);
//            user.setAskPrice(99.0f);
//            // 头像待补充
//            users.add(user);
//        }

        MyOkHttp myOkHttp = new MyOkHttp();
        myOkHttp.get().url("http://119.29.105.37:8000/expertList").addParam("categoryName", categoryName)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        //Log.d("Get成功：", "doPost onSuccess:" + response);
                        try{
                            List<User> users = new ArrayList<User>();
                            int n = response.getInt("total");
                            Log.d("Total:", Integer.toString(n));
                            JSONObject jsonList = response.getJSONObject("list");
                            for(int i = 1; i <= n; i++) {
                                JSONObject jsonObject = jsonList.getJSONObject(Integer.toString(i));
                                User user = new User(jsonObject.getInt("id"));
                                user.setTitle(jsonObject.getString("title"));
                                user.setCategory(jsonObject.getString("category"));
                                user.setName(jsonObject.getString("name"));
                                user.setAskPrice(Float.valueOf(jsonObject.get("askPrice").toString())); // 据说有精度问题？
                                user.setAnsNum(jsonObject.getInt("ansNum"));
                                Log.d("getExpertList: ", user.getName() + " title:" + user.getTitle() + " category:" + user.getCategory() + " askPrice:" + user.getAskPrice() + " ansNum:" + user.getAnsNum());
                                users.add(user);
                            }
                            if(users.size() > 0)
                                iExpertListView.showExpertList(users.get(0).getCategory(), users);
                            else
                                iExpertListView.showMessage("该板块暂无专家信息。");
                        }catch (JSONException e){
//                            Log.d("JSONException：", e.getMessage());
                            iExpertListView.showMessage("JSONException：" + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
//                        Log.d("Get失败：", Integer.toString(statusCode));
                        iExpertListView.showMessage(error_msg);
                    }
                });
//        iExpertListView.showExpertList(category, users);
    }
}
