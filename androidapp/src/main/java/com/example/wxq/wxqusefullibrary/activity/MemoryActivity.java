package com.example.wxq.wxqusefullibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.modelmanageer.UserManger;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

public class MemoryActivity extends BaseActivity {

    private android.widget.TextView tvsingle;
    private android.widget.LinearLayout activitymemory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        this.activitymemory = (LinearLayout) findViewById(R.id.activity_memory);
        this.tvsingle = (TextView) findViewById(R.id.tv_single);
        UserManger userManger = UserManger.getInstance(this);
        tvsingle.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_single:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
