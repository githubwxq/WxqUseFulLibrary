package com.example.wxq.wxqutilslibrary.widget.roundview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/** 用于需要圆角矩形框背景的TextView的情况,减少直接使用TextView时引入的shape资源文件 */
public class RoundTextView extends TextView {
    private RoundViewDelegate delegate;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RoundViewDelegate(this, context, attrs);
    }

    /** use delegate to set attr */
    public RoundViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate.isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate.isRadiusHalfHeight()) {
            delegate.setCornerRadius(getHeight() / 2);
        } else {
            delegate.setBgSelector();
        }
    }

                                       //使用说明

//    final RoundTextView rtv_3 = (RoundTextView) findViewById(R.id.rtv_3);
//    rtv_3.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            RoundViewDelegate delegate = rtv_3.getDelegate();
//            delegate.setBackgroundColor(
//                    delegate.getBackgroundColor() == Color.parseColor("#ffffff")
//                            ? Color.parseColor("#F6CE59") : Color.parseColor("#ffffff"));
//        }
//    });



//
//    rv:rv_cornerRadius_TR="10dp"
//    rv:rv_backgroundColor="#ffffff"
//    rv:rv_backgroundPressColor="#03A9F4"
//    rv:rv_isRadiusHalfHeight="true"
//    rv:rv_isRippleEnable="false"
//    rv:rv_strokeColor="#03A9F4"
//    rv:rv_strokePressColor="#6D8FB0"
//    rv:rv_strokeWidth="1dp"
//    rv:rv_textPressColor="#ffffff"/>
}
