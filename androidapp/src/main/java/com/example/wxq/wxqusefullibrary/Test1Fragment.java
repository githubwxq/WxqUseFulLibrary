package com.example.wxq.wxqusefullibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wxq.wxqusefullibrary.fragment.Test6Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test1Fragment extends Fragment {




    @BindView(R.id.btn_testfragment1)
    Button btnTestfragment1;
    @BindView(R.id.btn_testfragment2)
    Button btnTestfragment2;
    @BindView(R.id.rxbus_change)
    Button rxbusChange;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_my_rx, null);


        ButterKnife.bind(this, view);
        btnTestfragment1.setText("butterknife");

        return view;
    }

    @OnClick({R.id.btn_testfragment1, R.id.btn_testfragment2, R.id.rxbus_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_testfragment1:
                btnTestfragment1.setText("butterknifebtnTestfragment1");
                RxBus.get().post(Test6Fragment.RXTAG,"我来自于第一个fragment");
                RxBus.get().post(MainActivity.RXTAG,"hellow mainactivity我来自于第一个fragment");




                break;
            case R.id.btn_testfragment2:
                break;
            case R.id.rxbus_change:
                break;
        }
    }
}
