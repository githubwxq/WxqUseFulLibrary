package com.example.wxq.wxqusefullibrary.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.wxq.wxqusefullibrary.CommonTools;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.model.MyRecorder;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.listview.animations.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import recordingvoice.AudioRecorderButton;
import recordingvoice.MediaPlayerManager;

public class RecordVoiceActivity extends BaseActivity {
    private AudioRecorderButton mAudioRecorderButton;
    private ListView mListView;
    private ArrayAdapter<MyRecorder> mAdapter;
    private List<MyRecorder> mDatas = new ArrayList<MyRecorder>();
    private  String mfilePath;
    private View animView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_voice);
        animView=findViewById(R.id.id_recoder_anim);
        animView.setBackgroundResource(R.mipmap.adj);
     //   animView.setBackgroundResource(R.drawable.play_anim);
        animView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonTools.isEmpty(mfilePath)){}else{
                    animView.setBackgroundResource(R.drawable.play_anim);
                    AnimationDrawable animation = (AnimationDrawable) animView.getBackground();
                    animation.start();
                    // 播放录音
                    MediaPlayerManager.playSound(mfilePath, new MediaPlayer.OnCompletionListener() {

                        public void onCompletion(MediaPlayer mp) {
                            //播放完成后修改图片
                            animView.setBackgroundResource(R.mipmap.adj);
                        }
                    });

                }
            }
        });
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setFinishRecorderCallBack(new AudioRecorderButton.AudioFinishRecorderCallBack() {

            public void onFinish(float seconds, final String filePath) {
            showToast(filePath);
                mfilePath=filePath;
            //    android.graphics.drawable.BitmapDrawable cannot be cast to android.graphics.drawable.AnimationDrawable
                // 声音播放动画
//                if (animView != null) {
//                    animView.setBackgroundResource(R.mipmap.adj);
//                    animView = null;
//                }
           //     animView = findViewById(R.id.id_recoder_anim);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayerManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.release();
    }
}
