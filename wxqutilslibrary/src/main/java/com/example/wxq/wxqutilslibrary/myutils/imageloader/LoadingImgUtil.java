package com.example.wxq.wxqutilslibrary.myutils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.wxq.wxqutilslibrary.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/9/16.
 */
public class LoadingImgUtil {

    /*显示的image配置 至于缓存路径不是options 设置的
     *通用imageloaderoptopm
     */
    public static DisplayImageOptions DEFAULT_PIC_OPPIONS= new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.default_image)
            .showImageForEmptyUri(R.drawable.default_image)
            .showImageOnFail(R.drawable.default_image)
            .cacheInMemory(true)
            .cacheOnDisk(true).considerExifParams(true )
            .bitmapConfig(Bitmap.Config.RGB_565).build();



    /*显示的image配置 至于缓存路径不是options 设置的
    *头像imageloaderoptoptions
    */
    public static DisplayImageOptions DEFAULR_PIC_HEARD=new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.default_head)
            .showImageForEmptyUri(R.drawable.default_head)
            .showImageOnFail(R.drawable.default_head).cacheInMemory(true)
            .cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();


    public static DisplayImageOptions BIG_PIC_OPTIONS = new DisplayImageOptions.Builder()
            .showImageOnLoading(0).showImageForEmptyUri(0).showImageOnFail(0)
            .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    /**
     *
     * @param url
     * @param imgview
     * @param progressBar
     * @param flag  加载头像 加载其他
     */
    public static void loading(String url,ImageView imgview,final ProgressBar progressBar,boolean flag){
        ImageLoader imageLoad = ImageLoader.getInstance();
        SimpleImageLoadingListener loadingListener=null;
        ImageLoadingProgressListener loadingProgressListener=null;
        if (progressBar != null) {
            loadingListener = new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setProgress(0);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view,
                                            FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view,
                                              Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }
            };
            loadingProgressListener=new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    progressBar.setProgress(Math.round(100.0f*current/total));
                }
            };

        }
        if(flag){
            imageLoad.displayImage(url,imgview,DEFAULT_PIC_OPPIONS,loadingListener,loadingProgressListener);

        }else{
            imageLoad.displayImage(url,imgview,DEFAULT_PIC_OPPIONS,loadingListener,loadingProgressListener);
        }


    }

    public static void initImageLoader(Context context) {
        @SuppressWarnings("deprecation")
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "wxq/Cache");// 获取到缓存的目录地址
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .tasksProcessingOrder(QueueProcessingType.FIFO)
//                .writeDebugLogs() // Remove for release app
                .imageDownloader(new BaseImageDownloader(context))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
                .memoryCacheSize(20 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
    }


     //快速滑动时不加载图片
   //lvTestAdapter.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));



    public static void displayFromDrawable(int imageId, ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        ImageLoader.getInstance().displayImage("drawable://" + imageId,
                imageView);
    }
    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayFromSDCard(String uri, ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName
     *            图片名称，带后缀的，例如：1.png
     * @param imageView
     */
    public static void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
    }

//    //设置图片在下载期间显示的图片
//    2     showStubImage(R.drawable.ic_launcher)
//    3
//            4     //设置图片Uri为空或是错误的时候显示的图片
//            5     showImageForEmptyUri(R.drawable.ic_empty)
//    6
//            7     //设置图片加载/解码过程中错误时候显示的图片
//            8     showImageOnFail(R.drawable.ic_error)
//    9
//            10     //设置图片在下载前是否重置，复位
//            11     resetViewBeforeLoading()
//    12
//            13     //设置下载的图片是否缓存在内存中
//            14     cacheInMemory()
//    15
//            16     //设置下载的图片是否缓存在SD卡中
//            17     cacheOnDisc()
//    18
//            19     //设置图片的解码类型
//            20     bitmapConfig(Bitmap.Config.RGB_565)
//    21
//            22     //设置图片的解码配置
//            23     decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)
//    24
//            25     //设置图片下载前的延迟
//            26     delayBeforeLoading(int delayInMillis)
//    27
//            28     //设置额外的内容给ImageDownloader
//            29     extraForDownloader(Object extra)
//    30
//            31     //设置图片加入缓存前，对bitmap进行设置
//            32     preProcessor(BitmapProcessor preProcessor)
//    33
//            34     //设置显示前的图片，显示后这个图片一直保留在缓存中
//            35     postProcessor(BitmapProcessor postProcessor)
//    36
//            37     //设置图片以如何的编码方式显示
//            38     imageScaleType(ImageScaleType imageScaleType)
//imageScaleType(ImageScaleType imageScaleType)
//    imageScaleType:
//    EXACTLY :图像将完全按比例缩小的目标大小
//    EXACTLY_STRETCHED:图片会缩放到目标大小完全
//    IN_SAMPLE_INT:图像将被二次采样的整数倍
//    IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
//    NONE:图片不会调整

}
