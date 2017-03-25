package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.springview.SpringView;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.AliFooter;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.AliHeader;

import java.util.ArrayList;
import java.util.List;

public class SprignView2Activity extends BaseActivity {
    private List<String> mDatas = new ArrayList<String>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SpringView springView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprign_view2);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewAdapter = new RecyclerViewAdapter(this, mDatas);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        springView = (SpringView) findViewById(R.id.springview);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new MyOnFreshListener());
        springView.setHeader(new AliHeader(this,R.drawable.ali,true));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new AliFooter(this,false));





    }
    private void initData(){
        for (int i = 0; i < 9; i++) {
            mDatas.add(i==0?"We are in RecyclerView":(i==1?"SpringView支持RecyclerView\n\n这是一个仿阿里旅行的header，logo可以图片可自行替换":""));
        }
    }


    @Override
    public void widgetClick(View v) {

    }




    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SampleViewHolder> {
        private List<String> results;
        public RecyclerViewAdapter(Context context, List<String> results) {
            this.results = results;
        }
        @Override
        public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new SampleViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final SampleViewHolder holder, final int position) {
            holder.text_item.setText(results.get(position));
            if (position%2==1) {
                holder.text_item.setBackgroundColor(Color.parseColor("#e3f1fc"));
                holder.text_item.setTextColor(Color.parseColor("#9dd2fc"));
            }
            else {
                holder.text_item.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.text_item.setTextColor(Color.parseColor("#cccccc"));
            }
        }
        @Override
        public int getItemCount() {
            return results.size();
        }
        public class SampleViewHolder extends RecyclerView.ViewHolder {
            public TextView text_item;
            public SampleViewHolder(View view) {
                super(view);
                text_item = (TextView) view.findViewById(R.id.text);
            }
        }
    }


    private class MyOnFreshListener implements SpringView.OnFreshListener {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    springView.onFinishFreshAndLoad();
                }
            }, 1000);
        }

        @Override
        public void onLoadmore() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    springView.onFinishFreshAndLoad();
                }
            }, 1000);
        }
    }
}
