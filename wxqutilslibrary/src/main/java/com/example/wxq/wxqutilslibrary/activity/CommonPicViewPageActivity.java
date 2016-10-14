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
