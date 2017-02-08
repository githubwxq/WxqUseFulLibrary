package com.example.wxq.wxqusefullibrary.bmob.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.constant.AppConstant;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.MyUser;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class BmobStartActivity extends BaseActivity {
    private static final String TAG = "StartActivity" ;
    private ImageView backgroundImage;
    private TextView imageTips;
    private Animation animation;
    private Uri background = null;
    private TextView title ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmob_start);
        Bmob.initialize(getApplicationContext(), AppConstant.APP_ID);
        initView();
    }

    private void initView() {
        setTitleHeadVisiable(false);
        backgroundImage = (ImageView) findViewById(R.id.backgroundImage);
        animation = AnimationUtils.loadAnimation(this, R.anim.entrance);
       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {
               next();
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });

        backgroundImage.startAnimation(animation);
    }

    private void next() {
        Intent intent;
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        if (user == null) {
            intent = new Intent(this, LoginActivity.class);

        } else {
//已经登入过了   到时候添加删除登入
          intent = new Intent(this, BmobIndexActivity.class);
//            //intent = new Intent(this, EditPaopaoActivity.class);
        }
        startActivity(intent);
        this.finish();


    }

    @Override
    public void widgetClick(View v) {

    }
}
