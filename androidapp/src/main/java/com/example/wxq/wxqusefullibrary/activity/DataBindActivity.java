package com.example.wxq.wxqusefullibrary.activity;

import android.app.Activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.databinding.DatabindBinding;
import com.example.wxq.wxqusefullibrary.model.User;


public class DataBindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  ActivityBaseBinding 类是自动生成的

        final DatabindBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.databind);

        User user = new User("Connor", "Lin");
        // 所有的 set 方法也是根据布局中 variable 名称生成的
       // DataBindingUtil.setContentView(this, R.layout.databind).set.setUser(user);
        dataBinding.setUser(user);
    }
}
