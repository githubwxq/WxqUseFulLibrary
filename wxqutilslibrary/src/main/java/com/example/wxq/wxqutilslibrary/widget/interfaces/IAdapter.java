package com.example.wxq.wxqutilslibrary.widget.interfaces;


import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;

/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.classic.adapter
 * 类 描 述: 扩展的Adapter接口规范
 * 创 建 人: 续写经典
 * 创建时间: 2016/1/21 17:54.
 */
public interface IAdapter<T> {

    void onUpdate(BaseAdapterHelper helper, T item, int position);

    int getLayoutResId(T item, int position);
}
