package com.example.wxq.wxqusefullibrary;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Book {
    String name;
    String  price;

    public Book(String name, String price) {
        this.name = name;
        this.price = price;
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
}
