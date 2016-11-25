package com.example.wxq.wxqusefullibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

public class SpringViewActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_view);
        findViewById(R.id.demo1).setOnClickListener(this);
        findViewById(R.id.demo2).setOnClickListener(this);
        findViewById(R.id.demo3).setOnClickListener(this);
        findViewById(R.id.demo4).setOnClickListener(this);
        findViewById(R.id.demo5).setOnClickListener(this);
        findViewById(R.id.demo6).setOnClickListener(this);
        findViewById(R.id.demo7).setOnClickListener(this);
        findViewById(R.id.demo8).setOnClickListener(this);
        findViewById(R.id.warning).setOnClickListener(this);
        findViewById(R.id.test).setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.demo1:
                intent.setClass(this, SpringListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.demo2:
                intent.setClass(this, SpringListViewActivity.class);
                startActivity(intent);
                break;
//            case R.id.demo3:
//                intent.setClass(this,Demo3Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.demo4:
//                intent.setClass(this,Demo4Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.demo5:
//                intent.setClass(this,Demo5Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.demo6:
//                intent.setClass(this,Demo6Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.demo7:
//                intent.setClass(this,Demo7Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.demo8:
//                intent.setClass(this,Demo8Activity.class);
//                startActivity(intent);
//                break;
//            case R.id.warning:
//                intent.setClass(this, WarningActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.test:
//                intent.setClass(this, TestActivity.class);
//                startActivity(intent);
            //break;
        }
    }

}