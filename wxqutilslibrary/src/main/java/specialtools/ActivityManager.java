package specialtools;


import android.app.Activity;

import java.util.Stack;

/**
 * ��Ŀ���ƣ�UtilsLib
 * ���ߣ�lb291
 * ���䣺 lb291700351@live.cn
 * ʱ�䣺2016/5/25 16:29
 * ��������Activity��صĹ���,ʹ�õ���ģʽ��һ��Ӧ�ó���ֻ������һ��Activity��ջ�Ĺ�����
 */
public class ActivityManager {
    //===Desc:��Ա����======================================================================================
    /**
     * ����Activity��ջ
     */
    private static Stack<Activity> activitys;

    private static ActivityManager am;//��ǰ���ʵ����ʹ�õĵ���ģʽ


    //===Desc:���캯��======================================================================================

    /**
     * ˽�л����캯��
     */
    private ActivityManager() {
        if (null == activitys)
            activitys = new Stack<>();
    }

    /**
     * ʹ�õ���ģʽ��ȡ��ǰ��ĵ�һʵ��
     *
     * @return ��ǰ��ĵ�һʵ��
     */
    public static synchronized ActivityManager getInstance() {
        if (null == am)
            am = new ActivityManager();
        return am;
    }

    //===Desc:�ṩ�����ʹ�õľ�̬����==========================================================================================��

    /**
     * ���һ��Activity����ջ
     *
     * @param activity ��Ҫ��ӵ�Activity����
     */
    public synchronized void addActivity(Activity activity) {
        activitys.add(activity);
    }

    /**
     * ��ȡջ����Activity
     *
     * @return �����ջ�д����activity���򷵻�ջ����Activity���������ջ��û�д����activity���򷵻�null
     */
    public Activity getTopActivity() {
        if (null == activitys || activitys.size() == 0)
            return null;
        return activitys.lastElement();//����ջ�������һ��Ԫ�س�ȥ
    }

    /**
     * �رմ���ڶ�ջ�е�Activity
     *
     * @param activity ��Ҫ�رյ�activity
     */
    public void killActivity(Activity activity) {
        if (null == activity)
            return;
        activitys.remove(activity);
        activity.finish();
    }

    /**
     * �ر�ջ����Activity
     */
    public void killTopActivity() {
        Activity topActivity = getTopActivity();
        killActivity(topActivity);
    }

    /**
     * �ر�ָ�����ֵ�activity
     *
     * @param cls Activity��class����
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls))
                killActivity(activity);
        }
    }

    /**
     * �رմ���ڶ�ջ�����е�activity
     */
    public void killAllActivity() {
        for (Activity a : activitys) {
            if (null != a) {
                a.finish();
            }
        }
        activitys.clear();//��ն�ջ
    }


}
