package com.example.wxq.wxqusefullibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.modelmanageer.UserManger;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import emoji.EmojiFilter;
import emoji.ExpressionView;
import emoji.MTextView;
import emoji.SmileyParser;

public class MemoryActivity extends BaseActivity {

    private android.widget.TextView tvsingle;
    private android.widget.LinearLayout activitymemory;
  private ExpressionView emoji_relative;
   private MTextView mtext;
    private EditText et_text;
    private SmileyParser smileyParser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        emoji_relative= (ExpressionView) findViewById(R.id.emoji_relative);

        smileyParser = new SmileyParser(this);
        et_text= (EditText) findViewById(R.id.et_text);

        this.activitymemory = (LinearLayout) findViewById(R.id.activity_memory);
        this.tvsingle = (TextView) findViewById(R.id.tv_single);
        UserManger userManger = UserManger.getInstance(this);
        tvsingle.setOnClickListener(this);


        emoji_relative.initEmojiView(this, et_text, 500);
        mtext= (MTextView) findViewById(R.id.mtext);


        et_text.setFilters(new InputFilter[]{new EmojiFilter(this), new InputFilter.LengthFilter(500)});
        et_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        et_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        et_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mtext.setMText(smileyParser.replace(s,mtext));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });





    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_single:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
