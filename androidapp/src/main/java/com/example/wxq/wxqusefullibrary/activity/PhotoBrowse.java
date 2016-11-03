package com.example.wxq.wxqusefullibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.myutils.imageloader.LoadingImgUtil;
import com.example.wxq.wxqutilslibrary.widget.photoview.Info;
import com.example.wxq.wxqutilslibrary.widget.photoview.PhotoView;

import java.util.ArrayList;

/**
 * Created by liuheng on 2015/8/19.
 */
public class PhotoBrowse extends BaseActivity {

//    int[] imgs = new int[]{R.mipmap.aaa, R.mipmap.bbb, R.mipmap.ccc, R.mipmap.ddd, R.mipmap.ic_launcher, R.mipmap.image003,R.mipmap.aaa, R.mipmap.bbb};
//

    ArrayList<String> mOthersList = new ArrayList<String>();
    GridView gv;
    View mParent;
    View mBg;
    PhotoView mPhotoView;
    Info mInfo;

    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);

    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_browse);
        mOthersList.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        mOthersList.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        mOthersList.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        mOthersList.add("http://pic44.nipic.com/20140717/2531170_194615292000_2.jpg");
        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mParent = findViewById(R.id.parent);
        mBg = findViewById(R.id.bg);
        mPhotoView = (PhotoView) findViewById(R.id.img);
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mOthersList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                    return (view == object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(PhotoBrowse.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                LoadingImgUtil.loading(mOthersList.get(position).toString(), (ImageView) view, null, true);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  finish();

                        ((PhotoView)v).animaTo(mInfo, new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setVisibility(View.GONE);

                                mBg.setVisibility(View.GONE);
                            }
                        });
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            showToast("position" + position);

             //   ((PhotoView)viewPager.getChildAt(position)).animaFrom(mInfo);

            }
        });





        gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mOthersList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PhotoView p = new PhotoView(PhotoBrowse.this);
                p.setLayoutParams(new AbsListView.LayoutParams((int) (getResources().getDisplayMetrics().density * 100), (int) (getResources().getDisplayMetrics().density * 100)));
                p.setScaleType(ImageView.ScaleType.CENTER_CROP);

                LoadingImgUtil.loading(mOthersList.get(position).toString(), (ImageView) p, null, true);
                //   p.setImageResource(mOthersList[position]);
                // 把PhotoView当普通的控件把触摸功能关掉
                p.disenable();
                return p;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoView p = (PhotoView) view;
                mInfo = p.getInfo();
//                LoadingImgUtil.loading(mOthersList.get(position).toString(), (ImageView) mPhotoView, null, true);
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        //确保图片加载完后执行动画最好写回调函数通知结束后干什么
//                        mBg.startAnimation(in);
//                        mBg.setVisibility(View.VISIBLE);
//                        mParent.setVisibility(View.VISIBLE);
//                        //   mPhotoView.setMaxAnimFromWaiteTime(5000);
//                        mPhotoView.animaFrom(mInfo);
//                    }
//                }, 500);

                mBg.startAnimation(in);
                mBg.setVisibility(View.VISIBLE);
                mParent.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
//                mPhotoView.setMaxAnimFromWaiteTime(500);
//                mPhotoView.animaFrom(mInfo);
                //viewpage 选择某一项
                viewPager.setCurrentItem(position);
             //   ((PhotoView)viewPager.getChildAt(position)).animaFrom(mInfo);
             //   ((PhotoView)(viewPager.getChildAt(1))).animaFrom(mInfo);
//                if(((PhotoView)viewPager.getChildAt(position))==null){
//                    showToast("null");
//
//                }

            }
        });


        mPhotoView.enable();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBg.startAnimation(out);
                mPhotoView.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        mParent.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getVisibility() == View.VISIBLE) {
            mBg.startAnimation(out);

//            mPhotoView.animaTo(mInfo, new Runnable() {
//                @Override
//                public void run() {
//                    mParent.setVisibility(View.GONE);
//                }
//            });


        } else {
            super.onBackPressed();
        }
    }
}
