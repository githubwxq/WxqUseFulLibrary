package com.example.wxq.wxqusefullibrary.bmob.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.activity.TabEntity;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment1;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment2;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment3;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment4;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.CommonTabLayout;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.CustomTabEntity;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.MsgView;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.OnTabSelectListener;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.UnreadMsgUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class BmobIndexActivity extends BaseActivity {

    private CommonTabLayout mTlBottom;
    private FrameLayout mFlChange;
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_index);
        initFixedData();
        initView();
        setTitleHeadVisiable(false);




    }

    private void initFixedData() {
        mFragments.add(new lazyFragment1());
        mFragments.add(new lazyFragment2());
        mFragments.add(new lazyFragment3());
        mFragments.add(new lazyFragment4());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

    }

    @Override
    public void widgetClick(View v) {

    }


    private void initView() {
        mTlBottom = (CommonTabLayout) findViewById(R.id.tl_bottom);
        mFlChange = (FrameLayout) findViewById(R.id.fl_change);

        mTlBottom.setTabData(mTabEntities, this, R.id.fl_change, mFragments);
        mTlBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
              showToast("选中" + position);
                if(position==2){
                    mTlBottom.hideMsg(0);


                    EventBus.getDefault().post("你好fragment1我来自activity");

                }
            //    EventBus.getDefault().

            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mTlBottom.setCurrentTab(0);
        mTlBottom.showDot(0);
        MsgView rtv_2_2 = mTlBottom.getMsgView(0);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message) {
        showToast("activity 收到消息"+message);
    }
}
