package com.example.wxq.wxqusefullibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.utils.App;
import com.example.wxq.wxqusefullibrary.utils.GlideImageLoader;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import banner.Banner;
import banner.BannerConfig;

public class CustomBannerActivity extends
        BaseActivity {
    Banner banner1,banner2,banner3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_banner);
        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        List<String> titles=new ArrayList(Arrays.asList(tips));
        List<?> images  = new ArrayList(list);
        banner1.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)  //有黑色条目
                .setImageLoader(new GlideImageLoader())
                .start();

    }

    @Override
    public void widgetClick(View v) {

    }
}
