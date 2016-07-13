package com.example.wxq.wxqusefullibrary.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test6Fragment extends BaseFragment {

    public static final String RXTAG="Test6Fragment";

    @BindView(R.id.tv_f6)
    TextView tvF6;
    @BindView(R.id.btn_f6)
    Button btnF6;

    // 可见时调用
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initData(View view) {
        tvF6.setText("我是fragment6");

    }

    @Override
    public int getLayoutId() {
        return R.layout.test6fragment;
    }


//接口触发就应该这样实现
    @OnClick({R.id.tv_f6, R.id.btn_f6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_f6:
                break;
            case R.id.btn_f6:
                btnF6.setText("点击了我了");
                break;
        }
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RXTAG)
            }
    )
    public void getInforn(String info) {
        tvF6.setText("我接受到了"+info);
    }





}

