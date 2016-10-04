package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import specialtools.ActivityManager;

public class TestBaseActivity extends BaseActivity {


    private TextView tv_test_title;
    private RelativeLayout rl_root;
    private TextView tv_test_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base);
        initView();
        setTitleText("测试标题栏集成");
        setOKText("右边");
        tv_test_title.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_title:
                ActivityManager.getInstance().killActivity(this);
                break;


        }
    }


    private void initView() {
        tv_test_title = (TextView) findViewById(R.id.tv_test_title);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        tv_test_name = (TextView) findViewById(R.id.tv_test_name);
        tv_test_name.setOnClickListener(this);
    }
}
