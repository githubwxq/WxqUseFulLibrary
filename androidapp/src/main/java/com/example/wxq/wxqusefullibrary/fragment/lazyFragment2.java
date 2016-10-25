package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;


/**
 * create an instance of this fragment.
 */
public class lazyFragment2 extends SuperFragment {
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_fragment22, container, false);
    }

    @Override
    protected void initData() {
        Log.i("wxq", "initdata2");
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
