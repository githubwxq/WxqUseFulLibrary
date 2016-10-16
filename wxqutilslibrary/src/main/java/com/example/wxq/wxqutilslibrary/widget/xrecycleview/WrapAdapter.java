package com.example.wxq.wxqutilslibrary.widget.xrecycleview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/16.
 * 装饰者模式
 */
public class WrapAdapter extends RecyclerView.Adapter{
    private static final int TYPE_REFRESH_HEADER =  -5;
    private static final int TYPE_HEADER =  -4;
    private static final int TYPE_FOOTER =  -3;
    private static final int TYPE_NORMAL =  0;



    private RecyclerView.Adapter adapter;
    private ArrayList<View> mHeaderViews;
    private ArrayList<BaseMoreFooter> mFootViews;

    private int headerPosition = 1;

    public WrapAdapter(ArrayList<View> headerViews, ArrayList<BaseMoreFooter> footViews, RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.mHeaderViews = headerViews;
        this.mFootViews = footViews;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
       if(manager instanceof GridLayoutManager){
           final GridLayoutManager gridManager = ((GridLayoutManager) manager);
           gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
               @Override
               public int getSpanSize(int position) {
                   return (isHeader(position)||  isFooter(position)) ? gridManager.getSpanCount() : 1;
               }
           });

       }



    }

    public boolean isHeader(int position) {
        return position >= 0 && position < mHeaderViews.size();
    }

    public boolean isFooter(int position) {
        return position < getItemCount() && position >= getItemCount() - mFootViews.size();
    }

    public boolean isRefreshHeader(int position) {
        return position == 0 ;
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_REFRESH_HEADER){
            return new SimpleViewHolder(mHeaderViews.get(0));
        }else if (viewType == TYPE_HEADER) {
            return new SimpleViewHolder(mHeaderViews.get(headerPosition++ ));
        } else if (viewType == TYPE_FOOTER) {
            return new SimpleViewHolder((View) mFootViews.get(0));
        }
        return adapter.onCreateViewHolder(parent, viewType);

    }



    //重新绑定确保之前的adapter被装饰者position不受head和foot的影响
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if(isHeader(position)){
    return;
    }
     int adjPosition=position-getHeadersCount();
        int adapterCount;
        if(adapter!=null){
            adapterCount=adapter.getItemCount();

            if(adjPosition<adapterCount){
                adapter.onBindViewHolder(holder, adjPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (adapter != null) {
            return getHeadersCount() + getFootersCount() + adapter.getItemCount();
        } else {
            return getHeadersCount() + getFootersCount();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(isRefreshHeader(position)){
            return TYPE_REFRESH_HEADER;  //刷新的头部区别于其他头部
        }
        if (isHeader(position)) {
            return TYPE_HEADER;
        }
        if(isFooter(position)){
            return TYPE_FOOTER;
        }
        int adjPosition = position - getHeadersCount();
        int adapterCount;
        if (adapter != null) {
            adapterCount = adapter.getItemCount();
            if (adjPosition < adapterCount) {
                return adapter.getItemViewType(adjPosition);
            }
        }
        return TYPE_NORMAL;
    }

    @Override
    public long getItemId(int position) {
        if (adapter != null && position >= getHeadersCount()) {
            int adjPosition = position - getHeadersCount();
            int adapterCount = adapter.getItemCount();
            if (adjPosition < adapterCount) {
                return adapter.getItemId(adjPosition);
            }
        }
        return -1;
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(observer); //重新装饰的 adapter 用被装饰者的方法
        }
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
    }

    private class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
