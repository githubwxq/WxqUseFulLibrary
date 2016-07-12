package com.example.wxq.wxqusefullibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test1Fragment extends Fragment {

    @BindView(R.id.btn_testfragment1)
    Button btnTestfragment1;
    @BindView(R.id.btn_testfragment2)
    Button btnTestfragment2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_my_rx, null);


        ButterKnife.bind(this, view);
        btnTestfragment1.setText("butterknife");

        return view;
    }
}
