package com.example.wxq.wxqutilslibrary.widget.popupwindow;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2016/7/5.
 * 继承控件对控件的功能扩展 而写个类调用控件是对控件功能的使用 使用
 */
public class CommonPopupWindow {
    PopupWindow popupWindow;

//简单的popupwindow 自定义布局传入 布局id 代码不抽取的话直接放mainactiviy中执行
    public  void easyPopupWindow(Activity context,View view ,int id) {
        if (popupWindow == null) {
            View layout = context.getLayoutInflater().inflate(id, null);//跟换布局

            popupWindow = new PopupWindow(layout, AbsListView.LayoutParams.WRAP_CONTENT,
                    AbsListView.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());//必须要设置背景
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);

        }
        //在view的下面显示view在外面处理
        popupWindow.showAsDropDown(view,
               11, 10);
        popupWindow.update();
    }


   public void disspop(){
    popupWindow.dismiss();


  }





//
//
//    popupwindow = new PopupWindow(contentView, -2, -2);
//    // 动画播放有一个前提条件： 窗体必须要有背景资源。 如果窗体没有背景，动画就播放不出来。
//    popupwindow.setBackgroundDrawable(new ColorDrawable(
//            Color.TRANSPARENT));
//    int[] location = new int[2];
//    view.getLocationInWindow(location);
//    popupwindow.showAtLocation(parent, Gravity.LEFT
//    + Gravity.TOP, 60, location[1]);
//    ScaleAnimation sa = new ScaleAnimation(0.5f, 1.0f, 0.5f,
//            1.0f, Animation.RELATIVE_TO_SELF, 0,
//            Animation.RELATIVE_TO_SELF, 0.5f);
//    sa.setDuration(200);
//    AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
//    aa.setDuration(200);
//    AnimationSet set = new AnimationSet(false);
//    set.addAnimation(aa);
//    set.addAnimation(sa);
//    contentView.startAnimation(set);
//



}
