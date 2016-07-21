package com.example.wxq.wxqusefullibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2016/7/21.
 */
public class CustomView1  extends View {

    Context m_context;
    public CustomView1(Context context){
        super(context);
        m_context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔
        Paint paint=new Paint( );
        paint.setAntiAlias(true);
paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.setStrokeWidth(5);
        paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
        canvas.drawRGB(255,255,255);
        canvas.drawCircle(90,100,50,paint);//花园


    }
}
