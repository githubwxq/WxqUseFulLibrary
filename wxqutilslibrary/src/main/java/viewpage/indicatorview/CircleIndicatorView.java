package viewpage.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.wxq.wxqutilslibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwei on 17/5/22.
 */

public class CircleIndicatorView extends View implements ViewPager.OnPageChangeListener {
    private static final String LETTER[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    // private int mSelectColor = Color.parseColor("#E38A7C");
    private int mSelectColor = Color.parseColor("#FFFFFF");
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private int mCount; // indicator 的数量
    private int mRadius;//半径
    private int mStrokeWidth;//border
    private int mTextColor;// 小圆点中文字的颜色
    private int mDotNormalColor;// 小圆点默认颜色
    private int mSpace = 0;// 圆点之间的间距
    private List<Indicator> mIndicators;
    private int mSelectPosition = 0; // 选中的位置
    private FillMode mFillMode = FillMode.NONE;// 默认只有小圆点
    private ViewPager mViewPager;
    private OnIndicatorClickListener mOnIndicatorClickListener;
    /**
     * 是否允许点击Indicator切换ViewPager
     */
    private boolean mIsEnableClickSwitch = false;

    public CircleIndicatorView(Context context) {
        super(context);
        init();
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttr(context, attrs);
        init();
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context, attrs);
        init();
    }


    private void init() {

        mCirclePaint = new Paint();
        mCirclePaint.setDither(true);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextPaint = new Paint();
        mTextPaint.setDither(true);
        mTextPaint.setAntiAlias(true);
        // 默认值
        mIndicators = new ArrayList<>();

        initValue();

    }

    private void initValue() {
        mCirclePaint.setColor(mDotNormalColor);
        mCirclePaint.setStrokeWidth(mStrokeWidth);

        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mRadius);
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private void getAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView);
        mRadius = (int) typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorRadius, DisplayUtils.dpToPx(6));
        mStrokeWidth = (int) typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorBorderWidth, DisplayUtils.dpToPx(2));
        mSpace = typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorSpace, DisplayUtils.dpToPx(5));
        // color
        mTextColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorTextColor, Color.BLACK);
        mSelectColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorSelectColor, Color.WHITE);
        mDotNormalColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorColor, Color.GRAY);

        mIsEnableClickSwitch = typedArray.getBoolean(R.styleable.CircleIndicatorView_enableIndicatorSwitch, false);
        int fillMode = typedArray.getInt(R.styleable.CircleIndicatorView_fill_mode, 2);
        if (fillMode == 0) {
            mFillMode = FillMode.LETTER;
        } else if (fillMode == 1) {
            mFillMode = FillMode.NUMBER;
        } else {
            mFillMode = FillMode.NONE;
        }
        typedArray.recycle();
    }

    /**
     * 测量每个圆点的位置
     */
    private void measureIndicator() {
        mIndicators.clear();
        float cx = 0;
        for (int i = 0; i < mCount; i++) {
            Indicator indicator = new Indicator();
            if (i == 0) {
                cx = mRadius + mStrokeWidth;
            } else {
                cx += (mRadius + mStrokeWidth) * 2 + mSpace;
            }

            indicator.cx = cx;
            indicator.cy = getMeasuredHeight() / 2;

            mIndicators.add(indicator);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = (mRadius + mStrokeWidth) * 2 * mCount + mSpace * (mCount - 1);
        int height = mRadius * 2 + mSpace * 2;

        setMeasuredDimension(width, height);

        measureIndicator();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < mIndicators.size(); i++) {

            Indicator indicator = mIndicators.get(i);
            float x = indicator.cx;

            float y = indicator.cy;

            if (mSelectPosition == i) {
                mCirclePaint.setStyle(Paint.Style.FILL);
                mCirclePaint.setColor(mSelectColor);
            } else {
                mCirclePaint.setColor(mDotNormalColor);
                if (mFillMode != FillMode.NONE) {
                    mCirclePaint.setStyle(Paint.Style.STROKE);
                } else {
                    mCirclePaint.setStyle(Paint.Style.FILL);

                }
            }
            canvas.drawCircle(x, y, mRadius, mCirclePaint);

            // 绘制小圆点中的内容
            if (mFillMode != FillMode.NONE) {
                String text = "";
                if (mFillMode == FillMode.LETTER) {
                    if (i >= 0 && i < LETTER.length) {
                        text = LETTER[i];
                    }
                } else {
                    text = String.valueOf(i + 1);
                }
                Rect bound = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), bound);
                int textWidth = bound.width();
                int textHeight = bound.height();

                float textStartX = x - textWidth / 2;
                float textStartY = y + textHeight / 2;
                canvas.drawText(text, textStartX, textStartY, mTextPaint);
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPoint = 0;
        float yPoint = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xPoint = event.getX();
                yPoint = event.getY();
                handleActionDown(xPoint, yPoint);
                break;

        }

        return super.onTouchEvent(event);
    }

    private void handleActionDown(float xDis, float yDis) {
        for (int i = 0; i < mIndicators.size(); i++) {
            Indicator indicator = mIndicators.get(i);
            if (xDis < (indicator.cx + mRadius + mStrokeWidth)
                    && xDis >= (indicator.cx - (mRadius + mStrokeWidth))
                    && yDis >= (yDis - (indicator.cy + mStrokeWidth))
                    && yDis < (indicator.cy + mRadius + mStrokeWidth)) {
                // 找到了点击的Indicator
                // 是否允许切换ViewPager
                if (mIsEnableClickSwitch) {
                    mViewPager.setCurrentItem(i, false);
                }

                // 回调
                if (mOnIndicatorClickListener != null) {
                    mOnIndicatorClickListener.onSelected(i);
                }
                break;
            }
        }
    }

    public void setOnIndicatorClickListener(OnIndicatorClickListener onIndicatorClickListener) {
        mOnIndicatorClickListener = onIndicatorClickListener;
    }

    private void setCount(int count) {
        mCount = count;
        invalidate();
    }

    /**
     * 设置 border
     *
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth) {
        mStrokeWidth = borderWidth;
        initValue();
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        mTextColor = textColor;
        initValue();
    }

    /**
     * 设置选中指示器的颜色
     *
     * @param selectColor
     */
    public void setSelectColor(int selectColor) {
        mSelectColor = selectColor;
    }

    /**
     * 设置指示器默认颜色
     *
     * @param dotNormalColor
     */
    public void setDotNormalColor(int dotNormalColor) {
        mDotNormalColor = dotNormalColor;
        initValue();
    }

    /**
     * 设置选中的位置
     *
     * @param selectPosition
     */
    public void setSelectPosition(int selectPosition) {
        mSelectPosition = selectPosition;
    }

    /**
     * 设置Indicator 模式
     *
     * @param fillMode
     */
    public void setFillMode(FillMode fillMode) {
        mFillMode = fillMode;
    }

    /**
     * 设置Indicator 半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        mRadius = radius;
        initValue();
    }

    public void setSpace(int space) {
        mSpace = space;
    }

    public void setEnableClickSwitch(boolean enableClickSwitch) {
        mIsEnableClickSwitch = enableClickSwitch;
    }

    /**
     * 与ViewPager 关联
     *
     * @param viewPager
     */
    public void setUpWithViewPager(ViewPager viewPager) {
        releaseViewPager();
        if (viewPager == null) {
            return;
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        int count = mViewPager.getAdapter().getCount();
        setCount(count);
    }

    /**
     * 重置ViewPager
     */
    private void releaseViewPager() {
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
            mViewPager = null;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mSelectPosition = position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Indicator 点击回调
     */
    public interface OnIndicatorClickListener {
        public void onSelected(int position);
    }


    public static class Indicator {
        public float cx; // 圆心x坐标
        public float cy; // 圆心y 坐标
    }

    public enum FillMode {
        LETTER,
        NUMBER,
        NONE
    }
}
//s使用方法
//<android.support.v4.view.ViewPager
//        android:id="@+id/viewpager"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent">
//
//</android.support.v4.view.ViewPager>
//<ImageView
//         android:layout_width="match_parent"
//                 android:layout_height="match_parent"
//                 android:src="#000000"
//                 android:alpha="0.6"
//                 />
//<com.zhouwei.indicatorview.CircleIndicatorView
//        android:id="@+id/indicator_view"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_alignParentBottom="true"
//        android:layout_marginBottom="50dp"
//        android:layout_centerHorizontal="true"
//        app:indicatorSelectColor="#00A882"
//        app:fill_mode="letter"
//        app:indicatorBorderWidth="2dp"
//        app:indicatorRadius="8dp"
//        app:indicatorColor="@color/colorAccent"
//        app:indicatorTextColor="@android:color/white"
//        />
//<com.zhouwei.indicatorview.CircleIndicatorView
//        android:id="@+id/indicator_view1"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_above="@+id/indicator_view"
//        android:layout_centerHorizontal="true"
//        android:layout_marginBottom="30dp"
//        app:indicatorColor="#273142"
//        app:fill_mode="number"
//        app:enableIndicatorSwitch="true"
//        app:indicatorTextColor="@android:color/white"
//        app:indicatorSelectColor="#C79EFE"
//        app:indicatorRadius="10dp"
//        app:indicatorSpace="10dp"
//        app:indicatorBorderWidth="0dp"
//        />
//<com.zhouwei.indicatorview.CircleIndicatorView
//        android:id="@+id/indicator_view2"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_centerHorizontal="true"
//        android:layout_marginBottom="30dp"
//        android:layout_above="@id/indicator_view1"
//        />
//
//<com.zhouwei.indicatorview.CircleIndicatorView
//        android:id="@+id/indicator_view3"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_centerHorizontal="true"
//        android:layout_marginBottom="30dp"
//        android:layout_above="@id/indicator_view2"
//        />
//    private void initView() {
//        // 初始化ViewPager 相关
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        mPagerAdapter = new ViewPagerAdapter();
//        mViewPager.setAdapter(mPagerAdapter);
//
//        mIndicatorView = (CircleIndicatorView) findViewById(R.id.indicator_view);
//        // 关联ViewPager
//        mIndicatorView.setUpWithViewPager(mViewPager);
//
//        CircleIndicatorView indicatorView1 = (CircleIndicatorView) findViewById(R.id.indicator_view1);
//        CircleIndicatorView indicatorView2 = (CircleIndicatorView) findViewById(R.id.indicator_view2);
//
//        indicatorView1.setUpWithViewPager(mViewPager);
//        indicatorView2.setUpWithViewPager(mViewPager);
//
//        // 在代码中设置相关属性
//        CircleIndicatorView indicatorView = (CircleIndicatorView) findViewById(R.id.indicator_view3);
//        // 设置半径
//        indicatorView.setRadius(DisplayUtils.dpToPx(15));
//        // 设置Border
//        indicatorView.setBorderWidth(DisplayUtils.dpToPx(2));
//
//        // 设置文字颜色
//        indicatorView.setTextColor(Color.WHITE);
//        // 设置选中颜色
//        indicatorView.setSelectColor(Color.parseColor("#FEBB50"));
//        //
//        indicatorView.setDotNormalColor(Color.parseColor("#E38A7C"));
//        // 设置指示器间距
//        indicatorView.setSpace(DisplayUtils.dpToPx(10));
//        // 设置模式
//        indicatorView.setFillMode(CircleIndicatorView.FillMode.LETTER);
//        // 设置点击Indicator可以切换ViewPager
//        indicatorView.setEnableClickSwitch(true);
//
//        // 最重要的一步：关联ViewPager
//        indicatorView.setUpWithViewPager(mViewPager);
//
//        indicatorView.setOnIndicatorClickListener(new CircleIndicatorView.OnIndicatorClickListener() {
//            @Override
//            public void onSelected(int position) {
//
//            }
//        });
//
//
//    }