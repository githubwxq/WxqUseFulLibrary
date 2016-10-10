package com.example.wxq.wxqutilslibrary.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.R;
import com.example.wxq.wxqutilslibrary.myutils.log.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.x;

import specialtools.ActivityManager;

/**
 * Created by Administrator on 2016/8/17 0017.
 * 主要功能为：抽取最基本的activity 拥有基本的方法 第一步抽象
 * 分功能为： 添加友盟统计 常用的东西 eventbus 等等
 * abstract 类可以不实现接口的方法注意！！！
 * 这是最基础的activity封装仅仅封装了标题栏以及一些工具
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private View mContextView = null;
    private boolean isDebug;
    private String APP_NAME;
    protected final String TAG = this.getClass().getSimpleName();
    private RelativeLayout llRoot;
    private LinearLayout llBasetitleBack;
    private TextView tvBasetitleTitle;
    private TextView tvBasetitleOK;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);//父类的带货有自己的
        findView();
        ActivityManager.getInstance().addActivity(this);
        //注册eventbus
        EventBus.getDefault().register(this);
        //注册xutils

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManager.getInstance().killActivity(this);

    }

    private void findView() {
        llRoot = (RelativeLayout) findViewById(R.id.ll_basetitle_root);
        llBasetitleBack = (LinearLayout) findViewById(R.id.ll_basetitle_back);
        tvBasetitleTitle = (TextView) findViewById(R.id.tv_basetitle_title);
        tvBasetitleOK = (TextView) findViewById(R.id.tv_basetitle_ok);
        llBasetitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*
    * 重写setContentView让继承者设置的view 添加到内容布局中
    * */
    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.ll_basetitle);
        if (null != llRoot)
            llRoot.addView(view, lp);

    }

    //ctrl alt k
    /**
     * 设置中间标题文字
     *
     * @param c
     */
    public void setTitleText(CharSequence c) {
        if (tvBasetitleTitle != null)
            tvBasetitleTitle.setText(c);
    }

    /**
     * 设置中间标题文字
     *
     * @param resId
     */
    public void setTitleText(int resId) {
        if (tvBasetitleTitle != null)
            tvBasetitleTitle.setText(resId);
    }

    /**
     * 设置右边标题
     *
     * @param c
     */
    public void setOKText(CharSequence c) {
        if (tvBasetitleOK != null)
            tvBasetitleOK.setText(c);
    }

    /**
     * 设置右边按钮是否显示
     *
     * @param visible
     */
    public void setOkVisibity(boolean visible) {
        if (tvBasetitleOK != null) {
            if (visible)
                tvBasetitleOK.setVisibility(View.VISIBLE);
            else
                tvBasetitleOK.setVisibility(View.GONE);
        }
    }

    public LinearLayout getLlBasetitleBack() {
        return llBasetitleBack;
    }

    public TextView getTvBasetitleTitle() {
        return tvBasetitleTitle;
    }


    public TextView getTvBasetitleOK() {
        return tvBasetitleOK;
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);
    }

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }


    public void showLog(String content) {
        LogUtils.i(TAG, content);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBase(String event) {
        l();
    }

    protected void l() {
        Exception exception = new Exception();
        final StackTraceElement[] stackTrace = exception.getStackTrace();
        final StackTraceElement stackTraceElement = stackTrace[1];
        final String className = stackTraceElement.getClassName();
        final String classNamePre = stackTrace[2].getClassName();
        final String methodName = stackTraceElement.getMethodName();
        final int lineNumber = stackTraceElement.getLineNumber();
        Log.i("wxq", String.format("class:%s %s method:%s:%d", classNamePre, className, methodName, lineNumber));
    }
}
