package com.example.wxq.wxqutilslibrary.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;


import com.example.wxq.wxqutilslibrary.myutils.log.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wy
 * @version V_5.0.0
 * @date 2016/3/1
 * @description 基类fragment 懒加载注意第一个界面数据先给
 */
public abstract class LazyLoadBaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    /**
     * Fragment当前状态是否可见 使用setUserVisibleHint，这个是每次都会调用的
     */
    protected boolean isVisible;
    protected Handler mHandler = null;
    protected boolean canOpen = true;//用来防止多次点击打开多个页面 点击跳转下一个界面时

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        showLog(this.getClass().getSimpleName()+"fragment可见"+ time);
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        showLog(this.getClass().getSimpleName()+"fragment不可见"+ time);
    }

    /**
     * 延迟加载 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    public abstract void stateUpdate();

    public void showLog(String content) {
        showLog(content, null);
    }

    public void showLog(String content, Throwable ex) {
        if (ex == null) {
            LogUtils.i("fragment",content);

        } else {
            LogUtils.i("fragment",ex.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        canOpen = true;
    }
}
