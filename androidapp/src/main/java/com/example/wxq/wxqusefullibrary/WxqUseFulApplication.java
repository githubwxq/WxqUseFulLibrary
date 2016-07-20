package com.example.wxq.wxqusefullibrary;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2016/7/15.
 */
public class WxqUseFulApplication extends Application implements Thread.UncaughtExceptionHandler{

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
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
