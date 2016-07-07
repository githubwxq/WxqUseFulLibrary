package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.wxq.wxqutilslibrary.R;


/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月25日
 * @description 确定dialog
 * 单列维护    context 最好用getapplication的context
 * 只要确定 信息看看而已  之间用clicklistener 的接口回调就可以了 不用在自己弄个
 */
public class CustomSureDialog {
    private static CustomSureDialog instance;
    private Dialog alertDialog;

    private CustomSureDialog(){

    }

    public static CustomSureDialog getInstance() {
        if (instance == null) {
            instance = new CustomSureDialog();
        }
        return instance;
    }

    public Dialog createAlertDialog(Context context, String content, String btncontent, View.OnClickListener listener) {
        alertDialog = new Dialog(context, R.style.textDialogStyle);// 创建自定义样式dialog
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
                                     //R.layout.layout_sure_dialog
            View v = inflater.inflate(0, null);// 得到加载view

            Button btnSure = (Button) v.findViewById(0);

            btnSure.setText(btncontent);

            btnSure.setOnClickListener(listener);//回调直接外面人处理 但这个 回调只能回调view 不能回调其他的

            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);// 不可以用“返回键”取消
            alertDialog.setContentView(v);// 设置布局
        }catch (Exception e){
            e.printStackTrace();
        }
        return alertDialog;
    }

    public void cancelAlertDialog() {
        if (alertDialog != null) {
            alertDialog.cancel();
        }
    }

    public boolean isAlertDialog() {
        boolean flag = false;
        if (alertDialog != null) {
            flag = alertDialog.isShowing();
        }else{
            flag = false;
        }
        return flag;
    }

}
