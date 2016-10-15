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
        Toast.makeText(getApplicationContext(),"qidong ",Toast.LENGTH_LONG).show();

    }

    @Override
    public void dealWithException(Throwable ex) {
        //发生异常处理
        Toast.makeText(getApplicationContext(),"nonon",Toast.LENGTH_LONG).show();
//        Intent intent2 = new Intent();
//        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent2.setClass(this, Main2Activity.class);
//        startActivity(intent2);

    }
}
