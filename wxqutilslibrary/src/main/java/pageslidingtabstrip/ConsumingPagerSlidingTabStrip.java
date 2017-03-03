/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pageslidingtabstrip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.wxq.wxqutilslibrary.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import commontools.CommonTools;

public class ConsumingPagerSlidingTabStrip extends HorizontalScrollView {

    public interface IconTabProvider {
        public int getPageIconResId(int position);
    }

    // @formatter:off
    private static final int[] ATTRS = new int[]{android.R.attr.textSize,
            android.R.attr.textColor};
    // @formatter:on

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private final PageListener pageListener = new PageListener();
    public OnPageChangeListener delegatePageListener;
    private LinearLayout tabsContainer;
    private ViewPager pager;
    private OnTabClickListener onTabClickListener;
    private int tabCount;

    private int currentPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;

    private int indicatorColor = 0xffffff;// ...................
    private int underlineColor = 0xffa500;// ....................
    private int dividerColor = 0x1A000000;

    private boolean shouldExpand = false;
    private boolean textAllCaps = false;

    private int scrollOffset = 52;
    private int indicatorHeight = 8;
    private int underlineHeight = 2;
    private int dividerPadding = 12;
    private int tabPadding = 0;// .....................
    private int dividerWidth = 1;

    private int tabTextSize = 16;
    private int tabTextColor = 0x4C73F5;// ...................
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = 0;
    private boolean isShowLine = false;//设为true则不显示分割线并且下划线满格
    private boolean isShowUnderLine = true;//是否显示下划线

    private int lastScrollX = 0;

    private int tabBackgroundResId = 0;
    // private int tabBackgroundResId = R.drawable.background_tab;

    private Locale locale;

    private boolean isDoubleTab = false;//是否显示两层的tab

    private int curPosition = 0;

    private int tabDefaultColor = getResources().getColor(R.color.main_color),
            tabSelectedColor = getResources().getColor(R.color.grey);

    private int iconShowDirection = -1;
    private final int LEFT = 0;
    private final int RIGHT = 1;

    private int[] positions = null;

    private int[] iconIds = null;

    private Context context;


    public ConsumingPagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public ConsumingPagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressWarnings("ResourceType")
    public ConsumingPagerSlidingTabStrip(Context context, AttributeSet attrs,
                                         int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setMotionEventSplittingEnabled(false);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        scrollOffset = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
        indicatorHeight = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
        underlineHeight = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
        dividerPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
        tabPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
        dividerWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
        tabTextSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);

        // get system attrs (android:textSize and android:textColor)

        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
        tabTextColor = a.getColor(1, tabTextColor);

        a.recycle();

        // get custom attrs

        a = context.obtainStyledAttributes(attrs,
                R.styleable.PagerSlidingTabStrip);

        iconShowDirection = a.getInt(R.styleable.PagerSlidingTabStrip_pstsShowIconDirection, -1);
        indicatorColor = a.getColor(
                R.styleable.PagerSlidingTabStrip_pstsIndicatorColor,
                indicatorColor);
        underlineColor = a.getColor(
                R.styleable.PagerSlidingTabStrip_pstsUnderlineColor,
                underlineColor);
        dividerColor = a
                .getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor,
                        dividerColor);
        indicatorHeight = a.getDimensionPixelSize(
                R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight,
                indicatorHeight);
        underlineHeight = a.getDimensionPixelSize(
                R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight,
                underlineHeight);
        dividerPadding = a.getDimensionPixelSize(
                R.styleable.PagerSlidingTabStrip_pstsDividerPadding,
                dividerPadding);
        tabPadding = a.getDimensionPixelSize(
                R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight,
                tabPadding);
        tabBackgroundResId = a.getResourceId(
                R.styleable.PagerSlidingTabStrip_pstsTabBackground,
                tabBackgroundResId);
        shouldExpand = a
                .getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand,
                        shouldExpand);
        scrollOffset = a
                .getDimensionPixelSize(
                        R.styleable.PagerSlidingTabStrip_pstsScrollOffset,
                        scrollOffset);
        textAllCaps = a.getBoolean(
                R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

        tabDefaultColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsTabDefaultColor, tabDefaultColor);
        tabSelectedColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsTabSelectedColor, tabSelectedColor);
        tabTextSize = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabTextSize, tabTextSize);
        isShowLine = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShowLine, isShowLine);
        isDoubleTab = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsDoubleTab, isDoubleTab);
        isShowUnderLine = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShowUnderLine, isShowUnderLine);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Style.FILL);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException(
                    "ViewPager does not have adapter instance.");
        }

        pager.addOnPageChangeListener(pageListener);
        notifyDataSetChanged();
    }

    public ViewPager getViewPager() {
        return pager;
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }


    public void setOnTabClickListener(OnTabClickListener drawableRight) {
        this.onTabClickListener = drawableRight;

    }

    public void notifyDataSetChanged() {

        tabsContainer.removeAllViews();

        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount; i++) {

            if (pager.getAdapter() instanceof IconTabProvider) {
//                addIconTab(i,
//                        ((IconTabProvider) pager.getAdapter())
//                                .getPageIconResId(i));
            } else {
                addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
            }

        }
        updateTabStyles();

        getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener() {

                    @SuppressWarnings("deprecation")
                    @SuppressLint("NewApi")
                    @Override
                    public void onGlobalLayout() {

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }

                        currentPosition = pager.getCurrentItem();
                        scrollToChild(currentPosition, 0);
                    }
                });

    }

    private void addTextTab(final int position, String title) {
        if (!isDoubleTab) {
            View v = View.inflate(getContext(), R.layout.tab_desc, null);
            TextView text = (TextView) v.findViewById(R.id.text);
            if (iconShowDirection != -1 && positions != null && iconIds != null && positions.length == iconIds.length) {
                for (int index : positions) {
                    if (index == position) {
                        if (iconShowDirection == LEFT) {
                            text.setCompoundDrawablesWithIntrinsicBounds(iconIds[index], 0, 0, 0);
                        } else {
                            text.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconIds[index], 0);
                        }
                        text.setCompoundDrawablePadding(CommonTools.dip2px(context, 5));
                        break;
                    }
                }
            }
            text.setText(title);
            addTab(position, v);
        } else {
            View view = View.inflate(getContext(), R.layout.layout_text_tab, null);
            TextView classname = (TextView) view.findViewById(R.id.classname);
            TextView schoolname = (TextView) view.findViewById(R.id.schoolname);
            if (title.contains(";")) {
                String[] strs = title.split(";");
                schoolname.setText(strs[0]);
                classname.setText(strs[1]);
                addTab(position, view);
            } else {
                classname.setText(title.substring(0, title.indexOf("(")));
                schoolname.setText(title.substring(title.indexOf("(") + 1, title.indexOf(")")));
                addTab(position, view);
            }
        }

    }

    public interface OnTabClickListener {
        void onTabclick(View v, int position);
    }
    public static final int MIN_CLICK_DELAY_TIME = 1000;//这里设置不能超过多长时间
    private long lastClickTime = 0;
    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                    lastClickTime = currentTime;

                    if (onTabClickListener != null) {
                        onTabClickListener.onTabclick(v, position);
                    }
                    pager.setCurrentItem(position);
                }

            }
        });
       // tab.setOnClickListener(new NoDoubleClickListener(onTabClickListener,position,pager));
        setShouldExpand(true);// 设置weight=1f，实现平铺的效果，我加的。
        if (position == pager.getCurrentItem()) {
            if (isDoubleTab) {
                ((TextView) (tab.findViewById(R.id.classname))).setTextColor(tabSelectedColor);
            } else {
                ((TextView) tab.findViewById(R.id.text)).setTextColor(tabSelectedColor);
            }
        } else {
            if (isDoubleTab) {
                ((TextView) (tab.findViewById(R.id.classname))).setTextColor(tabDefaultColor);
            } else {
                ((TextView) tab.findViewById(R.id.text)).setTextColor(tabDefaultColor);
            }
        }
        tabsContainer
                .addView(tab, position, shouldExpand ? expandedTabLayoutParams
                        : defaultTabLayoutParams);
    }

    private void updateTabStyles() {

        for (int i = 0; i < tabCount; i++) {

            View v = tabsContainer.getChildAt(i);

            v.setBackgroundResource(tabBackgroundResId);

            if (v instanceof RelativeLayout) {

                TextView tab = (TextView) v.findViewById(R.id.text);
                tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        tab.setAllCaps(true);
                    } else {
                        tab.setText(tab.getText().toString().toUpperCase(locale));
                    }
                }
            } else {
                TextView classname = (TextView) v.findViewById(R.id.classname);
                TextView schoolname = (TextView) v.findViewById(R.id.schoolname);

                classname.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                classname.setTypeface(tabTypeface, tabTypefaceStyle);

                schoolname.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
                schoolname.setTypeface(tabTypeface, tabTypefaceStyle);

                if (textAllCaps) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        classname.setAllCaps(true);
                        schoolname.setAllCaps(true);
                    } else {
                        classname.setText(classname.getText().toString().toUpperCase(locale));
                        schoolname.setText(schoolname.getText().toString().toUpperCase(locale));
                    }
                }
            }
        }

    }

    private void scrollToChild(int position, int offset) {

        if (tabCount == 0) {
            return;
        }

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || tabCount == 0) {
            return;
        }

        final int height = getHeight();


        rectPaint.setColor(indicatorColor);

        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);

        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();
        int tabwidth = 0;
        if (isShowLine) {
            tabwidth = currentTab.getPaddingLeft();
        }

        // if there is an offset, start interpolating left and right coordinates
        // between current and next tab
        if (isShowUnderLine) {
            if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {
                View nextTab = tabsContainer.getChildAt(currentPosition + 1);
                final float nextTabLeft = nextTab.getLeft();
                final float nextTabRight = nextTab.getRight();
                lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset)
                        * lineLeft);
                lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset)
                        * lineRight);
            }

            canvas.drawRect(lineLeft + tabwidth, height - indicatorHeight, lineRight - tabwidth, height, rectPaint);

            // draw underline
            rectPaint.setColor(underlineColor);
            canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);
        }

        // draw divider
        dividerPaint.setColor(dividerColor);
        if (isShowLine) {
            for (int i = 0; i < tabCount - 1; i++) {
                View tab = tabsContainer.getChildAt(i);
                canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(),
                        height - dividerPadding, dividerPaint);
            }
        }

    }

    private List<Fragment> fragments; // 每个Fragment对应一个Page

    public void addFragmentsLsit(List<Fragment> list) {
        this.fragments = list;
    }

    private class PageListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

            currentPosition = position;
            currentPositionOffset = positionOffset;

            if (position > 1) {
                scrollToChild(position, (int) (positionOffset * tabsContainer
                        .getChildAt(position).getWidth()));
            }
            invalidate();

            if (delegatePageListener != null) {
                delegatePageListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager.getCurrentItem(), 0);
            }
            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            setColor(position);

            if (fragments != null && !fragments.isEmpty()) {
                if (fragments.get(position).isAdded()) {
   //、、注意！！！！！！！！！！！！！！！！
   //                 fragments.get(position).stateUpdate();

                }
            }
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
        }

    }

    /**
     * 滑动tab修改字体颜色，我写的。
     *
     * @param position
     */
    public void setColor(int position) {
        if (position != curPosition) {
            if (isDoubleTab) {
                View view = tabsContainer.getChildAt(position);
                TextView tv = (TextView) view.findViewById(R.id.classname);
                View curView = tabsContainer.getChildAt(curPosition);
                TextView curTv = (TextView) curView.findViewById(R.id.classname);
                tv.setTextColor(tabSelectedColor);
                curTv.setTextColor(tabDefaultColor);

            } else {
                TextView tv = (TextView) tabsContainer.getChildAt(position).findViewById(R.id.text);
                TextView curTv = (TextView) tabsContainer.getChildAt(curPosition).findViewById(R.id.text);
                tv.setTextColor(tabSelectedColor);
                curTv.setTextColor(tabDefaultColor);
            }
            curPosition = position;
        }
    }

    public void showOrHideRedPoint(boolean isShow, int position) {
        if (!isDoubleTab && tabsContainer != null) {
            if (tabsContainer.getChildCount() > position) {
                View view = tabsContainer.getChildAt(position).findViewById(R.id.redpoint);
                view.setVisibility(isShow ? VISIBLE : INVISIBLE);
                invalidate();
            }
        }
    }

    public void setIconShowDirection(int iconShowDirection) {
        this.iconShowDirection = iconShowDirection;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = getResources().getColor(resId);
        invalidate();
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        invalidate();
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        invalidate();
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        invalidate();
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        invalidate();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        this.underlineHeight = underlineHeightPx;
        invalidate();
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        invalidate();
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        invalidate();
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }

    public boolean isTextAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean textAllCaps) {
        this.textAllCaps = textAllCaps;
    }

    public void setTextSize(int textSizePx) {
        this.tabTextSize = textSizePx;
        updateTabStyles();
    }

    public int getTextSize() {
        return tabTextSize;
    }

    public void setTextColor(int textColor) {
        this.tabTextColor = textColor;
        updateTabStyles();
    }

    public void setTextColorResource(int resId) {
        this.tabTextColor = getResources().getColor(resId);
        updateTabStyles();
    }

    public int getTextColor() {
        return tabTextColor;
    }

    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
    }

    public void setTabBackground(int resId) {
        this.tabBackgroundResId = resId;
    }

    public int getTabBackground() {
        return tabBackgroundResId;
    }

    public void setTabPaddingLeftRight(int paddingPx) {
        this.tabPadding = paddingPx;
        updateTabStyles();
    }

    public int getTabPaddingLeftRight() {
        return tabPadding;
    }

    public void setIsShowLine(boolean isShowLine) {
        this.isShowLine = isShowLine;
    }

    public void setIsDoubleTab(boolean isDoubleTab) {
        this.isDoubleTab = isDoubleTab;
    }

    public void setTabDefaultColor(int tabDefaultColor) {
        this.tabDefaultColor = tabDefaultColor;
    }

    public void setTabSelectedColor(int tabSelectedColor) {
        this.tabSelectedColor = tabSelectedColor;
    }

    public void setIsShowUnderLine(boolean isShowUnderLine) {
        this.isShowUnderLine = isShowUnderLine;
    }

    public void setIconIds(int[] iconIds) {
        this.iconIds = iconIds;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            super.onRestoreInstanceState(savedState.getSuperState());
            currentPosition = savedState.currentPosition;
            requestLayout();
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    static class SavedState extends BaseSavedState {
        int currentPosition;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

}
