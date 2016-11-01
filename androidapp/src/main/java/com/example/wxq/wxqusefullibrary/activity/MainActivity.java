package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.wxq.wxqusefullibrary.Main2Activity;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.model.Function;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;
import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String RXTAG = "MainActivity";

    private final String[] mItems = {"TestCommonAdapter", "listview选中测试", "baseActivity测试"};
    private final Class<?>[] mClasses = {TestCommonAdapterActivity.class, Main2Activity.class,
            TestBaseActivity.class};
    private ArrayList<Function> functions=new ArrayList<>();
    ListView lv_functions;

    BottomView bottomView = null;
    //CommonPopupWindow popupWindow;
//    TextView tvWxq;
//
//    @BindView(R.id.tv_hellow)
//    TextView tvHellow;
//    @BindView(R.id.tv_wxq3)
//    TextView tvWxq3;
//    @BindView(R.id.tv_wxq4)
//    TextView tvWxq4;
//    @BindView(R.id.tv_wxq5)
//    TextView tvWxq5;
//    @BindView(R.id.tv_wxq6)
//    TextView tvWxq6;
//    @BindView(R.id.tv_wxq7)
//    TextView tvWxq7;
//    TextView rxbus;
//    TextView tv_wxq11;
//    TextView tv_wxq8;
//    TextView tv_wxq14;
//    private TextView tv_wxq12;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomView = new BottomView(this, R.style.BottomViewTheme_Defalut, R.layout.activity_dialog);
        bottomView.setTop(false);
        bottomView.showBottomView(true);
        bottomView.dismissBottomView();

        setContentView(R.layout.activity_main);
        initData();
        initView();


    }

    private void initData() {
        //listview 分类
        Function function_listview=new Function();
        function_listview.setType(0);//0 标题类型
        function_listview.setName("各种listview集合");

        Function function_superlistview=new Function();
        function_superlistview.setType(1);
        function_superlistview.setName("superrecycle");
        function_superlistview.setMclass(LinearActivity.class);

        Function pinnedSectionlListview=new Function();
        pinnedSectionlListview.setType(1);
        pinnedSectionlListview.setName("PinnedSectionListView");
        pinnedSectionlListview.setMclass(PinnedSectionListViewActivity.class);



        functions.add(function_listview);
        functions.add(function_superlistview);
        functions.add(pinnedSectionlListview);
        //轮播图 分类
        Function function_banner=new Function();
        function_banner.setType(0);//0 标题类型
        function_banner.setName("各种轮播图集合");
        Function banner1=new Function();
        banner1.setType(1);
        banner1.setName("无动画轮播控件");
        banner1.setMclass(BannerActivity.class);
        functions.add(function_banner);
        functions.add(banner1);


        //adapter 分类
        Function adapter=new Function();
        adapter.setType(0);//0 标题类型
        adapter.setName("常用适配器");
        Function commonadapter=new Function();
        commonadapter.setType(1);
        commonadapter.setName("CommonAdapter");
        commonadapter.setMclass(TestCommonAdapterActivity.class);
        functions.add(adapter);
        functions.add(commonadapter);


        //tab 分类
        Function tab=new Function();
        tab.setType(0);//0 标题类型
        tab.setName("常见导航栏");
        Function tab_slib=new Function();
        tab_slib.setType(1);
        tab_slib.setName("可滑动Tab");
        tab_slib.setMclass(SlidingTabActivity.class);

        Function commonTab=new Function();
        commonTab.setType(1);
        commonTab.setName("CommonTab");
        commonTab.setMclass(CommonTabActivity.class);

        Function tab_seg=new Function();
        tab_seg.setType(1);
        tab_seg.setName("SegmentTab");
        tab_seg.setMclass(SegmentTabActivity.class);


        functions.add(tab);
        functions.add(tab_slib);
        functions.add(commonTab);
        functions.add(tab_seg);



        //图片相关 分类
        Function about_pic=new Function();
        about_pic.setType(0);//0 标题类型
        about_pic.setName("图片相关");
        Function show_pic=new Function();
        show_pic.setType(1);
        show_pic.setName("phoneview图片预览");
        show_pic.setMclass(PhoteViewActivity.class);
        functions.add(about_pic);
        functions.add(show_pic);



        //其他测试
        Function test=new Function();
        test.setType(0);//0 标题类型
        test.setName("测试");
        Function testBaseActivity=new Function();
        testBaseActivity.setType(1);
        testBaseActivity.setName("测试baseactivity");
        testBaseActivity.setMclass(TestBaseActivity.class);

        Function testtablayoutActivity=new Function();
        testtablayoutActivity.setType(1);
        testtablayoutActivity.setName("测试tablayoutactivity");
        testtablayoutActivity.setMclass(Main2Activity.class);

        functions.add(test);
        functions.add(testBaseActivity);
        functions.add(testtablayoutActivity);



    }

    private void initView() {
        lv_functions= (ListView) findViewById(R.id.lv_functions);
        lv_functions.setAdapter(new MultLayoutAdapter(this,R.layout.function_list_item0,functions));









    }

    @Override
    public void widgetClick(View v) {

    }

    class MultLayoutAdapter extends CommonAdapter<Function> {

        public MultLayoutAdapter(Context context, int layoutResId, List<Function> data) {
            super(context, layoutResId, data);
        }

        @Override
        public void onUpdate(BaseAdapterHelper helper, Function item, final int position) {
            switch (item.getType()) {
                case 0:
                    helper.setText(R.id.tv_title, item.getName());
                    break;
                case 1:
                    helper.setText(R.id.tv_content, item.getName()).setOnClickListener(R.id.tv_content, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivity.this,functions.get(position).getMclass());
                            startActivity(intent);
                        }
                    });


                    break;


            }
        }


        @Override
        public int getLayoutResId(Function item, int position) {
            int layoutResId = -1;
            switch (item.getType()) {
                case 0: //布局样式一
                    layoutResId = R.layout.function_list_item0;
                    break;
                case 1: //布局样式一
                    layoutResId = R.layout.function_list_item1;
                    break;
            }
            return layoutResId;
        }

    }
}
