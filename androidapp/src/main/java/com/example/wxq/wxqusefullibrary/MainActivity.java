package com.example.wxq.wxqusefullibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.activity.BannerActivity;
import com.example.wxq.wxqusefullibrary.activity.DataBindActivity;
import com.example.wxq.wxqusefullibrary.activity.GuideActivity;
import com.example.wxq.wxqusefullibrary.activity.LinearActivity;
import com.example.wxq.wxqusefullibrary.activity.PhoteViewActivity;
import com.example.wxq.wxqusefullibrary.activity.PinnedSectionListViewActivity;
import com.example.wxq.wxqusefullibrary.activity.SlidingTabActivity;
import com.example.wxq.wxqusefullibrary.activity.TestBaseActivity;
import com.example.wxq.wxqusefullibrary.activity.TestCommonAdapterActivity;
import com.example.wxq.wxqusefullibrary.model.Function;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.model.MsgEvent;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;
import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;
import com.example.wxq.wxqutilslibrary.widget.dialog.CommonAlertDialog;
import com.example.wxq.wxqutilslibrary.widget.dialog.CommonBottomPopDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
     //   ButterKnife.bind(this);
//        tvHellow = (TextView) findViewById(R.id.tv_hellow);
//        tv_wxq11 = (TextView) findViewById(R.id.tv_wxq11);
//        rxbus = (TextView) findViewById(R.id.rxbus);
//        tvWxq = (TextView) findViewById(R.id.tv_wxq);
//        tv_wxq8 = (TextView) findViewById(R.id.tv_wxq8);
//        tv_wxq8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent a = new Intent(MainActivity.this, PhoteViewActivity.class);
//                startActivity(a);
//                //   popupWindow.disspop();
//            }
//        });
//
//        tvWxq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomView.dismissBottomView();
//                Intent a = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(a);
//            }
//        });
//        tv_wxq11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomView.dismissBottomView();
//                Intent a = new Intent(MainActivity.this, BannerActivity.class);
//                startActivity(a);
//            }
//        });
//
//        tv_wxq14 = (TextView) findViewById(R.id.tv_wxq14);
//        tv_wxq14.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomView.dismissBottomView();
//                Intent a = new Intent(MainActivity.this,DataBindActivity.class);
//                startActivity(a);
//
//            }
//        });


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
        functions.add(tab);
        functions.add(tab_slib);

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
        testBaseActivity.setName("phoneview图片预览");
        testBaseActivity.setMclass(TestBaseActivity.class);
        functions.add(test);
        functions.add(testBaseActivity);



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
//    private final  class  MultLayoutAdapter extends CommonAdapter<Function> {
//
//
//        public MultLayoutAdapter(Context context, int layoutResId, List<Function> data) {
//            super(context, layoutResId, data);
//        }
//
//        @Override
//        public void onUpdate(BaseAdapterHelper helper, Function item, int position) {
//            switch (item.getType()) {
//                case 0:
//
//                    break;
//                case 1:
//                    helper.setText(R.id.tv_price, item.getPrice());
//                    break;
//
//
//            }
//
//
//        }
//
//        @Override
//        public int getLayoutResId(Book item, int position) {
//            int layoutResId = -1;
//            switch (item.getTag()) {
//                case 0: //布局样式一
//                    layoutResId = R.layout.book_list_item;
//                    break;
//                case 1: //布局样式一
//                    layoutResId = R.layout.book_list_item2;
//                    break;
//                case 2: //布局样式一
//                    layoutResId = R.layout.book_list_item3;
//                    break;
////            case 3: //布局样式一
////                layoutResId = R.layout.item_none_picture;
////                break;
//
//
//            }
//
//
//            return layoutResId;
//        }
//    }
//    @Subscribe
//    public void changerxbus(String food) {
//
//        rxbus.setText(food+"来自第三个界面的回传");
//      //  Toast.makeText(this,food+"我在当前类",Toast.LENGTH_SHORT).show();
//    }
//
//    @Subscribe(
//            thread = EventThread.MAIN_THREAD,
//            tags = {
//                    @Tag(RXTAG)
//            }
//    )
//    public void getInfoFromFragment1(String informatiion) {
//
//        tvWxq3.setText(informatiion+"<<<<<<");
//        //  Toast.makeText(this,food+"我在当前类",Toast.LENGTH_SHORT).show();
//    }


//    @OnClick({R.id.tv_hellow, R.id.tv_wxq3, R.id.tv_wxq4, R.id.tv_wxq5, R.id.tv_wxq6, R.id.tv_wxq7})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_hellow:
//                startActivity(new Intent(this, TestCommonAdapterActivity.class));
//                break;
//            case R.id.tv_wxq3:
//                startActivity(new Intent(this, TestBaseActivity.class));
//                break;
//            case R.id.tv_wxq4:
//                final CommonBottomPopDialog commonBottomPopDialog = new CommonBottomPopDialog(this);
//                commonBottomPopDialog.show();
//                commonBottomPopDialog.setListenerInterface(new CommonBottomPopDialog.ClickListenerInterface() {
//                    @Override
//                    public void doDelete() {
//                        showToast("删除");
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        showToast("取消");
//                        commonBottomPopDialog.dismiss();
//                    }
//                });
//
//            case R.id.tv_wxq5:
//
//                CommonAlertDialog.getInstance().createAlertDialog(this, "注意有人来了", "取消", "确定", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showToast("no");
//                                CommonAlertDialog.getInstance().dismiss();
//                            }
//                        }, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showToast("ok");
//                                CommonAlertDialog.getInstance().dismiss();
//                            }
//                        }
//
//                ).show();
//
//                break;
//            case R.id.tv_wxq6:
//                EventBus.getDefault().postSticky(new MsgEvent("From Main With Sticky"));
//                EventBus.getDefault().post("i am wxq lala main");
//                Toast.makeText(MainActivity.this, "点击了wxq6", Toast.LENGTH_LONG).show();
//            //   startActivity(new Intent(this, LinearActivity.class));
//
//                break;
//            case R.id.tv_wxq7:
//                Toast.makeText(MainActivity.this, "点击了wxq7", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, GuideActivity.class));
//                break;
//        }
//    }
//
//    @Override
//    public void widgetClick(View v) {
//switch (v.getId()){
//    case R.id.tv_wxq12:
//        startActivity(new Intent(this, LinearActivity.class));
//        break;
//}
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        RxBus.get().unregister(this);
//    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(MsgEvent event) {
//        rxbus.setText(event.getJsonData());
//        //    EventBus.getDefault().removeAllStickyEvents();
//    }
//
//    private void initView() {
//        tv_wxq12 = (TextView) findViewById(R.id.tv_wxq12);
//        tv_wxq12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LinearActivity.class));
//            }
//        });
//    }

}
