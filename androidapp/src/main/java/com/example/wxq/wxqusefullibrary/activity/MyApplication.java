package com.example.wxq.wxqusefullibrary.activity;

import android.util.Log;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.application.BaseApplication;

/**
 * Created by Administrator on 2016/10/4.
 */
public class MyApplication extends BaseApplication {
    @Override
    protected void initResourceAndother() {
        Log.i("wxq","application 启动");


    }

    @Override
    public void dealWithException(Throwable ex) {
        Toast.makeText(getApplicationContext(),"nonon",Toast.LENGTH_LONG).show();
    }
}
