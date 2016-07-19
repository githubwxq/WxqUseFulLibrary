package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.widget.TabHeadItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试自定义 tablayout
 * Created by Administrator on 2016/7/12.
 */
public class Test3Fragment extends Fragment {


//    @BindView(R.id.tv_f3)
//    TextView tvF3;
    @BindView(R.id.tl_fg3)
    TabLayout tlFg3;

    //ctrl +alt +k 生命周期排序
    @Override
    public void onAttach(Context context) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreateView...");
        View view = inflater.inflate(R.layout.test3fragment, null);


        ButterKnife.bind(this, view);

         initTabs();





       // tvF3.setText("fragment3");
        return view;
    }

    private void initTabs() {

     
        TabHeadItem headItem1 = new TabHeadItem(getActivity());
        headItem1.setTabText("fist1");
        tlFg3.addTab(tlFg3.newTab().setCustomView(headItem1));



        TabHeadItem headItem2 = new TabHeadItem(getActivity());
        headItem1.setTabText("fist2");
        tlFg3.addTab(tlFg3.newTab().setCustomView(headItem2));


        TabHeadItem headItem3 = new TabHeadItem(getActivity());
        headItem1.setTabText("fist3");
        tlFg3.addTab(tlFg3.newTab().setCustomView(headItem3));

//        TabLayout.Tab tab =headItem1;
//        tlFg3.addView(headItem1);
//        TabHeadItem headItem2 = new TabHeadItem(getActivity());
//        headItem2.setTabText("fist2");
//        tlFg3.addView(headItem2);
//        TabHeadItem headItem3 = new TabHeadItem(getActivity());
//        headItem3.setTabText("fist3");
//        tlFg3.addView(headItem3);
        //回调接口
        tlFg3.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               String position= tab.getPosition()+"weizi";
              //  Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
                TabHeadItem customView = (TabHeadItem) tab.getCustomView();
                customView.setTabText("你点击了我");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabHeadItem customView = (TabHeadItem) tab.getCustomView();
                customView.setTabText("你没有点击了我");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(this.getClass().getName(), "ArrayListFragment **** onActivityCreated...");

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onResume...");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onPause...");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStop...");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroyView...");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDetach...");
        // TODO Auto-generated method stub
        super.onDetach();
    }


}

