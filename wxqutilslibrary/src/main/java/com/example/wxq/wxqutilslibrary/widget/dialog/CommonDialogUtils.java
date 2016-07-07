package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.wxq.wxqutilslibrary.R;

/**
 * Created by Administrator on 2016/7/5.
 * //完全自定义布局到时候添加布局以及回调接口
 */
public class CommonDialogUtils {



    public static void showDailog(Context context,int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(context, id, null);//吧0 换成布局id 简单的dialog完成
        // dialog.setView(view);//
        dialog.setView(view, 0, 0, 0, 0);//
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
      //  params.width = 1000;
      //  params.height = 800;
        params.gravity= Gravity.BOTTOM;//排列在底部
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setWindowAnimations(R.style.textDialogStyle);


    }
}
