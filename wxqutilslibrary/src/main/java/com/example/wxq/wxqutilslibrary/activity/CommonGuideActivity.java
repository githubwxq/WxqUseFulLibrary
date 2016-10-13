package com.example.wxq.wxqutilslibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.R;

import java.util.ArrayList;

public class CommonGuideActivity extends Activity {
    private ViewPager mViewPager;
    private Button mBtnGo;
    private ViewPaperAdapter vpAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_guide);
        initViews();

    }

    private void initViews() {
            mViewPager = (ViewPager)findViewById(R.id.vp_guide);
            mBtnGo = (Button)findViewById(R.id.btn_go);
            //实例化各个界面的布局对象
            View view1 = View.inflate(this, R.layout.guide_view, null);
            View view2 = View.inflate(this, R.layout.guide_view, null);
            View view3 = View.inflate(this, R.layout.guide_view, null);
            ((ImageView)view1.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_1);
            ((ImageView)view2.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_2);
            ((ImageView)view3.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_3);
            mBtnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CommonGuideActivity.this, "Go Home", Toast.LENGTH_SHORT).show();
                }
            });

            view1.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(0);
                }
            });
            view2.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(1);
                }
            });
            view3.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(2);
                }
            });

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);

        mViewPager.setOffscreenPageLimit(views.size());
      //  mViewPager.setPageMargin(-dip2px(135));
        vpAdapter=new ViewPaperAdapter(views);
        mViewPager.setAdapter(vpAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position + 1 == mViewPager.getAdapter().getCount()) {
                    if (mBtnGo.getVisibility() != View.VISIBLE) {
                        mBtnGo.setVisibility(View.VISIBLE);
                        mBtnGo.startAnimation(AnimationUtils.loadAnimation(CommonGuideActivity.this, android.R.anim.fade_in));
                    }
                } else {
                    if (mBtnGo.getVisibility() != View.GONE) {
                        mBtnGo.setVisibility(View.GONE);
                        mBtnGo.startAnimation(AnimationUtils.loadAnimation(CommonGuideActivity.this, android.R.anim.fade_out));
                    }
                }
            }
        });

    //    mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }


    class ViewPaperAdapter extends PagerAdapter {
        private ArrayList<View> views;

        public ViewPaperAdapter(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            } else
                return 0;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position), 0);
            return views.get(position);
        }

    }
}
