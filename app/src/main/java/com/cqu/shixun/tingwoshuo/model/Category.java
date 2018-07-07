package com.cqu.shixun.tingwoshuo.model;

public class Category {
    int id;     // 分类id

    String name;    // 分类名称

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
