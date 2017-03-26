package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import lvgv.CommonAdapter;
import lvgv.ViewHolder;
import refrishandloadmore.OnLoadMoreListener;
import refrishandloadmore.OnRefreshListener;
import refrishandloadmore.SwipeToLoadLayout;

public class RenderingActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    View view;
    TextView tv_tab_title;
    private SwipeToLoadLayout swipeToLoadLayout;
    ListView swipe_target;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_twitter_listview);
   setContentView(R.layout.activity_nav_jd);


        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        swipe_target = (ListView) findViewById(R.id.swipe_target);
        List<String> datas = new ArrayList<>();
        datas.add("exw");
        datas.add("12");
        datas.add("34");
        datas.add("56");
        datas.add("exw");
        datas.add("12");
        datas.add("34");
        datas.add("56");
        datas.add("exw");
        datas.add("12");
        datas.add("34");
        datas.add("56");
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipe_target.setAdapter(new CommonAdapter<String>(this, datas, R.layout.book_list_item) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_bookname, s);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //     ViewServer.get(this).removeWindow(this);
    }

    public void onResume() {
        super.onResume();
        //        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void onLoadMore() {
        // 加载更多
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
                swipeToLoadLayout.setLoadMoreEnabled(false);
            }
        }, 3000);
    }

    @Override
    public void onRefresh() {
        // 加载刷新
        new MyHandler(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);

    }
}
//没有用的父布局时指没有背景绘制或者没有大小限制的父布局，这样的布局不会对UI效果产生任何影响。我们可以把没有用的父布局，通过<merge/>标签合并来减少UI的层次；
//        使用线性布局LinearLayout排版导致UI层次变深，如果有这类问题，我们就使用相对布局RelativeLayout代替LinearLayout,减少UI的层次；
//        不常用的UI被设置成GONE,比如异常的错误页面，如果有这类问题，我们需要用<ViewStub/>标签，代替GONE提高UI性能。
//        去掉window的默认背景
//        当我们使用了Android自带的一些主题时，window会被默认添加一个纯色的背景，这个背景是被DecorView持有的。当我们的自定义布局时又添加了一张背景图或者设置背景色，那么DecorView的background此时对我们来说是无用的，但是它会产生一次Overdraw，带来绘制性能损耗。
//        去掉window的背景可以在onCreate()中setContentView()之后调用getWindow().setBackgroundDrawable(null);或者在theme中添加android:windowbackground="null"；