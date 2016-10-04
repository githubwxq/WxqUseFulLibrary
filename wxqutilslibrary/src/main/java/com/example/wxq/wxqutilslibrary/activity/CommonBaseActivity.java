package com.example.wxq.wxqutilslibrary.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqutilslibrary.R;
import com.example.wxq.wxqutilslibrary.myutils.log.LogUtils;

/**
 * Created by Administrator on 2016/8/17 0017.
 * 主要功能为：抽取最基本的activity 拥有基本的方法 第一步抽象
 * 分功能为： 添加友盟统计 常用的东西 eventbus 等等
 * 分功能为：
 */
public abstract class CommonBaseActivity extends FragmentActivity {
    public String className = this.getClass().getSimpleName();
    private RelativeLayout llRoot;
    private LinearLayout llBasetitleBack;
    private TextView tvBasetitleTitle;
    private TextView tvBasetitleOK;
    private LinearLayout ll_basetitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);//父类的带货有自己的
        findView();//加载标题控件
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        View view = getLayoutInflater().inflate(getLayoutId(), null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.ll_basetitle);
        if (null != llRoot)
            llRoot.addView(view, lp);
        initView(view);
        initData();

    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getLayoutId();

    private void findView() {
        llRoot = (RelativeLayout) findViewById(R.id.ll_basetitle_root);
        llBasetitleBack = (LinearLayout) findViewById(R.id.ll_basetitle_back);
        tvBasetitleTitle = (TextView) findViewById(R.id.tv_basetitle_title);
        tvBasetitleOK = (TextView) findViewById(R.id.tv_basetitle_ok);
        ll_basetitle=(LinearLayout)findViewById(R.id.ll_basetitle);
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
    }

    //ctrl alt k
    public void showLog(String content) {
        LogUtils.i(className, content);
    }

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

    public void setTitleBarVisiable(boolean visiable) {
        if(visiable){
            ll_basetitle.setVisibility(View.VISIBLE);
        }else{
            ll_basetitle.setVisibility(View.GONE);
        }

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
}
