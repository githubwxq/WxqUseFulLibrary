package com.example.wxq.wxqutilslibrary.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import specialtools.ActivityManager;

public abstract class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {
    private int count;
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);//捕获系统异常
        mContext = getApplicationContext();
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (count == 0) {
                    Log.i("wxq", "=前台=");
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    Log.i("wxq", "=后台后台=");
                }


            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        initResourceAndother();
    }

    protected abstract void initResourceAndother();

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        outputError(ex);
        dealWithException(ex);
        ActivityManager.getInstance().AppExit(mContext);
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public abstract void dealWithException(Throwable ex);

    public String outputError(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        return writer.toString();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try {
            System.gc();
        } catch (Exception e) {
        }
    }

    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
        try {
            System.gc();
        } catch (Exception e) {
        }
    }


}