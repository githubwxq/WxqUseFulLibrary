package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.adapter.Data;
import com.example.wxq.wxqusefullibrary.adapter.MessageAdapter;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import java.util.List;

import imagewatch.ImageWatcher;
import imagewatch.MessagePicturesLayout;
import imagewatch.PicSaveDialog;
import imagewatch.SpaceItemDecoration;
import imagewatch.Utils;

public class weixinActivity extends
        BaseActivity implements MessagePicturesLayout.Callback, ImageWatcher.OnPictureLongPressListener  {

    private ImageWatcher vImageWatcher;

    private RecyclerView vRecycler;
    private MessageAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isTranslucentStatus = false;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//            isTranslucentStatus = true;
//        }
        setContentView(R.layout.activity_weixin);


        vRecycler = (RecyclerView) findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.addItemDecoration(new SpaceItemDecoration(this).setSpace(14).setSpaceColor(0xFFECECEC));
        vRecycler.setAdapter(adapter = new MessageAdapter(this).setPictureClickCallback(this));
        adapter.set(Data.get());

//        // 一般来讲， ImageWatcher 需要占据全屏的位置
        vImageWatcher = (ImageWatcher) findViewById(R.id.v_image_watcher);
        // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
        vImageWatcher.setTranslucentStatus(225); //、、toolbar和状态栏的高度和
        // 配置error图标
        vImageWatcher.setErrorImageRes(R.drawable.default_image);
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
      vImageWatcher.setOnPictureLongPressListener(this);

        Utils.fitsSystemWindows(isTranslucentStatus, findViewById(R.id.v_fit));





    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {
        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
        PicSaveDialog.getInstance().createSavePicDialog(this,vImageWatcher,url,null);


    }

    @Override
    public void onThumbPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList) {
        vImageWatcher.show(i, imageGroupList, urlList);
    }

    @Override
    public void onBackPressed() {
        if (!vImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }
}
