package com.example.wxq.wxqusefullibrary;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2016/7/15.
 */
public class WxqUseFulApplication extends Application implements Thread.UncaughtExceptionHandler{

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    //内存泄漏观察者
    public static RefWatcher getRefWatcher(Context context) {
        WxqUseFulApplication application = (WxqUseFulApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        refWatcher= LeakCanary.install(this);//加载内存泄漏工具并获取观察者
        mainThreadId = android.os.Process.myTid();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        CommonTools.outputError(ex);
    }
}
