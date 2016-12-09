package com.example.wxq.wxqutilslibrary.widget.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.wxq.wxqutilslibrary.R;


/**
 * http://blog.csdn.net/lmj623565791/article/details/41967509
 *
 * @author zhy
 */
public class RectImageView extends ImageView {
    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    /**
     * 圆角大小的默认值
     */
    private static final int BODER_RADIUS_DEFAULT = 10;
    /**
     * 圆角的大小
     */
    private int mBorderRadius;

    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;
    /**
     * 圆角的半径
     */
    private int mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;
    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;
    /**
     * view的宽度
     */
    private int mWidth;
    private RectF mRoundRect;

    public RectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RoundImageView);
        mBorderRadius = a.getDimensionPixelSize(
                R.styleable.RoundImageView_borderRadius, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                BODER_RADIUS_DEFAULT, getResources()
                                        .getDisplayMetrics()));// 默认为10dp
        type = a.getInt(R.styleable.RoundImageView_type, TYPE_ROUND);// 默认为Circle
        a.recycle();
    }

    public RectImageView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 如果类型是圆形，则强制改变view的宽高一致，以小值为准
         */
        if (type == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        try {
            mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWidth * 1.0f / bSize;
        } else if (type == TYPE_ROUND) {
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
//                String Tag = "exue";
//                Log.e(Tag, "getWidth==" + getWidth());
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight() * 1.0f / bmp.getHeight());
//                System.out.println("scale==" + scale);
//                System.out.println("getWidth()==" + getWidth());
//                System.out.println("getHeight()==" + getHeight());
//                System.out.println("bmp.getWidth()==" + bmp.getWidth());
//                System.out.println("bmp.getHeight()==" + bmp.getHeight());
//                System.out.println("bmp.getWidth()*scale==" + bmp.getWidth() * scale);
//                System.out.println("bmp.getHeight()*scale==" + bmp.getHeight() * scale);
//                Log.e("exue", "scale=" + scale);
//                if (scale < 1) {
//                    float scaleWidth = bmp.getWidth() * scale;
//                    float scaleHeight = bmp.getHeight() * scale;
//                    if (mRoundRect == null) {
//                        mRoundRect = new RectF(0, 0, getWidth(), getHeight());
//                    }
//                    if (scaleHeight <= getHeight() && scaleWidth <= getWidth()) {
//                        mRoundRect.left = 0;
//                        mRoundRect.top = 0;
//                        mRoundRect.right = getWidth();
//                        mRoundRect.bottom = getHeight();
//                    } else if (scaleHeight <= getHeight() && scaleWidth > getWidth()) {
//                        mRoundRect.left = bmp.getWidth() / 2.0f - getWidth() / 2.0f;
//                        mRoundRect.right = bmp.getWidth() / 2.0f + getWidth() / 2.0f;
//                        mRoundRect.top = 0;
//                        mRoundRect.bottom = getHeight();
//                    } else if (scaleHeight > getHeight() && scaleWidth <= getWidth()) {
//                        mRoundRect.left = 0;
//                        mRoundRect.right = getWidth();
//                        mRoundRect.top = bmp.getHeight() / 2.0f - getHeight() / 2.0f;
//                        mRoundRect.bottom = bmp.getHeight() / 2.0f + getHeight() / 2.0f;
//                    } else {
//                        mRoundRect.left = bmp.getWidth() / 2.0f - getWidth() / 2.0f;
//                        mRoundRect.right = bmp.getWidth() / 2.0f + getWidth() / 2.0f;
//                        mRoundRect.top = bmp.getHeight() / 2.0f - getHeight() / 2.0f;
//                        mRoundRect.bottom = bmp.getHeight() / 2.0f + getHeight() / 2.0f;
//                    }
//                }
            }
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setUpShader();
        if (type == TYPE_ROUND) {
            mBitmapPaint.setColor(Color.WHITE);

            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBitmapPaint);
        } else {
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 圆角图片的范围
        if (type == TYPE_ROUND)
            mRoundRect = new RectF(0, 0, w, h);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (this.mBorderRadius != pxVal) {
            this.mBorderRadius = pxVal;
            invalidate();
        }
    }

    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }


    //圆角矩形
//    android:src="@mipmap/icon"
//    app:borderRadius="2dp"
//    app:type="round"

    //圆形
//    a android:scaleType="centerCrop"
// app:borderRadius="3dp"
//   app:type="circle"

}