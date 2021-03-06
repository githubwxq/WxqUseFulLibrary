package com.example.wxq.wxqusefullibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.wxq.wxqutilslibrary.imageloadutils.glide.GlideUtil;

import banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
//        Glide.with(MyApplication.mContext)
//                .load(path)
//                .crossFade()
//                .into(imageView);

 //   GlideUtil.getInstance().loadCornerImage(context,imageView,(String)path);
    GlideUtil.getInstance().loadImage(context,imageView,(String)path,true);
    }
}
