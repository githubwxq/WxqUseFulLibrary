package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
public Context mcontext;
    public List<T> mdatas;
    public LayoutInflater mInflater;
    public int mlayoutId;

    public CommonAdapter(Context mcontext, List<T> mdatas, int mlayoutId) {
        this.mcontext = mcontext;
        this.mdatas = mdatas;
        this.mlayoutId = mlayoutId;
    }



    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public T getItem(int position) {

        return mdatas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mcontext, convertView, parent, mlayoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();

    }
    public abstract void convert(ViewHolder holder, T t);//给上层必须实现调用的
}
