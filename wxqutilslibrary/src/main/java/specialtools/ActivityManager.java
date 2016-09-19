package specialtools;


import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * author wxq
 * vesion 1.2.1
 * data 2016
 * 应用程序Actiivty的管理类
 */
public class ActivityManager {

    private static Stack<Activity> activitys;

    private static ActivityManager am;//��ǰ���ʵ����ʹ�õĵ���ģʽ



    private ActivityManager() {
        if (null == activitys)
            activitys = new Stack<>();
    }

    public static synchronized ActivityManager getInstance() {
        if (null == am)
            am = new ActivityManager();
        return am;
    }

    public synchronized void addActivity(Activity activity) {
        activitys.add(activity);
    }


    public Activity getTopActivity() {
        if (null == activitys || activitys.size() == 0)
            return null;
        return activitys.lastElement();//����ջ�������һ��Ԫ�س�ȥ
    }


    public void killActivity(Activity activity) {
        if (null == activity)
            return;
        activitys.remove(activity);
        activity.finish();
    }


    public void killTopActivity() {
        Activity topActivity = getTopActivity();
        killActivity(topActivity);
    }


    public void killActivity(Class<?> cls) {
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls))
                killActivity(activity);
        }
    }


    public void killAllActivity() {
        for (Activity a : activitys) {
            if (null != a) {
                a.finish();
            }
        }
        activitys.clear();
    }

    //退出应用程序
    public void AppExit(Context context){
        try{
            killAllActivity();
            android.app.ActivityManager  activityManager=(android.app.ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());

        }catch (Exception e){}


    }



}
