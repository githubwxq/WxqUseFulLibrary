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
 *
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class lazyFragment4 extends SuperFragment {



    @Override
    protected int getResourceId() {
        return R.layout.fragment_lazy_fragment22;
    }

    @Override
    protected void initDataAndView(View view) {
        Log.i("wxq","initdata4");
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }


}
