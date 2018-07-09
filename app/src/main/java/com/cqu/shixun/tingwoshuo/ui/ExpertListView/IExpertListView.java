package com.cqu.shixun.tingwoshuo.ui.ExpertListView;

// 专家列表界面的接口

import com.cqu.shixun.tingwoshuo.model.Category;
//import com.cqu.shixun.tingwoshuo.model.PersonItem;
import com.cqu.shixun.tingwoshuo.model.User;

import java.util.List;

public interface IExpertListView {
    // 显示所有的板块列表（弃用）
    public void showCategoryList(List<Category> categories);

    // 显示某个板块的专家列表
    public void showExpertList(String categoryName, List<User> users);

    // 显示信息
    public void showMessage(String msg);
}
