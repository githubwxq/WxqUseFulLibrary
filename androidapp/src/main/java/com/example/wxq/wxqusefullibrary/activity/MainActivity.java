package com.example.wxq.wxqusefullibrary.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ListView;

import com.example.wxq.wxqusefullibrary.Main2Activity;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.BmobIndexActivity;
import com.example.wxq.wxqusefullibrary.model.Function;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;
import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;
import com.example.wxq.wxqutilslibrary.widget.listview.animations.SwingLeftInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String RXTAG = "MainActivity";
    private final String[] mItems = {"TestCommonAdapter", "listview选中测试", "baseActivity测试"};
    private final Class<?>[] mClasses = {TestCommonAdapterActivity.class, Main2Activity.class,
            TestBaseActivity.class};
    private ArrayList<Function> functions = new ArrayList<>();
    ListView lv_functions;
    BottomView bottomView = null;

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

        //测试权限
        requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 0x0001);
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0002);


    }



    private void initData() {

        //第三方服务集成项目
        Function project=new Function();
        project.setType(0);//0 标题类型
        project.setName("Android练习项目");

        Function bomb=new Function();
        bomb.setType(1);
        bomb.setName("基于Bmob平台项目");
        bomb.setMclass(BmobIndexActivity.class);

        Function rx=new Function();
        rx.setType(1);
        rx.setName("Rx系列使用demo");
        rx.setMclass(RxIndexActivity.class);

        functions.add(project);
        functions.add(bomb);
        functions.add(rx);

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

        //顶部底部下拉刷新 swiperefreshlayout与其类似
        Function refresh=new Function();
        refresh.setType(0);//0 标题类型
        refresh.setName("各种下拉刷新集合");

        Function refresh1=new Function();
        refresh1.setType(1);
        refresh1.setName("springview下拉刷新");
        refresh1.setMclass(SpringViewActivity.class);

        Function refresh2=new Function();
        refresh2.setType(1);
        refresh2.setName("swiperefreshlayout下拉刷新");
        refresh2.setMclass(SpringViewActivity.class);

        functions.add(refresh);
        functions.add(refresh1);
        functions.add(refresh2);
        
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

        //dialog分类
        Function dialog=new Function();
        dialog.setType(0);//0 标题类型
        dialog.setName("各种dialog集合");
        Function dialog1=new Function();
        dialog1.setType(1);
        dialog1.setName("不同位子dailog");
        dialog1.setMclass(DifferentLocationDialogActivity.class);
        Function dialog2=new Function();
        dialog2.setType(1);
        dialog2.setName("不同样式dialog");
        dialog2.setMclass(DifferentStyleDialogActivity.class);

        functions.add(dialog);
        functions.add(dialog1);
        functions.add(dialog2);

        //adapter 分类
        Function adapter=new Function();
        adapter.setType(0);//0 标题类型
        adapter.setName("常用适配器");
        Function commonadapter=new Function();
        commonadapter.setType(1);
        commonadapter.setName("CommonAdapter");
        commonadapter.setMclass(TestCommonAdapterActivity.class);
        Function viewgroupadapter=new Function();
        viewgroupadapter.setType(1);
        viewgroupadapter.setName("ViewGroupAdapter");
        viewgroupadapter.setMclass(ViewGroupActivity.class);

        Function viewgroupLinearadapter=new Function();
        viewgroupLinearadapter.setType(1);
        viewgroupLinearadapter.setName("Linearadapter复用");
        viewgroupLinearadapter.setMclass(LinearLayoutAdapterActivity.class);

        functions.add(adapter);
        functions.add(commonadapter);
        functions.add(viewgroupadapter);
        functions.add(viewgroupLinearadapter);

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

        Function show_pic2=new Function();
        show_pic2.setType(1);
        show_pic2.setName("图片视频一起播放预览");
        show_pic2.setMclass(ShowPicAndVideoActivity.class);

        functions.add(about_pic);
        functions.add(show_pic);
        functions.add(show_pic2);
        Function imageview_gif=new Function();
        imageview_gif.setType(1);
        imageview_gif.setName("播放gif的imageview");
        imageview_gif.setMclass(GifImageViewActivity.class);
        functions.add(imageview_gif);

        //特殊控件测试
        Function button=new Function();
        button.setType(0);//0 标题类型
        button.setName("特殊button");
        Function button1=new Function();
        button1.setType(1);
        button1.setName("圆形边框button");
        button1.setMclass(TestZhuShiActivity.class);

        functions.add(button);
        functions.add(button1);

        //其他测试
        Function test=new Function();
        test.setType(0);//0 标题类型
        test.setName("测试");
        Function testBaseActivity=new Function();
        testBaseActivity.setType(1);
        testBaseActivity.setName("测试baseactivity");
        testBaseActivity.setMclass(BaseActivity.class);

        Function testtablayoutActivity=new Function();
        testtablayoutActivity.setType(1);
        testtablayoutActivity.setName("测试tablayoutactivity");
        testtablayoutActivity.setMclass(Main2Activity.class);

        functions.add(test);
        functions.add(testBaseActivity);
        functions.add(testtablayoutActivity);

       //视频播放控件集合

        Function shiping=new Function();
        shiping.setType(0);//0 标题类型
        shiping.setName("视频播放控件集合");

        Function shiping1=new Function();
        shiping1.setType(1);
        shiping1.setName("测试简单的ScalableVideoActivity");
        shiping1.setMclass(ScalableVideoActivity.class);
        functions.add(shiping);
        functions.add(shiping1);
    }

    private void initView() {
        lv_functions= (ListView) findViewById(R.id.lv_functions);
        MultLayoutAdapter multLayoutAdapter=new MultLayoutAdapter(this,R.layout.function_list_item0,functions);
//        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(multLayoutAdapter);
//        swingBottomInAnimationAdapter.setListView(lv_functions);
//        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(multLayoutAdapter);
//        SwingRightInAnimationAdapter swingRightInAnimationAdapter = new SwingRightInAnimationAdapter(swingBottomInAnimationAdapter);
//        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(multLayoutAdapter);
//        SwingRightInAnimationAdapter swingRightInAnimationAdapter = new SwingRightInAnimationAdapter(swingBottomInAnimationAdapter);
        SwingLeftInAnimationAdapter swingLeftInAnimationAdapter = new SwingLeftInAnimationAdapter(multLayoutAdapter);
        swingLeftInAnimationAdapter.setListView(lv_functions);
   // swingRightInAnimationAdapter.setListView(lv_functions);
        lv_functions.setAdapter(swingLeftInAnimationAdapter);

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
    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 0x0001:

                showToast("电话权限");
                break;
            case 0x0002:

                showToast("sd卡权限");
                break;
        }

    }
    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
        switch (requestCode) {
            case 0x0001:
                showToast("电话权限拒绝");
                break;
            case 0x0002:
                showToast("sd权限拒绝");
                break;
        }
    }
}
