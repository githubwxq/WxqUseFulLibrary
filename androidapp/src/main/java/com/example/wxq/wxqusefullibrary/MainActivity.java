package com.example.wxq.wxqusefullibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.activity.BannerActivity;
import com.example.wxq.wxqusefullibrary.activity.GuideActivity;
import com.example.wxq.wxqusefullibrary.activity.PhoteViewActivity;
import com.example.wxq.wxqusefullibrary.activity.TestBaseActivity;
import com.example.wxq.wxqusefullibrary.activity.TestCommonAdapterActivity;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.model.MsgEvent;
import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;
import com.example.wxq.wxqutilslibrary.widget.dialog.CommonAlertDialog;
import com.example.wxq.wxqutilslibrary.widget.dialog.CommonBottomPopDialog;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    public static final String RXTAG ="MainActivity";
    BottomView bottomView = null;
    //CommonPopupWindow popupWindow;


    TextView tvWxq;

    @BindView(R.id.tv_hellow)
    TextView tvHellow;
    @BindView(R.id.tv_wxq3)
    TextView tvWxq3;
    @BindView(R.id.tv_wxq4)
    TextView tvWxq4;
    @BindView(R.id.tv_wxq5)
    TextView tvWxq5;
    @BindView(R.id.tv_wxq6)
    TextView tvWxq6;
    @BindView(R.id.tv_wxq7)
    TextView tvWxq7;
    TextView rxbus;
    TextView tv_wxq11;
    TextView tv_wxq8;
    TextView tv_wxq14;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  popupWindow= new CommonPopupWindow();
        RxBus.get().register(this);
        bottomView = new BottomView(this, R.style.BottomViewTheme_Defalut, R.layout.activity_dialog);

        bottomView.setTop(false);
        bottomView.showBottomView(true);
        bottomView.dismissBottomView();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvHellow = (TextView) findViewById(R.id.tv_hellow);
        tv_wxq11 = (TextView) findViewById(R.id.tv_wxq11);
        rxbus= (TextView) findViewById(R.id.rxbus);
        tvWxq = (TextView) findViewById(R.id.tv_wxq);
       tv_wxq8 = (TextView) findViewById(R.id.tv_wxq8);
        tv_wxq8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, PhoteViewActivity.class);
                startActivity(a);
                //   popupWindow.disspop();
            }
        });

        tvWxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismissBottomView();
                int b=10;
                int c=20;
                int d=b+c;
                Intent a = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(a);
                //   popupWindow.disspop();
            }
        });
        tv_wxq11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismissBottomView();
                Intent a = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(a);
            }
        });

        tv_wxq14= (TextView) findViewById(R.id.tv_wxq14);
        tv_wxq14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismissBottomView();
                Intent a = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(a);

            }
        });










    }

    @Subscribe
    public void changerxbus(String food) {

        rxbus.setText(food+"来自第三个界面的回传");
      //  Toast.makeText(this,food+"我在当前类",Toast.LENGTH_SHORT).show();
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RXTAG)
            }
    )
    public void getInfoFromFragment1(String informatiion) {

        tvWxq3.setText(informatiion+"<<<<<<");
        //  Toast.makeText(this,food+"我在当前类",Toast.LENGTH_SHORT).show();
    }



    @OnClick({R.id.tv_hellow, R.id.tv_wxq3, R.id.tv_wxq4, R.id.tv_wxq5, R.id.tv_wxq6, R.id.tv_wxq7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hellow:
                startActivity(new Intent(this, TestCommonAdapterActivity.class));
                break;
            case R.id.tv_wxq3:
                startActivity(new Intent(this, TestBaseActivity.class));
                break;
            case R.id.tv_wxq4:
                final CommonBottomPopDialog commonBottomPopDialog=new CommonBottomPopDialog(this);
                commonBottomPopDialog.show();
                commonBottomPopDialog.setListenerInterface(new CommonBottomPopDialog.ClickListenerInterface() {
                    @Override
                    public void doDelete() {
                        showToast("删除");
                    }

                    @Override
                    public void doCancel() {
                        showToast("取消");
                        commonBottomPopDialog.dismiss();
                    }
                });

            case R.id.tv_wxq5:

                CommonAlertDialog.getInstance().createAlertDialog(this,"注意有人来了","取消","确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                showToast("no");
                                CommonAlertDialog.getInstance().dismiss();
                            }
                        },new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                showToast("ok");
                                CommonAlertDialog.getInstance().dismiss();
                            }
                        }

                ).show();

                break;
            case R.id.tv_wxq6:
                EventBus.getDefault().postSticky(new MsgEvent("From Main With Sticky"));
                EventBus.getDefault().post("i am wxq lala main");
                Toast.makeText(MainActivity.this, "点击了wxq6", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_wxq7:
                Toast.makeText(MainActivity.this, "点击了wxq7", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, GuideActivity.class));
                break;
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }


    @org.greenrobot.eventbus.Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MsgEvent event){
        rxbus.setText(event.getJsonData());
    //    EventBus.getDefault().removeAllStickyEvents();
    }
}
