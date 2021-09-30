package com.trian.component.utils.ecg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CurveView extends View {
    private Paint paint = new Paint();
    private int centerHeight;
    private int valueAnimation;

    public CurveView(Context context) {
        super(context);
        init(null, 0);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight()/ 2f, getWidth(), getHeight()/2f, paint);

        for (int i = 0; i < getWidth() - valueAnimation; i = i + 10) {
            int startX = i;
            int startY = (int) (centerHeight + 100*(float)Math.sin(i/180.0*Math.PI));
            int endX = i + 10;
            int endY = (int) (centerHeight + 100*(float)Math.sin ((i + 10)/180.0*Math.PI));

            canvas.drawLine(startX, startY, endX, endY, paint);
        }
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        centerHeight = getHeight() / 2;

        ValueAnimator valueAnimatorLoading = ValueAnimator.ofInt(getWidth(), 0);
        valueAnimatorLoading.addUpdateListener(valueAnimator -> {
            valueAnimation = (int) (Integer) valueAnimator.getAnimatedValue();
            invalidate();
        });
        valueAnimatorLoading.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                System.out.println("On Finish");
            }
        });
        valueAnimatorLoading.start();
        valueAnimatorLoading.setDuration(3000);
    }
}