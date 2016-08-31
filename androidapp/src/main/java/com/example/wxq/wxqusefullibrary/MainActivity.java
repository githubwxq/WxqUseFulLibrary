package com.example.wxq.wxqusefullibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.activity.TestCommonAdapterActivity;
import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import specialtools.ScreenUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        rxbus= (TextView) findViewById(R.id.rxbus);
        tvWxq = (TextView) findViewById(R.id.tv_wxq);
        tvWxq.setText(ScreenUtils.getScreenWidth(this) + "");
        tvWxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismissBottomView();
                Intent a = new Intent(MainActivity.this, Main2Activity.class);

                startActivity(a);

                //   popupWindow.disspop();
            }
        });

        // CommonDialogUtils.showDailog(this,R.layout.activity_dialog);


        // popupWindow.easyPopupWindow(this,tvHellow,R.layout.activity_dialog);

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
                break;
            case R.id.tv_wxq4:
                break;
            case R.id.tv_wxq5:
                break;
            case R.id.tv_wxq6:
                Toast.makeText(MainActivity.this, "点击了wxq6", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_wxq7:
                Toast.makeText(MainActivity.this, "点击了wxq7", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
