package com.example.wxq.wxqusefullibrary.widget.JustForTestWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wxq on 2016/8/4 0004.
 */
public class CustomView extends View implements Runnable {

    private Paint mPaint;
    private Context mcontext;
    private int radiu;

    public CustomView(Context context) {
        this(context, null);
    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(300, 300, radiu, mPaint);

    }

    @Override
    public void run() {
        while (true) {
            if (radiu <= 200) {
                radiu += 10;


                postInvalidate();//线程中通知重新绘制view


            } else {

                radiu = 0;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
