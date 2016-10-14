package com.example.wxq.wxqutilslibrary.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.wxq.wxqutilslibrary.R;
import com.example.wxq.wxqutilslibrary.myutils.imageloader.LoadingImgUtil;
import com.example.wxq.wxqutilslibrary.widget.photoview.PhotoView;

import java.util.ArrayList;

public abstract class CommonPicViewPageActivity extends BaseActivity {
    private ViewPager mPager;
    private ArrayList<String> urls = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_pic_view_page);
        mPager = (ViewPager) findViewById(R.id.pager);
        //   mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        //  urls= initImageUrls();
        urls=  initImageUrls();
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(CommonPicViewPageActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                LoadingImgUtil.loading(urls.get(position).toString(), (ImageView) view, null, true);


//                p.setLayoutParams(new AbsListView.LayoutParams((int) (getResources().getDisplayMetrics().density * 100), (int) (getResources().getDisplayMetrics().density * 100)));
//                p.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                p.setImageResource(imgs[position]);
//                // 把PhotoView当普通的控件把触摸功能关掉
//                p.disenable();

                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });
    }

    public abstract ArrayList<String> initImageUrls();

    public void initViewPageData() {
        urls = initImageUrls();
    }

    @Override
    public void widgetClick(View v) {

    }
}

////设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
//mImg1.disenable();
//        mImg1.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        mImg1.setVisibility(View.GONE);
//        mImg2.setVisibility(View.VISIBLE);
//
//        //获取img1的信息
//        mRectF = mImg1.getInfo();
//        //让img2从img1的位置变换到他本身的位置
//        mImg2.animaFrom(mRectF);
//        }
//        });
//
//        // 需要启动缩放需要手动开启
//        mImg2.enable();
//        mImg2.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        // 让img2从自身位置变换到原来img1图片的位置大小
//        mImg2.animaTo(mRectF, new Runnable() {
//@Override
//public void run() {
//        mImg2.setVisibility(View.GONE);
//        mImg1.setVisibility(View.VISIBLE);
//        }
//        });
//        }
//        });