package com.example.wxq.wxqutilslibrary.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.example.wxq.wxqutilslibrary.Global;
import com.example.wxq.wxqutilslibrary.imageloadutils.imageloader.LoadingImgUtil;
import com.jingewenku.abrahamcaijin.commonutil.application.AppUtils;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import specialtools.ActivityManager;

//import com.jingewenku.abrahamcaijin.commonutil.application.AppUtils;

public abstract class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {
    private int count;
    public static Context mContext;

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
     //   Stetho.initializeWithDefaults(this);

        // 内存泄漏检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        refWatcher = LeakCanary.install(this);
//        LeakCanary.install(this);
        Utils.init(getApplicationContext());  // 初始化工具类
        AppUtils.init(this);


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
        String time = getErrorTime();
        writeAndUpload(mContext, time + outputError(ex).substring(16, 35), outputError(ex));//错误写入到sd卡中
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
   //存放错误到本地wxqexception文件夹 可以上传到服务器
    public void writeAndUpload(Context context, String key, String value) {
        File cacheDir = getCacheFile(context);
        FileWriter writer = null;
        File cacheFile = new File(cacheDir, key + ".txt");
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new FileWriter(cacheFile, true);
            writer.write(value);// 写入缓存
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getCacheFile(Context context) {
        File cacheFile = null;
        String externalStorageState = Environment.getExternalStorageState();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        //    cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wxq/ErrorException");
            cacheFile = new File(Global.ERROREXCEPTION);
            if (!cacheFile.exists()) {
                cacheFile.mkdir();
            }
        } else {
            cacheFile = context.getCacheDir();
        }
        return cacheFile;
    }

    public String getErrorTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日_HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }


//各种路径
//    Log.i("wxq",mContext.getExternalCacheDir().getAbsolutePath());
//    Log.i("wxq",Environment.getExternalStorageDirectory().getAbsolutePath());
//    Log.i("wxq",mContext.getCacheDir().getAbsolutePath());
//    Log.i("wxq",mContext.getFilesDir().getAbsolutePath());
//    y I/wxq: /storage/emulated/0/Android/data/com.example.wxq.wxqusefullibrary/cache
//    01-18 18:03:00.120 3379-3379/com.example.wxq.wxqusefullibrary I/wxq: /storage/emulated/0
//    01-18 18:03:00.120 3379-3379/com.example.wxq.wxqusefullibrary I/wxq: /data/data/com.example.wxq.wxqusefullibrary/cache
//    01-18 18:03:00.120 3379-3379/com.example.wxq.wxqusefullibrary I/wxq: /data/data/com.example.wxq.wxqusefullibrary/files

//
//    Application 提供有一个 registerActivityLifecycleCallbacks() 的方法,需要传入的参数就是这个 ActivityLifecycleCallbacks 接口,作用和你猜的没错,就是在你调用这个方法传入这个接口实现类后,
//    系统会在每个 Activity 执行完对应的生命周期后都调用这个实现类中对应的方法,请记住是每个!
//

//字体不改变
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();
            //设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                createConfigurationContext(newConfig);
            } else {
                res.updateConfiguration(newConfig, res.getDisplayMetrics());
            }
        }
        return res;
    }

}