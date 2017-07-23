package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.imageloadutils.xutils.NetConnectTools;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonRecyclerAdapter;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.compresshelp.CompressHelper;
import me.iwf.photopicker.widget.MultiPickResultView;
import superrecycleview.RightSpacesItemDecoration;

public class UploadFilesActivity extends BaseActivity {
  RecyclerView rv_list;
    ArrayList<String> lists;
    MultiPickResultView multiPickResultView;
    TextView upload_pic;

    NineGridView ninegride;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_files);
        rv_list= (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        multiPickResultView= (MultiPickResultView) findViewById(R.id.take_phote);
        multiPickResultView.init(this,MultiPickResultView.ACTION_SELECT,null);
        upload_pic= (TextView) findViewById(R.id.upload_pic);
        lists=new ArrayList<>();
        lists.add("0");
        lists.add("12");
        lists.add("20");
        lists.add("30");

        rv_list.addItemDecoration(new RightSpacesItemDecoration(10));

        rv_list.setAdapter(new CommonRecyclerAdapter<String>(UploadFilesActivity.this, R.layout.main_list_list_item, lists) {

            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {


            }
        });
        upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(temppics.size()>0){
                    // 上传图片
                    String url="http://192.168.1.106:8080/Manager/AddCardServlet";
                    NetConnectTools.getInstance().upLoadPicture(url, null, multiPickResultView.getPhotos(), new NetConnectTools.CallBackListen() {
                        @Override
                        public void onSuccess(String result) {
                            showToast("chenggongle"+result);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });

                }


            }
        });


        List<ImageInfo> list=new ArrayList<>();

//
        ImageInfo imageInfo=new ImageInfo();
        imageInfo.setBigImageUrl("http://img2.niutuku.com/desk/anime/0529/0529-17277.jpg");
        imageInfo.setThumbnailUrl("http://img2.niutuku.com/desk/anime/0529/0529-17277.jpg");

        list.add(imageInfo);

        list.add(imageInfo);
        list.add(imageInfo);
        list.add(imageInfo);
        list.add(imageInfo);
        list.add(imageInfo);
//        九宫格
        NineGridView.setImageLoader(new GlideImageLoader());
        ninegride= (NineGridView) findViewById(R.id.ninegride);
        ninegride.setAdapter(new NineGridViewClickAdapter(this, list));
    }

    private ArrayList<String > temppics=new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        multiPickResultView.onActivityResult(requestCode, resultCode, data);
       // 创建的零食文件夹
        String temimg= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AAAaaawxq/takePhotoImageTemp";
        File file = new File(temimg);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            if(file.mkdir()){
                long afb=121212;
                //创建陈宫
                //读写权限确定得有
            }else{
                long afb=11;
                //创建陈宫
            }
        }

        temppics.clear();
        for (int i = 0; i < multiPickResultView.getPhotos().size(); i++) {
            File a=new File(multiPickResultView.getPhotos().get(i));
            showToast("文件原来大小"+a.length());
            File  oldFile=new File(multiPickResultView.getPhotos().get(i));
               File   newFile = new CompressHelper.Builder(this)
                .setMaxWidth(720)  // 默认最大宽度为720
                .setMaxHeight(960) // 默认最大高度为960
                .setQuality(80)    // 默认压缩质量为80
                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
               .setDestinationDirectoryPath(temimg)
                  .setFileName(oldFile.getName().substring(0, oldFile.getName().lastIndexOf("."))) // 设置你的文件名
                       .build()
                       .compressToFile(oldFile);

            Log.i("wxq", "size" + "压缩后台" + newFile.length());
            showToast("压缩后台" + newFile.length());  //最后清空临时文件夹内容
            temppics.add(newFile.getAbsolutePath());
        }

    }

    @Override
    public void widgetClick(View v) {

    }


        private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {

//            GlideUtil.getInstance().loadImage(UploadFilesActivity.this,imageView,url,true);
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_color)//
                    .error(R.drawable.ic_default_color)//
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
