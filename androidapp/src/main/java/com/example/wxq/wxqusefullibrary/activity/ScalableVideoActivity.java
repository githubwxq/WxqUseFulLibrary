package com.example.wxq.wxqusefullibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.adapter.SampleAdapter;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

public class ScalableVideoActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SampleAdapter mSampleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSampleAdapter = new SampleAdapter(this);
        mRecyclerView.setAdapter(mSampleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSampleAdapter.setVideoResId(R.raw.landscape_sample);
    }

    @Override
    public void widgetClick(View v) {

    }
}
