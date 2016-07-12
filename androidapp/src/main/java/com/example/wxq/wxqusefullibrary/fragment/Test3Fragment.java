package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test3Fragment extends Fragment {


    @BindView(R.id.tv_f3)
    TextView tvF3;

    //ctrl +alt +k 生命周期排序
    @Override
    public void onAttach(Context context) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreateView...");
        View view = inflater.inflate(R.layout.test3fragment, null);


        ButterKnife.bind(this, view);

        tvF3.setText("fragment3");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(this.getClass().getName(), "ArrayListFragment **** onActivityCreated...");

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onResume...");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onPause...");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStop...");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroyView...");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDetach...");
        // TODO Auto-generated method stub
        super.onDetach();
    }


}

