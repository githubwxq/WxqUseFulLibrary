package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.fragment.SimpleCardFragment;
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

import java.util.ArrayList;
import java.util.Random;

public class CommonTabActivity extends BaseActivity {

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_1;
    private CommonTabLayout mTabLayout_3;

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
           R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout mTabLayout_2;

    Random mRandom = new Random();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tab);
//        mFragments.add(new lazyFragment1());
//        mFragments.add(new lazyFragment2());
//        mFragments.add(new lazyFragment3());
//        mFragments.add(new lazyFragment4());
        mFragments2.add(new lazyFragment4());
        mFragments2.add(new lazyFragment3());
        mFragments2.add(new lazyFragment2());
        mFragments2.add(new lazyFragment1());
        for (String title : mTitles) {
        //    mFragments.add(new lazyFragment1());//不是单列
        // mFragments2.add(new lazyFragment2());
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        dealwith1();
        dealwith2();
//        /** with Fragments */
//        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);
        dealwith3();




    }

    private void dealwith3() {

        /** with Fragments */
        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);
        mTabLayout_3.setTabData(mTabEntities, this, R.id.fl_change, mFragments2);
        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mTabLayout_1.setCurrentTab(position);
                mTabLayout_2.setCurrentTab(position);
//                mTabLayout_4.setCurrentTab(position);
//                mTabLayout_5.setCurrentTab(position);
//                mTabLayout_6.setCurrentTab(position);
//                mTabLayout_7.setCurrentTab(position);
//                mTabLayout_8.setCurrentTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mTabLayout_3.setCurrentTab(1);
        mTabLayout_3.showDot(1);


    }

    private void dealwith1() {
        mTabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);

        mTabLayout_1.setTabData(mTabEntities);

        mTabLayout_1.showDot(2);
        mTabLayout_1.showDot(1);
        mTabLayout_1.showDot(3);
        mTabLayout_1.hideMsg(2);
    }

    private void dealwith2() {
        /** with ViewPager */
        mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.tl_2);
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        //显示未读红点
        //   mTabLayout_1.showDot(2);
//        mTabLayout_3.showDot(1);
//        mTabLayout_4.showDot(1);

        //两位数
        mTabLayout_2.showMsg(0, 55);
        mTabLayout_2.hideMsg(3);
        mTabLayout_2.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout_2.showMsg(1, 100);
        mTabLayout_2.setMsgMargin(1, -5, 5);

        //设置未读消息红点

          mTabLayout_2.showDot(2);
        MsgView rtv_2_2 = mTabLayout_2.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout_2.showMsg(3, 5);
        mTabLayout_2.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout_2.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#000079"));
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }




    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
