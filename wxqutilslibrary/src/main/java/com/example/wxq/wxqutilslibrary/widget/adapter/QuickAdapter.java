package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wxq
 * on 2016/7/15.
 */
public abstract class QuickAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;


    public QuickAdapter(Context context, List<T> mDatas, int itemLayoutId)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        System.out.println("getview======="+position);

        final QuickViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position),position);// 将position 抽出来 改变背景颜色什么的有用哦！！！
        return viewHolder.getConvertView();

    }
    public abstract void convert(QuickViewHolder helper, T item,int position);
    private QuickViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent)
    {
        return QuickViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    public void loadMore(List<T> moreDatas ){
        mDatas.addAll(moreDatas);

        this.notifyDataSetChanged();
    }


}
