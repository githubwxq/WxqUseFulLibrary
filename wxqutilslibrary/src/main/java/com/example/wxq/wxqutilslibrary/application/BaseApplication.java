package com.example.wxq.wxqutilslibrary.application;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

import static specialtools.LogUtils.*;

public abstract class BaseApplication extends Application {
    /*
    * 初始化TAG
    * */
    private static String TAG = BaseApplication.class.getName();

    /*Activity堆*/
    private Stack<Activity> activityStack = new Stack<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();
        d(TAG, TAG + "---onCreate()");



        printAppParameter();
        initOthers();
    }


    public abstract void initOthers();

    /*打印出一些app的参数*/
    public abstract void printAppParameter();

    public void addActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        if (activityStack != null) {
            Activity activity = activityStack.lastElement();
            return activity;
        }
        return null;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        if (activityStack != null) {
            Activity activity = activityStack.lastElement();
            killActivity(activity);
        }
    }


    public void removeActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        if (cls != null && activityStack != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                if (activityStack.get(i).getClass().equals(cls)) {
                    killActivity(activityStack.get(i));
                }
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null && activityStack != null) {
            if (activityStack.contains(activity)) {
                activityStack.remove(activity);
                activity.finish();
                activity = null;
            }
        }
    }

    //获取最后一个Activity
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回寨内Activity的总数
    public int howManyActivities() {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void exit() {

        finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}