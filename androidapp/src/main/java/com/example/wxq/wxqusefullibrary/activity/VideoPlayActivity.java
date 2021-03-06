//package com.example.wxq.wxqusefullibrary.activity;
//
//import android.Manifest;
//import android.annotation.TargetApi;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.cjt2325.cameralibrary.JCameraView;
//import com.cjt2325.cameralibrary.lisenter.JCameraLisenter;
//import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
//
//import java.io.File;
//
//public class VideoPlayActivity extends BaseActivity {
//    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
//    private JCameraView jCameraView;
//    private boolean granted = false;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_play);
//
//
//        jCameraView = (JCameraView) findViewById(R.id.jcameraview);
//
//        //设置视频保存路径
//        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
//
//        //JCameraView监听
//        jCameraView.setJCameraLisenter(new JCameraLisenter() {
//            @Override
//            public void captureSuccess(Bitmap bitmap) {
//                //获取图片bitmap
//                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
//            }
//
//            @Override
//            public void recordSuccess(String url, Bitmap firstFrame) {
//
//            }
//
//
//            @Override
//            public void quit() {
//                //退出按钮
//                VideoPlayActivity.this.finish();
//            }
//        });
//        //6.0动态权限获取
//        getPermissions();
//    }
//    private void getPermissions() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                //具有权限
//                granted = true;
//            } else {
//                //不具有获取权限，需要进行权限申请
//                ActivityCompat.requestPermissions(VideoPlayActivity.this, new String[]{
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
//                granted = false;
//            }
//        }
//    }
//    @Override
//    public void widgetClick(View v) {
//
//    }
//
//
//    @TargetApi(23)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == GET_PERMISSION_REQUEST) {
//            int size = 0;
//            if (grantResults.length >= 1) {
//                int writeResult = grantResults[0];
//                //读写内存权限
//                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
//                if (!writeGranted) {
//                    size++;
//                }
//                //录音权限
//                int recordPermissionResult = grantResults[1];
//                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
//                if (!recordPermissionGranted) {
//                    size++;
//                }
//                //相机权限
//                int cameraPermissionResult = grantResults[2];
//                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
//                if (!cameraPermissionGranted) {
//                    size++;
//                }
//                if (size == 0) {
//                    granted = true;
//                    jCameraView.onResume();
//                }else{
//                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//        }
//    }
//}
