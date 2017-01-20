package com.example.wxq.wxqusefullibrary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import recordingvoice.AudioRecorderButton;

public class RecordVoiceActivity extends BaseActivity {
    private AudioRecorderButton mAudioRecorderButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_voice);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setFinishRecorderCallBack(new AudioRecorderButton.AudioFinishRecorderCallBack() {

            public void onFinish(float seconds, String filePath) {
            showToast(filePath);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }
}
