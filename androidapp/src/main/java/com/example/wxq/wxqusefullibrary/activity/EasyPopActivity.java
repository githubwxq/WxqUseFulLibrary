package com.example.wxq.wxqusefullibrary.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import popwindow.EasyPopup;
import popwindow.HorizontalGravity;
import popwindow.VerticalGravity;


public class EasyPopActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "EasyPopActivity";



    private Button mCircleBtn;
    private Button mAboveBtn;
    private Button mRightBtn;
    private Button mBgDimBtn;
    private Button mAnyBgDimBtn;
    private Button mGiftBtn;
    private Button mComplexBtn;

    private LinearLayout mComplexBgDimView;

    private EasyPopup mWeiboPop;
    private EasyPopup mQQPop;
    private EasyPopup mCirclePop;
    private EasyPopup mAbovePop;
    private EasyPopup mBgDimPop;
    private EasyPopup mAnyBgDimPop;
//    private GiftPopup mGiftPopup;
//    private ComplexPopup mComplexPopup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_pop);

        mCircleBtn = (Button) findViewById(R.id.btn_circle_comment);
        mAboveBtn = (Button) findViewById(R.id.btn_above);
        mRightBtn = (Button) findViewById(R.id.btn_right);
        mBgDimBtn = (Button) findViewById(R.id.btn_bg_dim);
        mAnyBgDimBtn = (Button) findViewById(R.id.btn_bg_dim_any);
        mGiftBtn = (Button) findViewById(R.id.btn_gift);
        mComplexBtn = (Button) findViewById(R.id.btn_complex);
        mComplexBgDimView = (LinearLayout) findViewById(R.id.ll_complex_bg_dim);
        initQQPop();
        initWeiboPop();
        initCirclePop();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initGiftPop();
        initComplexPop();
        mCircleBtn.setOnClickListener(this);
        mAboveBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mBgDimBtn.setOnClickListener(this);
        mAnyBgDimBtn.setOnClickListener(this);
        mGiftBtn.setOnClickListener(this);
        mComplexBtn.setOnClickListener(this);
    }




    private void initQQPop() {
        mQQPop = new EasyPopup(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .createPopup();

    }

    private void showQQPop(View view) {
        mQQPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.LEFT, SizeUtils.dp2px(30), 0);
    }

    private void initWeiboPop() {
        mWeiboPop = new EasyPopup(this)
                .setContentView(R.layout.layout_center_pop)
                .setAnimationStyle(R.style.WeiboPopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();
    }

    private void showWeiboPop(View view) {
        mWeiboPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

    private void initCirclePop() {
        mCirclePop = new EasyPopup(this)
                .setContentView(R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.CirclePopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();
        TextView tvZan = mCirclePop.getView(R.id.tv_zan);
        TextView tvComment = mCirclePop.getView(R.id.tv_comment);
        tvZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCirclePop.dismiss();
            }
        });

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCirclePop.dismiss();
            }
        });
        mCirclePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.e(TAG, "onDismiss: mCirclePop");
            }
        });
    }

    private void showCirclePop(View view) {
        mCirclePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.LEFT, 0, 0);
    }

    private void initAbovePop() {
        mAbovePop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e(TAG, "onDismiss: mAbovePop");
                    }
                })
                .createPopup();
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.CENTER);
    }

    private void showRightPop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.RIGHT);
    }

    private void initBgDimPop() {
        mBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .createPopup();
    }

    private void showBgDimPop(View view) {
        mBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_TOP, HorizontalGravity.ALIGN_LEFT);
    }

    private void initAnyBgDimPop() {
        mAnyBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .setDimColor(Color.YELLOW)
                .createPopup();
    }

    private void showAnyBgDimPop(View view) {
        mAnyBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.ALIGN_RIGHT);
    }

    private void initGiftPop() {
//        mGiftPopup = new GiftPopup(this)
//                .createPopup();
    }

    private void showGiftPop(View view) {
//        mGiftPopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initComplexPop() {
//        mComplexPopup = new ComplexPopup(this)
//                .setDimView(mComplexBgDimView)
//                .createPopup();
    }

    private void showComplexPop(View view) {
//        mComplexPopup.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.LEFT);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_circle_comment:
                showCirclePop(v);
                break;
            case R.id.btn_above:
                showAbovePop(v);
                break;
            case R.id.btn_right:
                showRightPop(v);
                break;
            case R.id.btn_bg_dim:
                showBgDimPop(v);
                break;
            case R.id.btn_bg_dim_any:
                showAnyBgDimPop(v);
                break;
            case R.id.btn_gift:
                showGiftPop(v);
                break;
            case R.id.btn_complex:
                showComplexPop(v);
                break;
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_circle_comment:
                showCirclePop(v);
                break;
            case R.id.btn_above:
                showAbovePop(v);
                break;
            case R.id.btn_right:
                showRightPop(v);
                break;
            case R.id.btn_bg_dim:
                showBgDimPop(v);
                break;
            case R.id.btn_bg_dim_any:
                showAnyBgDimPop(v);
                break;
            case R.id.btn_gift:
                showGiftPop(v);
                break;
            case R.id.btn_complex:
                showComplexPop(v);
                break;
        }
    }
}
