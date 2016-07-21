package com.example.wxq.wxqusefullibrary.widget.JustForTestWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/21.
 */
public class reactview extends View{


    public reactview(Context context) {
        super(context);
        init();
    }



    public reactview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public reactview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.FILL);//设置填充样式
        paint.setStrokeWidth(15);//设置画笔宽度

        canvas.drawRect(10, 10, 100, 100, paint);//直接构造

        RectF rect = new RectF(120, 10, 210, 100);
        canvas.drawRect(rect, paint);//使用RectF构造

        Rect rect2 =  new Rect(230, 10, 320, 100);
        canvas.drawRect(rect2, paint);//使用Rect构造
    }
}
