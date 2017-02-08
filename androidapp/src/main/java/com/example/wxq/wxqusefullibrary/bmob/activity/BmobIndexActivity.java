package com.example.wxq.wxqusefullibrary.bmob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.activity.MainActivity;
import com.example.wxq.wxqusefullibrary.activity.TabEntity;
import com.example.wxq.wxqusefullibrary.bmob.activity.homepage.HomeFragment;
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

import swipemenu.view.SwipeMenu;

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
    private String[] mTitles = {"bmob", "消息传递", "数据库", "其他"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private SwipeMenu main_swipemenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_index);
        setTheme(R.style.AppTheme_NoActionBar);
       setTitleHeadVisiable(false);
//        main_swipemenu= (SwipeMenu) findViewById(R.id.main_swipemenu);
//        main_swipemenu.setChangedBlur(this, R.mipmap.dayu, R.color.colorPrimary);
//        main_swipemenu.setStyleCode(12111);
        //第一：默认初始化
       //  Bmob.initialize(this, "e2403b02ad752d9577698fc49504b980");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
        initFixedData();
        initView();
       //   setTitleHeadVisiable(false);
        setTitleText("android知识技巧大整合");
      
    }
//
    private void initFixedData() {
        mFragments.add(HomeFragment.newInstance());
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
                if (position == 2) {
                    mTlBottom.hideMsg(0);
                    EventBus.getDefault().post("你好fragment1我来自activity");
                }
                //    EventBus.getDefault().
                EventBus.getDefault().post(new MainActivity.MainActivityMedium(200,"mainactivity收到来之bmob的消息"));
                Intent intent = getIntent();
////放数据
//                intent.putExtra("name","wxq");
////注意
//                setResult(100,intent);
//                finish();
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
        showToast("activity 收到消息" + message);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
