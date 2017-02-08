package com.example.wxq.wxqusefullibrary.bmob.activity.homepage.childfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.homepage.adapter.NestRecycleViewAdapter;
import com.example.wxq.wxqusefullibrary.bmob.activity.homepage.adapter.OneRecycleAdapter;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;

public class RecommendFragment extends SuperFragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }


    @Override
    protected int getResourceId() {
        return R.layout.fragment_recommend;
    }

    RecyclerView recyclerView;
    SwipeRefreshLayout lay_refresh;
    NestRecycleViewAdapter nestFullListViewAdapter;
    OneRecycleAdapter oneRecycleAdapter;
    @Override
    protected void initDataAndView(View view) {
        showToast("懒加载");
        oneRecycleAdapter=new OneRecycleAdapter(mcontent);
        nestFullListViewAdapter=new NestRecycleViewAdapter(mcontent);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false)); //主要布局
        recyclerView.setAdapter(nestFullListViewAdapter);

        //        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(oneRecycleAdapter);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        lay_refresh= (SwipeRefreshLayout) view.findViewById(R.id.lay_refresh);
        lay_refresh.setColorSchemeResources(R.color.pink, R.color.yellow);
        lay_refresh.setOnRefreshListener(this);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void commonLoad(View view) {
       showToast("普通加载");
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("刷新中");
                lay_refresh.setRefreshing(false);
            }
        }, 2000);
    }
}
