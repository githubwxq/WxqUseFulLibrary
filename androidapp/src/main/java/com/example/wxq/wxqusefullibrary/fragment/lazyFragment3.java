package com.example.wxq.wxqusefullibrary.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class lazyFragment3 extends SuperFragment {



    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_fragment12, container, false);
    }

    @Override
    protected void initData() {
        Log.i("wxq","initdata3");
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }


}
