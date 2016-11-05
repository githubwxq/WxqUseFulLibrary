package com.example.wxq.wxqusefullibrary.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.CommonTools;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

public class DifferentLocationDialogActivity extends BaseActivity {

    private TextView mTvFromBottom;
    private TextView mTvFromCenter;
    private TextView mTvFromPercent;

    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_location_dialog);
        initView();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_from_bottom:
                showBottomDialog();
                break;
            case R.id.tv_from_center:
                showCenterDialog();
                break;
            case R.id.tv_from_percent:
                showPercentDialog();
                break;

        }
    }

    private void showPercentDialog() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.commentpopmenu, null);
        dialog = new Dialog(this, R.style.chooseDialog);//chooseDialog 样式
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        final Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText("取消");
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });
        //在show调用之后设置宽度铺满
        Window window = dialog.getWindow();//dialog为窗体层次
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = CommonTools.getScreenWidth(this);
        // lp.height
        window.setAttributes(lp);
    }

    private void showCenterDialog() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.commentpopmenu, null);
        dialog = new Dialog(this, R.style.chooseDialog);//chooseDialog 样式
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        final Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText("取消");
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });
        //在show调用之后设置宽度铺满
        Window window = dialog.getWindow();//dialog为窗体层次
        window.setGravity(Gravity.CENTER);
        // window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = CommonTools.getScreenWidth(this);
        // lp.height
        window.setAttributes(lp);

    }

    private void showBottomDialog() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.commentpopmenu, null);
        dialog = new Dialog(this, R.style.chooseDialog);//chooseDialog 样式
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        final Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setText("取消");
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });
        //在show调用之后设置宽度铺满
        Window window = dialog.getWindow();//dialog为窗体层次
        window.setGravity(Gravity.BOTTOM);
       // window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = CommonTools.getScreenWidth(this);
       // lp.height
        window.setAttributes(lp);


    }



    private void initView() {
        mTvFromBottom = (TextView) findViewById(R.id.tv_from_bottom);
        mTvFromCenter = (TextView) findViewById(R.id.tv_from_center);
        mTvFromPercent = (TextView) findViewById(R.id.tv_from_percent);
        mTvFromBottom.setOnClickListener(this);
        mTvFromCenter.setOnClickListener(this);
        mTvFromPercent.setOnClickListener(this);
    }


}
