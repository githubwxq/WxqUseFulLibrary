package com.example.wxq.wxqusefullibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;

/** 类进行管理 控件
 * Created by Administrator on 2016/7/13.
 */
public class TabHeadItem extends LinearLayout {



    ImageView ivHead;

    TextView tvTabTitle;

    public TabHeadItem(Context context) {
        super(context);
        init(context);
    }


    public TabHeadItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabHeadItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.tablayout_item, this);
        tvTabTitle= (TextView)view.findViewById(R.id.tv_tab_title);

    }
    public void setTabText(String text){

        tvTabTitle.setText(text);

    }
    public void setTabText(int color){

        tvTabTitle.setTextColor(color);

    }

}
