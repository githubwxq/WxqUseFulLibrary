package com.example.wxq.wxqusefullibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import Tools.ScreenUtils;

public class MainActivity extends AppCompatActivity {


    TextView tvHellow;

    TextView tvWxq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHellow= (TextView) findViewById(R.id.tv_hellow);

        tvWxq= (TextView) findViewById(R.id.tv_wxq);
        tvWxq.setText(ScreenUtils.getScreenWidth(this)+"");







    }


}
