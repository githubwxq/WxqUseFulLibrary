package com.example.wxq.wxqusefullibrary.bmob.activity.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.RecommendItem;
import com.example.wxq.wxqusefullibrary.utils.GlideImageLoader;
import com.example.wxq.wxqutilslibrary.myutils.glide.GlideUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import banner.Banner;
import banner.BannerConfig;
import banner.listener.OnBannerClickListener;
import specialtools.MyToast;

/**嵌套recycle 最简单的方法使用实现不同布局  listview也可以根据类型传入添加不同的view 实现复杂布局 数据与视图绑定
 * Created by Administrator on 2017/2/2.
 */
public class NestRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<RecommendItem> results;
    //type
    public static final int TYPE_1 = 1;  //推荐一
    public static final int TYPE_2 = 2;  // 推荐二
    public static final int TYPE_3 = 3;  // 推荐三
    public static final int TYPE_4 = 4;  // 推荐四
    public static final int TYPE_MAIN = 5; // 普通
    //get & set
    public List<RecommendItem> getResults() {
        return results;
    }

    public NestRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public NestRecycleViewAdapter(Context context, int srcId, List<RecommendItem> results) {
        this.context = context;
        this.results = results;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return  new MyBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_banner, parent, false));
            case TYPE_2:
                return  new MyRecommentViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type2, parent, false));
            case TYPE_3:
                return  new MyRecommentViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_type3, parent, false));

                 default:
                Log.d("error", "viewholder is null");
                return new MyBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_banner, parent, false));
        }
    }
    // 轮播图
    public class MyBannerViewHolder extends RecyclerView.ViewHolder{
       Banner banner;
        public MyBannerViewHolder(View itemView) {
            super(itemView);

            banner= (Banner) itemView.findViewById(R.id.banner3);

        }
    }

    // recycleview 推荐2
    public class MyRecommentViewHolder2 extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        public MyRecommentViewHolder2(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.item_recyc_type2);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  MyBannerViewHolder){
            bindBanner((MyBannerViewHolder)holder,position);
        }else if (holder instanceof MyRecommentViewHolder2 ){
           bindViewHolder2((MyRecommentViewHolder2) holder, position);
        }else if (holder instanceof MyRecommentViewHolder3 ){
            bindViewHolder3((MyRecommentViewHolder3) holder, position);
        }



    }

    private void bindViewHolder3(MyRecommentViewHolder3 holder, int position) {
        // 处理数据
        //添加头
        String headurl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486127012133&di=460611632c7e2064c17e17efbdc75022&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fw7%2F78%2Fd%2F82.jpg";
        GlideUtil.getInstance().loadImage(context,holder.item_recyc_type3_heard,headurl,true);
        //添加数据给recycleview
        // 数据创建
        ArrayList<RecommendItem>  recommendItems=new ArrayList<>();
        RecommendItem recommendItem = new RecommendItem();
        recommendItem.type=1;
        recommendItem.imageUrl="http://upload.northnews.cn/2013/1213/1386925630934.jpg";
        recommendItem.content="111111111";
        RecommendItem recommendItem2 = new RecommendItem();
        recommendItem2.type=1;
        recommendItem2.imageUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486127012136&di=8319b1639b6ab09cfa6a1e39700aee1b&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2Fimg013%2Fv3%2F2%2Fd%2F61.jpg";
        recommendItem2.content="2222222";
        recommendItems.add(recommendItem);
        recommendItems.add(recommendItem2);
        recommendItems.add(recommendItem);
        recommendItems.add(recommendItem2);
        // 创建
       holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false));
       // holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(new RecycleItemAdapterType3(context, recommendItems));



    }

    private void bindViewHolder2(MyRecommentViewHolder2 holder, int position) {
        //        holder.item_recyc_type2.setLayoutManager(new LinearLayoutManager(holder.item_recyc_type2.getContext(), LinearLayoutManager.VERTICAL, false));
  //      holder.item_recyc_type2.setLayoutManager(new FullyGridLayoutManager(holder.item_recyc_type2.getContext(), 2, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        // 数据创建
        ArrayList<RecommendItem>  recommendItems=new ArrayList<>();
        RecommendItem recommendItem = new RecommendItem();
        recommendItem.type=1;
        recommendItem.imageUrl="http://upload.northnews.cn/2013/1213/1386925630934.jpg";
        recommendItem.content="111111111";
        RecommendItem recommendItem2 = new RecommendItem();
        recommendItem2.type=1;
        recommendItem2.imageUrl="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2044577350,454612564&fm=23&gp=0.jpg";
        recommendItem2.content="2222222";

        recommendItems.add(recommendItem);
        recommendItems.add(recommendItem2);
        recommendItems.add(recommendItem);
        recommendItems.add(recommendItem2);
        // 创建
       //holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(new RecycleItemAdapterType2(context,recommendItems));
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
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }

    }

    private void bindBanner(MyBannerViewHolder holder, int position) {
        // 获取网络数据  在外面就应该获取而不是在这里获取
        String[] urls = context.getResources().getStringArray(R.array.url);
        String[] tips = context.getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        List<String> titles=new ArrayList(Arrays.asList(tips));
        List<?> images  = new ArrayList(list);
        holder.banner.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)  //有黑色条目
                .setImageLoader(new GlideImageLoader())
                .start();
        holder.banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                MyToast.showShort(context,"position"+position);
            }
        });



    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_1;
        }else if (position == 1){
            return TYPE_2;
        }else if (position == 2){
            return TYPE_3;
        }else if (position == 3){
            return TYPE_4;
        }else {
            return TYPE_MAIN;
        }
    }

    @Override
    public int getItemCount() {
        return 10; //由主页一共条数
    }

    private class MyRecommentViewHolder3 extends RecyclerView.ViewHolder {
       ImageView item_recyc_type3_heard;
       RecyclerView   recyclerView;

        public MyRecommentViewHolder3(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.recycler_view);
            item_recyc_type3_heard= (ImageView) itemView.findViewById(R.id.item_recyc_type3_heard);

        }
    }
}
