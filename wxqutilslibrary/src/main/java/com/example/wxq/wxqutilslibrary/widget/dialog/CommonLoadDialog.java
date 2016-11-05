package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.wxq.wxqutilslibrary.R;

/**
 * @author wxq
 * @version V_5.0.0
 * @date 2016/10/10 0010
 * @description
 */
public class CommonLoadDialog {

    private static CommonLoadDialog alertDialog;

    public static CommonLoadDialog getInstance() {
        if (alertDialog == null) {

            alertDialog = new CommonLoadDialog();

        }

        return alertDialog;
    }

    private Dialog dialog;

    public Dialog createAlertDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.layout_common_loading_dialog, null);// 得到加载view
      RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
////		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
//        TextView tv_title = (TextView) v.findViewById(R.id.tvPromptMsg);
//        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
//        Button btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);
//        tv_title.setText(msg);
//        btnCancle.setOnClickListener(canclelistener);
//        btnSure.setOnClickListener(surelistener);
        dialog = new Dialog(context, R.style.textDialogStyle);// 创建自定义样式dialog
        dialog.setCancelable(true);// 可以用“返回键”取消
        dialog.setContentView(layout);// 设置布局
        return dialog;


    }

    public void dismiss() {
        dialog.dismiss();
    }




}
