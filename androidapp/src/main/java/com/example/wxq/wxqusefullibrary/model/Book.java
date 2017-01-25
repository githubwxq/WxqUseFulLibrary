package com.example.wxq.wxqusefullibrary.model;

import com.example.wxq.wxqusefullibrary.R;

import viewgroupadapter.IMulTypeHelper;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Book implements IMulTypeHelper {
  public  String name;
    public  String  price;
public int id;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;//自己可以而外设置 接口不一定会返回 标记
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    int tag=0;


    public Book(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Book() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int getItemLayoutId() {
        switch (type){
            case "flag1":

                return R.layout.book_list_item;

            case "flag2":

                return R.layout.book_list_item2;
            case "flag3":

                return R.layout.book_list_item3;

        }

        return R.layout.book_list_item; //默认布局
    }
}
