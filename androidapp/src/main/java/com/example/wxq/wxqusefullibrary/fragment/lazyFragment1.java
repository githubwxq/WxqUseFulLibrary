package com.example.wxq.wxqusefullibrary.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class lazyFragment1 extends SuperFragment {

    private TextView mTest1;

    @Override
    protected int getResourceId() {
        return R.layout.fragment_lazy_fragment1;
    }

    @Override
    protected void initDataAndView(View view) {

        Log.i("wxq", "initdata1");
        mTest1 = (TextView) view.findViewById(R.id.test1);
        mTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("我来自fragment1");
            }
        });


        performCodeWithPermission("XX App请求访问相机权限", new PermissionCallback() {
            @Override
            public void hasPermission() {
                //执行打开相机相关代码
                showToast("获取到了执行相机的权限"); // 异步人家成功后调用你的
            }

            @Override
            public void noPermission() {
                showToast("获取到了相机的权限失败"); // 异步人家成功后调用你的
            }
        }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);


    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message) {
        showToast("fragment1 收到消息" + message);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }



    //添加guan广播接收者显示或者取消小红点
    @Override
    public ArrayList<String> setBroadcastAction() {
        ArrayList<String> action=new ArrayList<>();
        action.add("111");
        action.add("222");
        return action;
    }

    @Override
    public void dealWithBroadcastAction(String action , Intent intent) {

        switch (action) {
            case "111":
                //隐藏小红点
                  showToast("1111111111111111111111111");

                break;

            case "222":
                //显示小红点

                showToast("22222222222222222222222");
                break;
        }

    }

}
