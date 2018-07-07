package com.cqu.shixun.tingwoshuo.presenter.impl;

import com.cqu.shixun.tingwoshuo.model.Category;
import com.cqu.shixun.tingwoshuo.model.PersonItem;
// import com.cqu.shixun.tingwoshuo.model.User;
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
        List<PersonItem> personItems = new ArrayList<PersonItem>();

        // 后端完成此处的实现

        for(int i = 0; i < 10; i++){ // 测试用的数据
            PersonItem personItem = new PersonItem(i);
            personItem.setName("专家" + Integer.toString(i));
            personItem.setTitle(category.getName() + "板块的专家");
            personItem.setAnsNum(100+i);
            personItem.setAskPrice(99.0f);
            // 头像待补充
            personItems.add(personItem);
        }

        iExpertListView.showExpertList(category, personItems);
    }
}
