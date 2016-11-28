package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.springview.SpringView;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.MeituanFooter;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.MeituanHeader;

public class SpringViewAnimationHeadActivity extends BaseActivity {

    private SpringView springView;

    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull,R.drawable.mt_pull01,R.drawable.mt_pull02,R.drawable.mt_pull03,R.drawable.mt_pull04,R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01,R.drawable.mt_refreshing02,R.drawable.mt_refreshing03,R.drawable.mt_refreshing04,R.drawable.mt_refreshing05,R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01,R.drawable.mt_loading02};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_springview_animatin_head);
        springView = (SpringView) findViewById(R.id.springview);
        springView.setType(SpringView.Type.FOLLOW); //
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        springView.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
        springView.setFooter(new MeituanFooter(this, loadingAnimSrcs));
    }

    @Override
    public void widgetClick(View v) {

    }
}
