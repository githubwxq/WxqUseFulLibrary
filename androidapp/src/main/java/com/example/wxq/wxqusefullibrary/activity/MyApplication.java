package com.example.wxq.wxqusefullibrary.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.Main2Activity;
import com.example.wxq.wxqutilslibrary.application.BaseApplication;
//import com.facebook.stetho.Stetho;

//import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/10/4.
 */
public class MyApplication extends BaseApplication {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    protected void initResourceAndother() {
        Log.i("wxq","application 启动");
        Toast.makeText(getApplicationContext(),"启动 ",Toast.LENGTH_LONG).show();
        //第一：默认初始化
     //   Bmob.initialize(this, "Your Application ID");

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


    }

    @Override
    public void dealWithException(Throwable ex) {
        //发生异常处理
        Toast.makeText(getApplicationContext(),"程序错误",Toast.LENGTH_LONG).show();
        Intent intent2 = new Intent();
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.setClass(this, Main2Activity.class);
        startActivity(intent2);
    }
}
