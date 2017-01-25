package com.example.wxq.wxqusefullibrary.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import java.io.IOException;

import recordingvideo.MovieRecorderView;

public class RecordVideoActivity extends BaseActivity {
    private static final int REQ_CODE = 110;
    private static final int RES_CODE = 111;
    /**
     * 录制进度
     */
    private static final int RECORD_PROGRESS = 100;
    /**
     * 录制结束
     */
    private static final int RECORD_FINISH = 101;
    private MovieRecorderView movieRecorderView;
    private Button buttonShoot;
    private RelativeLayout rlBottomRoot;
    private ProgressBar progressVideo;
    private TextView textViewCountDown;
    private TextView textViewUpToCancel;//上移取消
    private TextView textViewReleaseToCancel;//释放取消
    /**
     * 是否结束录制
     */
    private boolean isFinish = true;
    /**
     * 是否触摸在松开取消的状态
     */
    private boolean isTouchOnUpToCancel = false;
    /**
     * 当前进度
     */
    private int currentTime = 0;

    /**
     * 按下的位置
     */
    private float startY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);
        String[] VIDEO_PERMISSION = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermission(VIDEO_PERMISSION, 0x0002);

        //

        initView();


    }

    private void initView() {
        movieRecorderView = (MovieRecorderView) findViewById(R.id.movieRecorderView);
        buttonShoot = (Button) findViewById(R.id.button_shoot);
        rlBottomRoot = (RelativeLayout) findViewById(R.id.rl_bottom_root);
        //progressVideo = (DonutProgress) findViewById(R.id.progress_video);
        progressVideo = (ProgressBar) findViewById(R.id.progressBar_loading);
        textViewCountDown = (TextView) findViewById(R.id.textView_count_down);
        textViewCountDown.setText("00:00");
        textViewUpToCancel = (TextView) findViewById(R.id.textView_up_to_cancel);
        textViewReleaseToCancel = (TextView) findViewById(R.id.textView_release_to_cancel);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) movieRecorderView.getLayoutParams();
        layoutParams.height = width * 4 / 3;//根据屏幕宽度设置预览控件的尺寸，为了解决预览拉伸问题
        //LogUtil.e(LOG_TAG, "mSurfaceViewWidth:" + width + "...mSurfaceViewHeight:" + layoutParams.height);
        movieRecorderView.setLayoutParams(layoutParams);

        FrameLayout.LayoutParams rlBottomRootLayoutParams = (FrameLayout.LayoutParams) rlBottomRoot.getLayoutParams();
        rlBottomRootLayoutParams.height = width / 3 * 2;
        rlBottomRoot.setLayoutParams(rlBottomRootLayoutParams);


        buttonShoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    textViewUpToCancel.setVisibility(View.VISIBLE);
                    isFinish = false;
                    startY = event.getY();
                    movieRecorderView.record(new MovieRecorderView.OnRecordFinishListener() {
                        @Override
                        public void onRecordFinish() {
                            mhandler.sendEmptyMessage(RECORD_FINISH);//录音结束
                        }
                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    textViewUpToCancel.setVisibility(View.GONE);
                    textViewReleaseToCancel.setVisibility(View.GONE);

                    if (startY - event.getY() > 100) {
                        if (!isFinish) {
                            resetData();
                        }
                    } else {
                        if (movieRecorderView.getTimeCount() > 3) {
                            mhandler.sendEmptyMessage(RECORD_FINISH);
                        } else {
                            Toast.makeText(RecordVideoActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                            resetData();

                        }


                    }


                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (startY - event.getY() > 100) {
                        isTouchOnUpToCancel = true;
                        if (textViewUpToCancel.getVisibility() == View.VISIBLE) {
                            textViewUpToCancel.setVisibility(View.GONE);
                            textViewReleaseToCancel.setVisibility(View.VISIBLE);
                        }


                    } else {
                        isTouchOnUpToCancel = false;//触摸在正常录制的位置
                        if (textViewUpToCancel.getVisibility() == View.GONE) {
                            textViewUpToCancel.setVisibility(View.VISIBLE);
                            textViewReleaseToCancel.setVisibility(View.GONE);
                        }


                    }
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    resetData();

                }


                return true;
            }
        });


        progressVideo.setMax(20);
        movieRecorderView.setOnRecordProgressListener(new MovieRecorderView.OnRecordProgressListener() {
            @Override
            public void onProgressChanged(int maxTime, int currentTime) {
                RecordVideoActivity.this.currentTime = currentTime;
                mhandler.sendEmptyMessage(RECORD_PROGRESS);
            }
        });


    }

    /**
     * 重置状态
     */
    private void resetData() {
        if (movieRecorderView.getRecordFile() != null)
            movieRecorderView.getRecordFile().delete();
        movieRecorderView.stop();
        isFinish = true;
        currentTime = 0;
        progressVideo.setProgress(0);
        textViewCountDown.setText("00:00");
        buttonShoot.setEnabled(true);
        textViewUpToCancel.setVisibility(View.GONE);
        textViewReleaseToCancel.setVisibility(View.GONE);
        try {
            movieRecorderView.initCamera();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dealWithHandMessage(Message msg) {
        switch (msg.what) {
            case RECORD_FINISH:
                if (isTouchOnUpToCancel) {//录制结束，还在上移删除状态没有松手，就复位录制
                    resetData();
                } else {//录制结束，在正常位置，录制完成跳转页面
                    isFinish = true;
                    buttonShoot.setEnabled(false);
                    finishActivity();
                }
                break;
            case RECORD_PROGRESS:
                progressVideo.setProgress(currentTime);
                if (currentTime < 10) {
                    textViewCountDown.setText("00:0" + currentTime);
                } else {
                    textViewCountDown.setText("00:" + currentTime);
                }
                break;

        }
    }
    /**
     * TODO 录制完成需要做的事情
     */
    private void finishActivity() {
        if (isFinish) {
            movieRecorderView.stop();
            showToast("path"+movieRecorderView.getRecordFile().getAbsolutePath());
            Intent intent = new Intent(this, VideoPreviewActivity.class);
            intent.putExtra("path", movieRecorderView.getRecordFile().getAbsolutePath());
            startActivityForResult(intent, REQ_CODE);
        //    resetData();

//            movieRecorderView.stop();
//            isFinish = true;
//            currentTime = 0;
//            progressVideo.setProgress(0);
//            textViewCountDown.setText("00:00");
//            buttonShoot.setEnabled(true);
//            textViewUpToCancel.setVisibility(View.GONE);
//            textViewReleaseToCancel.setVisibility(View.GONE);
//            try {
//                movieRecorderView.initCamera();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 0x0002:
                showToast("权限都有了");
                break;

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RES_CODE) {
            setResult(RES_CODE);
            finish();  // 退出页面否者返回重新录制
        }
    }
    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
        switch (requestCode) {
            case 0x0002:
                showToast("权限没有赋予");
                break;

        }
    }
}
