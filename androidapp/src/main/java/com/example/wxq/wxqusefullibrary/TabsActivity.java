package com.example.wxq.wxqusefullibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import com.example.wxq.wxqusefullibrary.fragment.Test2Fragment;
import com.example.wxq.wxqusefullibrary.fragment.Test3Fragment;
import com.example.wxq.wxqusefullibrary.fragment.Test4Fragment;
import com.example.wxq.wxqusefullibrary.fragment.Test5Fragment;
import com.example.wxq.wxqusefullibrary.fragment.Test6Fragment;
import com.example.wxq.wxqusefullibrary.widget.NoScrollViewPager;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import butterknife.ButterKnife;

public class TabsActivity extends BaseActivity {

    private static final String TAG = "OrientationActivity";
    private int param = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);

        RxBus.get().register(this);//创建rxbus添加监听器 当前activity被监听到
        tabs= (TabLayout) findViewById(R.id.tablayout);

        viewPager= (NoScrollViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
          @Override
          public Fragment getItem(int position) {
              //吧fragment 放到集合里面
              switch (position) {
                  case 0:
                      return new Test1Fragment();
                  case 1:
                      return new Test2Fragment();
                  case 2:
                      return new Test3Fragment();
                  case 3:
                      return new Test4Fragment();
                  case 4:
                      return new Test5Fragment();
                  case 5:
                      return new Test6Fragment();
                  default:
                      return new Test1Fragment();
              }

          }

          @Override
          public CharSequence getPageTitle(int position) {
              switch (position) {

                  case 0:
                      return "第"+(position+1)+"个";
                  case 1:
                      return "第"+(position+1)+"个";
                  case 2:
                      return "第"+(position+1)+"个";
                  case 3:
                      return "第"+(position+1)+"个";
                  case 4:
                      return "第"+(position+1)+"个";
                  case 5:
                      return "第"+(position+1)+"个";
                  default:
                      return "第"+(position+1)+"个";
              }
          }

          @Override
          public int getCount() {
              return 6;
          }
      });
     //   viewPager.setOffscreenPageLimit();//确保 viewpage oncreate 子类方法没有重新没加载 同时会调用oncreate方法现在需求是滑到那一个加载哪一个
        tabs.setupWithViewPager(viewPager);




    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart called.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory called.");
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }














    TabLayout tabs;

    NoScrollViewPager viewPager;


}
