package com.example.wxq.wxqusefullibrary.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPickUtils;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.BimpUtils;
import me.iwf.photopicker.utils.ImagePreference;
import me.iwf.photopicker.widget.MultiPickResultView;

public class ChoosePicActivity extends BaseActivity implements View.OnClickListener {

    private Button button;
    private Button button_no_camera;
    private Button button_one_photo;
    private Button button_photo_gif;
    private Button button_multiple_picked;
    private MultiPickResultView recycler_view;
    private MultiPickResultView recycler_onlylook;
    private MultiPickResultView recyclerView;

    ArrayList<String> selectedPhotos = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        initView();

         Gson gson=new Gson();

    }

    @Override
    public void widgetClick(View v) {

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        button_no_camera = (Button) findViewById(R.id.button_no_camera);
        button_one_photo = (Button) findViewById(R.id.button_one_photo);
        button_photo_gif = (Button) findViewById(R.id.button_photo_gif);
        button_multiple_picked = (Button) findViewById(R.id.button_multiple_picked);
        recycler_view = (MultiPickResultView) findViewById(R.id.recycler_view);
        recycler_onlylook = (MultiPickResultView) findViewById(R.id.recycler_onlylook);
        recyclerView= (MultiPickResultView) findViewById(R.id.recyclerView);

        button.setOnClickListener(this);
        button_no_camera.setOnClickListener(this);
        button_one_photo.setOnClickListener(this);
        button_photo_gif.setOnClickListener(this);
        button_multiple_picked.setOnClickListener(this);
        ImagePreference.getInstance(getApplicationContext()).clearAllImagesList();
        BimpUtils.deleteFile(this);
       // resultView.init(8, this, MultiPickResultView.ACTION_SELECT, ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.DRR));
        recyclerView.init(8,this,MultiPickResultView.ACTION_SELECT,ImagePreference.getInstance(this).getImagesList(ImagePreference.DRR));
    //    recycler_view.init(this, MultiPickResultView.ACTION_SELECT, null);

    }

    @Override
    protected void onDestroy() {
        ImagePreference.getInstance(getApplicationContext()).clearAllImagesList();
        BimpUtils.deleteFile(this);
        sendBroadcast(new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                + Environment.getExternalStorageDirectory())));
        super.onDestroy();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//                PhotoPicker.builder()
//                        .setPhotoCount(4)
//                        .setShowCamera(true)
//                        .setSelected(selectedPhotos)
//                        .start(this);
                break;

            case R.id.button_no_camera:

                break;
            case R.id.button_one_photo:

                break;
            case R.id.button_photo_gif:

                break;
            case R.id.button_multiple_picked:

                break;
        }
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        recyclerView.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
//
//        recyclerView.onActivityResult(requestCode, resultCode, data);
//        for (String s : recyclerView.getPhotos()) {
//            Log.i("wxq", s);
//        }
//
//
//        recyclerViewShowOnly.showPics(recyclerView.getPhotos());
//       selectedPhotos=recycler_view.getPhotos();
//        recycler_view.onActivityResult(requestCode, resultCode, data);
//   PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
//      @Override
//      public void onPickSuccess(ArrayList<String> photos) {
//          showToast("onPickSuccess"+photos.size());
//   //     photoAdapter.add(photos);
//      }
//
//      @Override
//      public void onPreviewBack(ArrayList<String> photos) {
//     //   photoAdapter.refresh(photos);
//          showToast("onPreviewBack"+photos.size());
//      }
//
//      @Override
//      public void onPickFail(String error) {
////        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
////        selectedPhotos.clear();
////        photoAdapter.notifyDataSetChanged();
//      }
//
//      @Override
//      public void onPickCancle() {
//        //Toast.makeText(MainActivity.this,"取消选择",Toast.LENGTH_LONG).show();
//      }




//
//    });

        //photoAdapter.refresh();


   /* if (resultCode == RESULT_OK &&
        (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

      List<String> photos = null;
      if (data != null) {
        photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
      }
      selectedPhotos.clear();

      if (photos != null) {

        selectedPhotos.addAll(photos);
      }
      photoAdapter.notifyDataSetChanged();
    }*/
    }
//        获取结果集合
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//
//            case 233:
//                if (resultCode == RESULT_OK) {
//                    ArrayList<String> uploadDir = ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.UPLOADDIR);
//                    if (uploadDir.size() == 0) { //无图片时 还是第一张 没有插好
//                        mTakePhoto.setBackgroundResource(R.mipmap.tw_camera_nor);
//                        cancel_photo.setVisibility(View.GONE);
//                        rl_photo2.setVisibility(View.GONE);
//                        rl_photo3.setVisibility(View.GONE);
//                    } else if (uploadDir.size() == 1) {
//                        cancel_photo.setVisibility(View.VISIBLE);
//                        //   cancel_photo2.setVisibility(View.GONE);
//                        mTakePhoto.setVisibility(View.VISIBLE);
//                        mTakePhoto.setBackgroundResource(R.mipmap.tw_add_nor);
//                        //加载第一张图片
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(0), new ImageSize(100, 100), mPhoto);
//                    } else if (uploadDir.size() == 2) {
//                        cancel_photo2.setVisibility(View.VISIBLE);
//                        cancel_photo3.setVisibility(View.GONE);
//                        cancel_photo.setVisibility(View.VISIBLE);
//                        rl_photo2.setVisibility(View.VISIBLE);
//                        take_photo2.setVisibility(View.VISIBLE);
//                        mTakePhoto.setVisibility(View.GONE);
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(0), new ImageSize(100, 100), mPhoto);
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(1), new ImageSize(100, 100), iv_photo2);
//                    } else if (uploadDir.size() == 3) {
//                        cancel_photo2.setVisibility(View.VISIBLE);
//                        cancel_photo3.setVisibility(View.VISIBLE);
//                        cancel_photo.setVisibility(View.VISIBLE);
//                        rl_photo2.setVisibility(View.VISIBLE);
//                        rl_photo3.setVisibility(View.VISIBLE);
//                        take_photo2.setVisibility(View.GONE);
//                        take_photo3.setVisibility(View.GONE);
//                        mTakePhoto.setVisibility(View.GONE);
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(0), new ImageSize(100, 100), mPhoto);
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(1), new ImageSize(100, 100), iv_photo2);
//                        LoadingImgUtil.loadingLocalImage(uploadDir.get(2), new ImageSize(100, 100), iv_photo3);
//                    }
//                }
//                break;
//            default:
//                super.onActivityResult(requestCode, resultCode, data);
//                break;
//        }
//    }




}
