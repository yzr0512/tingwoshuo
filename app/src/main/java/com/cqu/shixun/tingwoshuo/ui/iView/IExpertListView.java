package com.cqu.shixun.tingwoshuo.ui.iView;

// 专家列表界面的接口

import com.cqu.shixun.tingwoshuo.model.Category;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public interface IExpertListView {
    // 显示所有的板块列表
    public void showCategoryList(List<Category> categories);

    // 显示某个板块的专家列表
    public void showExpertList(Category category, List<User> users);

}