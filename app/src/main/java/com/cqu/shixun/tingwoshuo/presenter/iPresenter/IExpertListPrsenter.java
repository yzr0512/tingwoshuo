package com.cqu.shixun.tingwoshuo.presenter.iPresenter;

import com.cqu.shixun.tingwoshuo.model.Category;

public interface IExpertListPrsenter {

    // 获取所有板块列表
    public void getCategoryList();

    // 获取某个板块的专家列表
    public void getExpertList(String categoryName);

}
