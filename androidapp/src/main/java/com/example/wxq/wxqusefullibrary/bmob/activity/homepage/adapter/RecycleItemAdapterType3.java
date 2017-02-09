package com.example.wxq.wxqusefullibrary.bmob.activity.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.RecommendItem;
import com.example.wxq.wxqutilslibrary.imageloadutils.glide.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class RecycleItemAdapterType3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RecommendItem> results;

    //get & set
    public List<RecommendItem> getResults() {
        return results;
    }

    public RecycleItemAdapterType3(Context context, List<RecommendItem> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            bind((ItemViewHolder) holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    /////////////////////////////

    private void bind(ItemViewHolder holder, int position){
      //  x.image().bind(holder.item_recyc_type2_item_img, results.get(position).getImg(),new ImageOptions.Builder().build(),new CustomBitmapLoadCallBack(holder.item_recyc_type2_item_img));

        GlideUtil.getInstance().loadImage(context,holder.item_recyc_type2_item_img,results.get(position).imageUrl,true);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_recyc_type2_item_img;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundResource(R.color.white);
            item_recyc_type2_item_img = (ImageView) itemView.findViewById(R.id.item_recyc_type2_item_img);
        }
    }
}
