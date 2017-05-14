package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonRecyclerAdapter;

import java.util.ArrayList;

import superrecycleview.RightSpacesItemDecoration;

public class UploadFilesActivity extends BaseActivity {
  RecyclerView rv_list;
    ArrayList<String> lists;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_files);
        rv_list= (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        lists=new ArrayList<>();
        lists.add("0");
        lists.add("12");
        lists.add("20");
        lists.add("30");

        rv_list.addItemDecoration(new RightSpacesItemDecoration(10));

        rv_list.setAdapter(new CommonRecyclerAdapter<String>(UploadFilesActivity.this,R.layout.main_list_list_item,lists) {

            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {




            }
        });


    }

    @Override
    public void widgetClick(View v) {

    }
}
