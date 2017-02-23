package com.example.wxq.wxqutilslibrary.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.R;
import com.example.wxq.wxqutilslibrary.imageloadutils.log.LogUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import specialtools.ActivityManager;

/**
 * Created by Administrator on 2016/8/17 0017.
 * 主要功能为：抽取最基本的activity 拥有基本的方法 第一步抽象
 * 分功能为： 添加友盟统计 常用的东西 eventbus 等等
 * abstract 类可以不实现接口的方法注意！！！
 * 这是最基础的activity封装仅仅封装了标题栏以及一些工具
 * eventbus post不同的消息对象 不同数据给接收方处理
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private View mContextView = null;
    private boolean isDebug;
    private String APP_NAME;
    protected final String TAG = this.getClass().getSimpleName();
    private RelativeLayout llRoot;
    private LinearLayout llBasetitleBack;
    private TextView tvBasetitleTitle;
    private TextView tvBasetitleOK;
    private int width;
    private int height;
    private float density;
    private int densityDpi;
    public Handler mhandler = new MyHandler(this) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activity != null && !activity.isFinishing()) {
                dealWithHandMessage(msg);
            }
        }
    };


    private LinearLayout ll_basetitle;
    // 处理系统发出的广播
    private BroadcastReceiver broadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);//父类的带货有自己的
        //写死竖屏
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //处理Intent(主要用来获取其中携带的参数)
        if (getIntent() != null) {
            handleIntent(getIntent());
        }
        // 初始化基类activity控件
        findView();
        ActivityManager.getInstance().addActivity(this);
        //注册广播
        initBroadcastAction();
        //注册eventbus
        EventBus.getDefault().register(this);
        // 获取简单的一些参数
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;
        height = metric.heightPixels;
        density = metric.density;
        densityDpi = metric.densityDpi;
        //初始化子类activity的数据
//        initData();
//        initView();
//        initListener();
    }

    public void handleIntent(Intent intent) {
    }

    ;
//    public  void initView(){}
//    public  void initData(){}
//    public  void initListener(){}

    private void initBroadcastAction() {
        if (setBroadcastAction() != null && setBroadcastAction().size() > 0) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dealWithBroadcastAction(intent.getAction());//之类可以覆盖
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            for (String action : setBroadcastAction()
                    ) {
                intentFilter.addAction(action);
            }
            registerReceiver(broadcastReceiver, intentFilter);
        } else {
        }
    }

    // 之类添加的action
    public ArrayList<String> setBroadcastAction() {
        return null; //默认返回空之类可以添加
    }

    public void dealWithBroadcastAction(String action) {
    }

    public void dealWithHandMessage(Message msg) {
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
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 需要activity对象所以在destory之前调用
        if (broadcastReceiver != null) {

            unregisterReceiver(broadcastReceiver);

        }
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
        ll_basetitle = (LinearLayout) findViewById(R.id.ll_basetitle);
    }


    public void setTitleHeadVisiable(boolean isVisiable) {
        if (isVisiable) {
            ll_basetitle.setVisibility(View.VISIBLE);
        } else {
            ll_basetitle.setVisibility(View.GONE);
        }
    }
    View view ;
    private SparseArray<View> mViewMap = new SparseArray<View>();
    public  <T extends View> T FindViewById(int viewId) {
        // 先从view map中查找,如果有的缓存的话直接使用,否则再从mContentView中找
        View tagetView = mViewMap.get(viewId);
        if (tagetView == null) {
            tagetView = view.findViewById(viewId);
            mViewMap.put(viewId, tagetView);
        }
        return tagetView == null ? null : (T) view.findViewById(viewId);
    }
    /*
    * 重写setContentView让继承者设置的view 添加到内容布局中
    * */
    @Override
    public void setContentView(int layoutResID) {
         view = getLayoutInflater().inflate(layoutResID, null);
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
    public abstract void widgetClick(View v);  //防止多次点击的事件监听

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
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
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

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }
    //内存优化处理

    public static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        public Activity activity;

        public MyHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            activity = mActivityReference.get();
        }
    }

    public static class MyThread extends Thread {
        WeakReference<Activity> mWeakReference;
        public Activity activity;

        public MyThread(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            activity = mWeakReference.get();

        }
    }

    public static class MyRunable implements Runnable {

        WeakReference<Activity> mWeakReference;
        public Activity activity;

        public MyRunable(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            activity = mWeakReference.get();

        }
    }


    //6.0权限处理分装给activity
    private int REQUEST_CODE_PERMISSION = 123;

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        Log.d(TAG, "获取权限成功=" + requestCode);

    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        Log.d(TAG, "获取权限失败=" + requestCode);
    }

    protected int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void skipAnotherActivity(Activity activity,
                                           Class<? extends Activity> cls,
                                           HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(activity, cls);
        Iterator<?> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, (String) value);
            }
            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            }
            if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            }
            if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            }
            if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            }
        }
        activity.startActivity(intent);
    }
    //实例化一个SerializableBook对象
//    SerializableBook book = new SerializableBook();
//    book.setAuthor("walfred");
//    book.setName("How to learn Android");
//    book.setPrice(10.00f);
//    book.setPubdate("2014-01-01");
//
//    Bundle extras = new Bundle();
//    extras.putSerializable(SerializableKey, book);
//
//    in.putExtras(extras);
//    startActivity(in);
}
//activity注意项//不退出进入后台
//  =================== =================== =================== =================== =================== =================== =================== ===================
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                || keyCode == KeyEvent.KEYCODE_HOME) {
//            moveTaskToBack(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    //singtask 模式下启动当前页面走onNewIntent方法
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);// must store the new intent unless getIntent() will return the old one
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            mTabHost.setCurrentTab(bundle.getInt("id"));
//        }
//    }
//权限子activity处理用法
//  =================== =================== =================== =================== =================== =================== =================== ===================

//    /**
//     * 打电话
//     *
//     * @param view
//     */
//    public void onClick1(View view) {
//        requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 0x0001);
//    }
//    /**
//     * 权限成功回调函数
//     *
//     * @param requestCode
//     */
//    @Override
//    public void permissionSuccess(int requestCode) {
//        super.permissionSuccess(requestCode);
//        switch (requestCode) {
//            case 0x0001:
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13468857714"));
//                startActivity(intent);
//                break;
//        }
//
//    }


//  =================== =================== =================== =================== =================== =================== =================== ===================
