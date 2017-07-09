package com.example.wxq.wxqusefullibrary.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import camera.TextureCameraPreview;

public class CameraActivity extends BaseActivity {
    TextureCameraPreview mTextureCameraPreview;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // 声明变量
        mTextureCameraPreview= (TextureCameraPreview) findViewById(R.id.texture_camera_view);
        button= (Button) findViewById(R.id.btn_button);
        button.setOnClickListener(this);
// 拍照
        takephoto();


    }

    private void takephoto() {
        mTextureCameraPreview.takePicture(new TextureCameraPreview.OnPictureTakenListener() {

//            public void onSuccess(byte[] data) {
//                //TODO 拍照成功时回调
//            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                showToast("okoko");
            }

            @Override
            public void onStoreFileSuccess(String path) {
                showToast(path);
            }

            @Override
            public void onFailed(String msg) {
                //TODO 拍照失败时回调
                showToast(msg);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        takephoto();
    }
}
