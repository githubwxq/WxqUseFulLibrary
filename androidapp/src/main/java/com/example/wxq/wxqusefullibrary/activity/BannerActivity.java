package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.imageloadutils.imageloader.LoadingImgUtil;
import com.example.wxq.wxqutilslibrary.widget.banner.XBanner;

import java.util.ArrayList;

public class BannerActivity extends BaseActivity implements XBanner.XBannerAdapter {
    XBanner banner_1;
    ArrayList<String> mOthersList=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        banner_1= (XBanner) findViewById(R.id.banner_1);
        banner_1.setPoinstPosition(2);
        mOthersList.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        mOthersList.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        mOthersList.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        mOthersList.add("http://pic44.nipic.com/20140717/2531170_194615292000_2.jpg");


        banner_1.setmAdapter(this);
        banner_1.setData(mOthersList);

        banner_1.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(BannerActivity.this, "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {

     LoadingImgUtil.loading(mOthersList.get(position).toString(), (ImageView) view, null, true);



    }
}
