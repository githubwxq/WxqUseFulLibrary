package com.example.wxq.wxqusefullibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.adapter.PinnedSectionAdapter;
import com.example.wxq.wxqusefullibrary.model.Function;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.listview.PinnedSectionListView;

import java.util.ArrayList;

import rx.functions.Func0;

public class PinnedSectionListViewActivity extends BaseActivity {
    PinnedSectionListView  list;
    private ArrayList<Function> functions=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_section_list_view);
        intData();
        initView();

    }

    private void initView() {
        list= (PinnedSectionListView) findViewById(R.id.list);

        list.setAdapter(new PinnedSectionAdapter(functions,this));



    }

    private void intData() {

        Function a=new Function();
        a.setType(0);//0 标题类型
        a.setName("类别a");
        Function a1=new Function();
        a1.setType(1);
        a1.setName("a1");
        Function a2=new Function();
        a2.setType(1);
        a2.setName("a2");
        Function a3=new Function();
        a3.setType(1);
        a3.setName("a3");
        Function a4=new Function();
        a4.setType(1);
        a4.setName("a4");

        Function b=new Function();
        b.setType(0);//0 标题类型
        b.setName("类别b");
        Function b1=new Function();
        b1.setType(1);
        b1.setName("b1");
        Function b2=new Function();
        b2.setType(1);
        b2.setName("b2");
        Function b3=new Function();
        b3.setType(1);
        b3.setName("b3");
        Function b4=new Function();
        b4.setType(1);
        b4.setName("b4");

        Function c=new Function();
        c.setType(0);//0 标题类型
        c.setName("类别c");
        Function c1=new Function();
        c1.setType(1);
        c1.setName("c1");
        Function c2=new Function();
        c2.setType(1);
        c2.setName("c2");
        Function c3=new Function();
        c3.setType(1);
        c3.setName("c3");

        functions.add(a);
        functions.add(a1);
        functions.add(a2);
        functions.add(a3);
        functions.add(a4);

        functions.add(b);
        functions.add(b1);
        functions.add(b2);
        functions.add(b3);
        functions.add(b4);

        functions.add(c);
        functions.add(c1);
        functions.add(c2);
        functions.add(c3);

        functions.add(c);
        functions.add(c1);
        functions.add(c2);
        functions.add(c3);

    }

    @Override
    public void widgetClick(View v) {

    }
















}
