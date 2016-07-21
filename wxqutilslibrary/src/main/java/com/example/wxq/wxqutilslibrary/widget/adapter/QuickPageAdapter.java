package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public abstract class QuickPageAdapter<T> extends PagerAdapter{

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public QuickPageAdapter(int mItemLayoutId,Context context, List<T> mDatas) {
        this.mItemLayoutId = mItemLayoutId;
        mContext=context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       View view= mInflater.inflate(mItemLayoutId,container,false);
        //给子类实现
        setData(view,position,mDatas.get(position));
        container.addView(view);
        return view;


    }

    public abstract void setData(View view, int position, T t);
}
