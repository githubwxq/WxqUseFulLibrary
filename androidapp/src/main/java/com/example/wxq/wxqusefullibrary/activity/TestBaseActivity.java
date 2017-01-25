package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.model.Book;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.model.MsgEvent;
import com.example.wxq.wxqutilslibrary.myutils.imageloader.LoadingImgUtil;
import com.example.wxq.wxqutilslibrary.myutils.xutils.NetConnectTools;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import specialtools.ACache;

public class TestBaseActivity extends BaseActivity {

    private ACache aCache;
    private TextView tv_test_title;
    private RelativeLayout rl_root;
    private TextView tv_test_name;
    private ImageView iv_1;
    private ImageView iv_2;
    private String parms;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base);
        initView();
        setTitleText("测试标题栏集成");
        setOKText("右边");
        tv_test_title.setOnClickListener(this);
        tv_test_name.setOnClickListener(this);
        aCache = aCache.get(this);
        String url="http://op.juhe.cn/onebox/weather/query";

        NetConnectTools.getInstance().getData("https://www.juhe.cn/docs/api/id/196", null, new NetConnectTools.CallBackListen() {
            @Override
            public void onSuccess(String result) {
                showToast(result);
                setTitleText(result+"ok");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                setTitleText("错误");
            }

            @Override
            public void onFinished() {
                showToast("结束");
            }
        },this);

//        NetConnectTools.getInstance().postData("http://op.juhe.cn/onebox/weather/query", null, new NetConnectTools.CallBackListen() {
//            @Override
//            public void onSuccess(String result) {
//                showToast(result);
//                setTitleText(result+"ok");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                setTitleText("错误");
//            }
//
//            @Override
//            public void onFinished() {
//                showToast("结束");
//            }
//        });









     //   EventBus.getDefault().post("i am wxq lala");

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 100)
    public void onEvent(MsgEvent event){
        parms= event.getJsonData();
        showToast("获取参数" + parms);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent2(MsgEvent event){
        showToast("获取没填加粘性参数"+parms);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_title:
                //  ActivityManager.getInstance().killActivity(this);
                aCache.put("wxq", "我是王晓清");
                Book book = new Book("wxq", "123");
                Gson gson = new Gson();
                String jsonObject = gson.toJson(book);
                aCache.put("book", jsonObject);
                break;
            case R.id.tv_test_name:
                //  ActivityManager.getInstance().killActivity(this);
                String wxq = aCache.getAsString("wxq");
                showToast(wxq);
                showToast(aCache.getAsJSONObject("book").toString());
                EventBus.getDefault().post(new MsgEvent("我来自于testbaseactivity"));
                break;

        }
    }

    private void initView() {
        tv_test_title = (TextView) findViewById(R.id.tv_test_title);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        tv_test_name = (TextView) findViewById(R.id.tv_test_name);
        tv_test_name.setOnClickListener(this);
        iv_1 = (ImageView) findViewById(R.id.iv_1);
        LoadingImgUtil.loading("http://c.hiphotos.baidu.com/image/h%3D200/sign=e1003505bf4543a9ea1bfdcc2e168a7b/54fbb2fb43166d221d53fe654e2309f79052d21f.jpg",iv_1,null,true);
        iv_1.setOnClickListener(this);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        LoadingImgUtil.loading("http://d.hiphotos.baidu.com/zhidao/pic/item/5882b2b7d0a20cf45235c0db75094b36acaf9908.jpg",iv_2,null,true);
        iv_2.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBase(String event) {
        l();
        showToast(event);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event){
        showToast("123");
    }
}
