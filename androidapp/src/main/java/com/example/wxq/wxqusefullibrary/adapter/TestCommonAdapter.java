package com.example.wxq.wxqusefullibrary.adapter;

import android.content.Context;

import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6 0006.
 */

public class TestCommonAdapter extends CommonAdapter<Object>{

    public TestCommonAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public TestCommonAdapter(Context context, int layoutResId, List<Object> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, Object item, int position) {

    }

    @Override
    public int getLayoutResId(Object item, int position) {
      return super.getLayoutResId(item, position);  //普通的adapter
    }
}
