package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqutilslibrary.R;

/**
 * Created by Administrator on 2016/10/7.
 */
public class CommonAlertDialog {
    private  static  CommonAlertDialog alertDialog;

    public static CommonAlertDialog getInstance(){
        if(alertDialog==null){

            alertDialog=new CommonAlertDialog();

        }

        return alertDialog;
    }

    private Dialog dialog;

    public Dialog createAlertDialog(Context context, String msg, String cancleBtnMsg, String sureBtnMsg, View.OnClickListener canclelistener, View.OnClickListener surelistener){

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_common_dialog, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
//		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
        TextView tv_title = (TextView) v.findViewById(R.id.tvPromptMsg);
        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
        Button btnSure = (Button) v.findViewById(R.id.btnSure);
        btnCancle.setText(cancleBtnMsg);
        btnSure.setText(sureBtnMsg);
        tv_title.setText(msg);
        btnCancle.setOnClickListener(canclelistener);
        btnSure.setOnClickListener(surelistener);
        dialog = new Dialog(context, R.style.textDialogStyle);// 创建自定义样式dialog
        dialog.setCancelable(true);// 可以用“返回键”取消
        dialog.setContentView(layout);// 设置布局
        return dialog;




    }

public void dismiss(){
    dialog.dismiss();
}




}
