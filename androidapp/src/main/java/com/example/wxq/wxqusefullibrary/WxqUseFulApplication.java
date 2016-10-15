package com.example.wxq.wxqusefullibrary;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.squareup.leakcanary.RefWatcher;

import org.xutils.x;

/**
 * Created by Administrator on 2016/7/15.
 */
public class WxqUseFulApplication extends Application implements Thread.UncaughtExceptionHandler{

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();

        mainThreadId = android.os.Process.myTid();
        Thread.setDefaultUncaughtExceptionHandler(this);

        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
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
