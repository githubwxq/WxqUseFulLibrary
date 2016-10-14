package com.example.wxq.wxqusefullibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.CommonPicViewPageActivity;

import java.util.ArrayList;

public class PhoteViewActivity extends CommonPicViewPageActivity {
    ArrayList<String> mOthersList=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_phote_view);
        //   initViewPageData();
        setTitleText("图片详情");



    }

    @Override
    public ArrayList<String> initImageUrls() {
        mOthersList.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        mOthersList.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        mOthersList.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        mOthersList.add("http://pic44.nipic.com/20140717/2531170_194615292000_2.jpg");

        return mOthersList;
    }
}
