package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.myutils.glide.GlideUtil;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

//glidedemo代码调试
public class GifImageViewActivity extends BaseActivity {
    ImageView displayFromDrawable;
    private Handler handler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_image_view);

        displayFromDrawable = (ImageView) findViewById(R.id.image_view);
        // LoadingImgUtil.displayFromDrawable(R.mipmap.test, displayFromDrawable);
        Glide.with(this).load(R.drawable.anim).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(R.anim.anim).into(displayFromDrawable);
        displayFromDrawable.setOnClickListener(this);


        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        showToast("lalallallaalalalalalalalalal");

                        break;

                }
            }
        };


    }

    int duration;

    @Override
    public void widgetClick(View v) {
        Glide.with(this)
                .load("http://www.vaikan.com/wordpress/wp-content/uploads/2014/math-gifs/UM4iYce.gif")
//                        .asGif()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean
                                                       isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean
                                                           isFromMemoryCache, boolean
                                                           isFirstResource) {
                        //计算动画时长
                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();

                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        //发送延时消息，通知动画结束
                        handler.sendEmptyMessageDelayed(1, duration);
                        return false;
                    }
                })
                        //仅仅加载一次gif动画
                .into(new GlideDrawableImageViewTarget(displayFromDrawable,2));


    }
}
