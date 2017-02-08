package com.example.wxq.wxqusefullibrary.bmob.activity.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.RecommendItem;

import java.util.List;

/**
 * 避免嵌套充分使用gridelayoutmanager
 */
public class OneRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<RecommendItem> results;

    //type
    public static final int TYPE_SLIDER = 0xff01;
    public static final int TYPE_TYPE2_HEAD = 0xff02;
    public static final int TYPE_TYPE2 = 0xff03;
    public static final int TYPE_TYPE3_HEAD = 0xff04;
    public static final int TYPE_TYPE3 = 0xff05;
    public static final int TYPE_TYPE4 = 0xff06;

    public OneRecycleAdapter(Context context, int srcId, List<RecommendItem> results) {
        this.context = context;
        this.results = results;
    }
    public OneRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 //根据viewtype决定样式
        switch (viewType){
            case TYPE_SLIDER:
                return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_banner, parent, false));
            case TYPE_TYPE2_HEAD:
            case TYPE_TYPE3_HEAD:
                return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false));
            case TYPE_TYPE2:
            case TYPE_TYPE3:
            case TYPE_TYPE4:
                return new ViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item3, parent, false));
            default:
                Log.d("error", "viewholder is null");
                return null;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {




    }

    @Override
    public int getItemCount() {

        return 40;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_SLIDER;
        }else if (position == 1){
            return TYPE_TYPE2_HEAD;
        }else if (2<=position && position <= 7){
            return TYPE_TYPE2;
        }else if (position == 8){
            return TYPE_TYPE3_HEAD;
        }else if (9<=position && position <= 14){
            return TYPE_TYPE3;
        }else if (15<=position && position <= 18){
            return TYPE_TYPE4;
        }else {
            return TYPE_TYPE2;
        }

    }

    //根据数据返回类型
   public class BannerViewHolder extends RecyclerView.ViewHolder{

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder{

        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type){
                        case TYPE_SLIDER:
                        case TYPE_TYPE2_HEAD:
                        case TYPE_TYPE3_HEAD:
                        case TYPE_TYPE4:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE2:
                            return 3;
                        case TYPE_TYPE3:
                            return 2;
                        default:
                            return 3;
                    }
                }
            });
        }
    }
}
