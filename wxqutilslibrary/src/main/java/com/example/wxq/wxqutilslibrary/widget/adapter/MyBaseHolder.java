package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/7/7.
 */
public abstract class MyBaseHolder<T> {
    private View mRootView;
    private T data;
//当new对象的时候就会加载布局初始化控件，以及设置tag
 public Context context;

    public MyBaseHolder(Context context) {
        this.context=context;
     mRootView=initView();//大家不同的实现就用抽象方法实现不同内容
        mRootView.setTag(this);//把当前类给他 呆货取出来

    }

    // 1. 加载布局文件
    // 2. 初始化控件 findViewById
    protected abstract View initView();

    // 返回item的布局对象
    public View getRootView() {
        return mRootView;
    }
    // 设置当前item的数据
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }
    // 获取当前item的数据
    public T getData() {
        return data;
    }

    // 4. 根据数据来刷新界面
    public abstract void refreshView(T data);
}
