package com.trian.component.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gaoyingjie on 2019/9/17
 * Description:
 */
public class GridView extends View {

    private int width, height;
    private Paint paint;

    //private int col = 40;//列

    public GridView(Context context) {
        this(context, null);
    }

    public GridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //col = this.width/20;
        drawGird(canvas, Color.parseColor("#D7D7D7"));

    }

    private void drawGird(Canvas canvas, int color) {
        paint.setColor(color);
        paint.setStrokeWidth(2);
        //float colSpace = width * 1f / cols;
        int colSpace = 20;
        int cols = this.width / colSpace;
        int heightCount = (int) Math.floor((double) height / 20);//向下取整
        float space = (this.height - heightCount * 20) / 2;
        //画竖线
        for (int i = 0; i <= this.width / 20; i++) {
            canvas.drawLine(i * colSpace, space, i * colSpace, heightCount * colSpace + space, paint);
        }


        //画横线
        for (int i = 0; i <= heightCount; i++) {
            canvas.drawLine(0, i * colSpace + space, width, i * colSpace + space, paint);
        }

//        //画横线上半部分
//        for (int i = centreY;i>0;i -= colSpace){
//            canvas.drawLine(0,i,width,i,paint);
//        }
//        //画横线下半部分
//        for (int i = centreY;i<height;i += colSpace){
//            canvas.drawLine(0,i,width,i,paint);
//        }
        //paint.setStrokeWidth(4);
//        canvas.drawLine(0,0,width,0,paint);//上
//        canvas.drawLine(0,height,width,height,paint);//下
//        canvas.drawLine(0,0,0,height,paint);//左
//        canvas.drawLine(width,0,width,height,paint);//右
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        invalidate();

    }
}
