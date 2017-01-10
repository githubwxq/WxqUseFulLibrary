package com.example.wxq.wxqutilslibrary.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.wxq.wxqutilslibrary.myutils.imageloader.LoadingImgUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import specialtools.ActivityManager;

public abstract class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {
    private int count;
    private Context mContext;
    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);//捕获系统异常
        mContext = getApplicationContext();
        //初始化imageloader
        LoadingImgUtil.initImageLoader(mContext);
        // 注册xutils
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

        //   开启友盟统计
        MobclickAgent.setSessionContinueMillis(1000);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setDebugMode(false);

        // 内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        //  LeakCanary.install(this);
        initResourceAndother();
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

    }

    protected abstract void initResourceAndother();

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e("wxq", outputError(ex));
        dealWithException(ex);// 给上层处理异常  可以重启服务发警告等等
        MobclickAgent.onKillProcess(this);
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