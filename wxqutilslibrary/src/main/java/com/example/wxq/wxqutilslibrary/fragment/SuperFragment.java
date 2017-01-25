package com.example.wxq.wxqutilslibrary.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/*
* 懒加载fragment 用到显示的时候才加载
* 王晓清10.25 防止覆盖
*
* */
public abstract class SuperFragment extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"; //防止fragment重叠

    public String fragmentTitle;

    private boolean isVisible;

    private boolean isPrepared;

    private boolean isFirstLoad = true;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 若 viewpager 不设置 setOffscreenPageLimit 或设置数量不够
        // 销毁的Fragment onCreateView 每次都会执行(但实体类没有从内存销毁)
        // 导致initData反复执行,所以这里注释掉
        // isFirstLoad = true;
        // 2016/04/29
        // 取消 isFirstLoad = true的注释 , 因为上述的initData本身就是应该执行的
        // onCreateView执行 证明被移出过FragmentManager initData确实要执行.
        // 如果这里有数据累加的Bug 请在initViews方法里初始化您的数据 比如 list.clear();
        //  必须设置好缓存界面否则的话对象销毁总是执行 lazyload（）mViewPager.setOffscreenPageLimit(4);
        isFirstLoad = true;
        view = inflater.inflate(getResourceId(), container, false);
        isPrepared = true;
        commonLoad(view);
        lazyLoad(view);
        return view;
    }
     // 普通加载
    public void commonLoad(View view) {
    }

    protected abstract int getResourceId();

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
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
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     *               visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad(view);
    }

    protected void onInvisible() {
    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad(View view) {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            //if (!isAdded() || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initDataAndView(view);

    }

    protected abstract void initDataAndView(View view);

    public String getTitle() {
        if (null == fragmentTitle) {
            setDefaultFragmentTitle(null);
        }
        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
    }

    public void setTitle(String title) {
        fragmentTitle = title;
    }

    /**
     * 设置fragment的Title直接调用若不显示该title 可以不做处理
     *
     * @param title 一般用于显示在TabLayout的标题
     */
    protected abstract void setDefaultFragmentTitle(String title);


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
//        只要上面的9行代码！ FragmentState没帮我们保存Hidden状态，那就我们自己来保存，在页面重启后，我们自己来决定Fragment是否显示！
//        解决思路转变了，由Activity/父Fragment来管理子Fragment的Hidden状态转变为 由Fragment自己来管理自己的Hidden状态！
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        public Activity activity;

        public MyHandler(Activity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
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


    private int permissionRequestCode = 88;
    private PermissionCallback permissionRunnable;

    public interface PermissionCallback {
        void hasPermission();

        void noPermission();
    }

    /**
     * Android M运行时权限请求封装(可用于activity和fragment)
     *
     * @param permissionDes 权限描述
     * @param runnable      请求权限回调
     * @param permissions   请求的权限（数组类型），直接从Manifest中读取相应的值，比如Manifest.permission.WRITE_CONTACTS
     */
    public void performCodeWithPermission(@NonNull String permissionDes, PermissionCallback runnable, @NonNull String... permissions) {
        if (permissions == null || permissions.length == 0) return;
//    this.permissionrequestCode = requestCode;
        this.permissionRunnable = runnable;
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || checkPermissionGranted(permissions)) {
            if (permissionRunnable != null) {
                permissionRunnable.hasPermission();
                permissionRunnable = null;
            }
        } else {
            //permission has not been granted.
            requestPermission(permissionDes, permissionRequestCode, permissions);
        }

    }

    private boolean checkPermissionGranted(String[] permissions) {
        boolean flag = true;
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), p) != PackageManager.PERMISSION_GRANTED) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void requestPermission(String permissionDes, final int requestCode, final String[] permissions) {
        if (shouldShowRequestPermissionRationale(permissions)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example, if the request has been denied previously.

//            Snackbar.make(getWindow().getDecorView(), requestName,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.common_ok, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ActivityCompat.requestPermissions(BaseAppCompatActivity.this,
//                                    permissions,
//                                    requestCode);
//                        }
//                    })
//                    .show();
            //如果用户之前拒绝过此权限，再提示一次准备授权相关权限
            new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage(permissionDes)
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //     ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
                            requestPermissions(permissions, requestCode);
                        }
                    }).show();
        } else {
            requestPermissions(permissions, requestCode);
        }
    }

    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        boolean flag = false;
        for (String p : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == permissionRequestCode) {
            if (verifyPermissions(grantResults)) {
                if (permissionRunnable != null) {
                    permissionRunnable.hasPermission();
                    permissionRunnable = null;
                }
            } else {
                showToast("暂无权限执行相关操作！");
                if (permissionRunnable != null) {
                    permissionRunnable.noPermission();
                    permissionRunnable = null;
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    public boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}

//fragment 中请求权限  MPermissions 推荐可以使用不过有插件得加入
// frgment中处理权限回调在Fragment中申请权限，不要使用ActivityCompat.requestPermissions,
// 直接使用Fragment的requestPermissions方法，否则会回调到Activity的 onRequestPermissionsResult
//    如果在Fragment中嵌套Fragment，在子Fragment中使用requestPermissions方 法，onRequestPermissionsResult不会回调回来，
//    建议使用 getParentFragment().requestPermissions方法，这个方法会回调到父Fragment中的onRequestPermissionsResult，加入以下代码可以把回调透传到子Fragment
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        List<Fragment> fragments = getChildFragmentManager().getFragments();
////        if (fragments != null) {
////            for (Fragment fragment : fragments) {
////                if (fragment != null) {
////                    fragment.onRequestPermissionsResult(requestCode,permissions,grantResults); // 回调给子处理
////                }
////            }
////        }
//    }
//Tips：
//
//        1）BaseActivity要继承AppCompatActivity
//        2）support包使用尽量新的，我使用的是compile 'com.android.support:appcompat-v7:23.0.1' 以防里面的ActivityCompat找不到相关类或方法。
//        3）如果在Fragment中使用，直接在自己的BaseFragment写个方法调用此Activity的方法即可。
///**
// * Android M运行时权限请求封装
// * @param permissionDes 权限描述
// * @param runnable 请求权限回调
// * @param permissions 请求的权限（数组类型），直接从Manifest中读取相应的值，比如Manifest.permission.WRITE_CONTACTS
// */
//public void performCodeWithPermission(@NonNull String permissionDes,BaseAppCompatActivity.PermissionCallback runnable,@NonNull String... permissions){
//        if(getActivity()!=null && getActivity() instanceof BaseAppCompatActivity){
//        ((BaseAppCompatActivity) getActivity()).performCodeWithPermission(permissionDes,runnable,permissions);
//        }
//        }

//    performCodeWithPermission("XX App请求访问相机权限",new BaseAppCompatActivity.PermissionCallback() {
//        @Override
//        public void hasPermission() {
//            //执行打开相机相关代码
//        }
//        @Override
//        public void noPermission() {
//        }
//    }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);