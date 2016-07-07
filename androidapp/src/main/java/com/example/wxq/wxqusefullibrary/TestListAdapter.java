package com.example.wxq.wxqusefullibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TestListAdapter extends CommonAdapter<Book> {

    public TestListAdapter(Context mcontext, List<Book> mdatas, int mlayoutId) {
        super(mcontext, mdatas, mlayoutId);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return super.getView(position, convertView, parent);
    }

    @Override
    public void convert(ViewHolder holder, Book book) {

    }
}
