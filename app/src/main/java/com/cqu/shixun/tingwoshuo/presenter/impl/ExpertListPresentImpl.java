package com.cqu.shixun.tingwoshuo.presenter.impl;

import com.cqu.shixun.tingwoshuo.model.Category;
import com.cqu.shixun.tingwoshuo.model.User;
import com.cqu.shixun.tingwoshuo.presenter.iPresenter.IExpertListPrsenter;
import com.cqu.shixun.tingwoshuo.ui.iView.IExpertListView;

import java.util.ArrayList;
import java.util.List;

public class ExpertListPresentImpl implements IExpertListPrsenter {

    IExpertListView iExpertListView;

    public ExpertListPresentImpl(IExpertListView iExpertListView){
        this.iExpertListView = iExpertListView;
    }

    @Override
    public void getCategoryList() {
        List<Category> categories = new ArrayList<Category>();

        // 后端完成此处的实现

        for(int i = 0; i < 10; i++){ // 测试用的数据
            Category category = new Category(i, "板块" + Integer.toString(i));
            categories.add(category);
        }

        iExpertListView.showCategoryList(categories);
    }

    @Override
    public void getExpertList(Category category) {
        List<User> users = new ArrayList<User>();

        // 后端完成此处的实现

        for(int i = 0; i < 10; i++){ // 测试用的数据
            User user = new User(i);
            user.setName("专家" + Integer.toString(i));
            user.setTitle(category.getName() + "板块的专家");
            user.setAnsNum(100+i);
            user.setAskPrice(99.0f);
            // 头像待补充
            users.add(user);
        }

        iExpertListView.showExpertList(category, users);
    }
}
