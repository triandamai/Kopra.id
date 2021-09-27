package com.trian.component.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.trian.component.R;
import com.trian.domain.models.EcgWaveData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zandongdong on 2018/1/3.
 */

public class EcgSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    protected Paint mPath;
    protected int mWidth, mHeight;
    protected float scale;
    //背景画笔
    protected Paint mPaint;
    //画背景标签
    protected Paint mTagPaint;
    //画字体背景
    protected Paint mTextPaint;
    //网格颜色
    protected int mGridColor = Color.parseColor("#FF9999");
    //小网格颜色
    protected int mSGridColor = Color.parseColor("#CC6699"); //FFB5C5
    //背景颜色
    protected int mBackgroundColor = Color.WHITE;
    //网格宽度
    protected float mXGridWidth;//固定为5毫米(X轴5毫米距离像素点个数)
    protected float mYGridWidth;//固定为5毫米(Y轴5毫米距离像素点个数)
    //小网格的宽度
    protected int mSGridWidth = 10;
    protected boolean isTransparentModeOpen = false;
    ArrayList<Integer> data = new ArrayList<>();
    Map<Integer, ArrayList<Float>> maps = new LinkedHashMap<>();
    int index = 0;//最近的数据在数组中的索引
    int maxLines;//一屏幕最多的线段数量
    float xaxis;//x轴单位长度
    float yaxis;//y轴单位长度
    float x = 2f;//x轴单位长度系数
    float y = 1f;//y轴单位长度系数
    int waveSize = 10;//ecg波个数
    int waveHeight;//单个波的高度
    int waveMargin;//波的间隔
    int waveMargin1;//波的间隔
    private Context context;
    // SurfaceHolder
    private SurfaceHolder mHolder;
    // 用于绘图的Canvas
    private Canvas mCanvas;
    // 子线程标志位
    public boolean mIsDrawing = false;
    private boolean mIsDestroyed = false;
    private float xPixelPerMm;//x轴每毫米像素点个数
    private float xdpi;//x轴横向屏幕像素密度
    private float xSpeed = 25f;//走速默认25
    private float sampleRate = 250;//采样率
    private float ydpi;//y轴纵向向屏幕像素密度
    private float yPixelPerMm;//y轴每毫米像素点个数
    private float range;//每毫伏mv幅度(单位像素)
    private float enhance = 10;//y轴增益默认10
    private float zoom = 4.900000f / 1000f;//y轴放大倍数 用来和硬件1mv波形比例对应，所有的y点都需要乘这个比例0.0049
    private float mv;//毫伏
    private int transparentPoints;//透明点个数

    public String labelText = "II";

    public EcgSurfaceView(Context context) {
        super(context);
        this.context = context;
        initView();

    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);


        DisplayMetrics metric = new DisplayMetrics();
        Context ctx = ((ContextWrapper) context).getBaseContext();
        ((Activity) ctx).getWindowManager().getDefaultDisplay().getRealMetrics(metric);


        scale = metric.density;
        xdpi = metric.xdpi;
        ydpi = metric.ydpi;
        xPixelPerMm = xdpi / 25.4f;
        yPixelPerMm = ydpi / 25.4f;

        mXGridWidth = xPixelPerMm * 5f;
        mYGridWidth = yPixelPerMm * 5f;


        mPath = new Paint();
        mPath.setColor(Color.parseColor("#565756"));
        mPath.setStrokeWidth(2.0f);
        mPath.setStyle(Paint.Style.STROKE);
        mPath.setAntiAlias(true);
        mPath.setDither(true);
        mPath.setStrokeCap(Paint.Cap.ROUND);
        mPath.setStrokeJoin(Paint.Join.ROUND);

        waveHeight = context.getResources().getDimensionPixelSize(R.dimen.wave_height);
        waveMargin = context.getResources().getDimensionPixelSize(R.dimen.wave_margin);
        waveMargin1 = context.getResources().getDimensionPixelSize(R.dimen.wave_margin1);


        xaxis = xSpeed / sampleRate * xPixelPerMm;
        range = yPixelPerMm * enhance * zoom;


        transparentPoints = (int) ((2 * xPixelPerMm) / xaxis + 0.5f);


        mPaint = new Paint();
        mPaint.setColor(mGridColor);
        mPaint.setStrokeWidth(2f);


        mTagPaint = new Paint();
        mTagPaint.setColor(Color.parseColor("#565756"));
        mTagPaint.setStrokeWidth(2f);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.parseColor("#565756"));
        mTextPaint.setTextSize(20);
    }


    public EcgSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();


    }

    public EcgSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EcgSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();


    }

    public void switchXspeed(float value) {
        mIsDrawing = false;

        index = 0;
        maps.clear();
        xaxis = value / sampleRate * xPixelPerMm;
        maxLines = (int) (mWidth / xaxis);
        transparentPoints = (int) ((2 * xPixelPerMm) / xaxis + 0.5f);

        mIsDrawing = true;
    }
    /*public void switchLead(int position,Boolean isFirst) {
        mIsDrawing = false;
        final String[] xSpeedValue1 = getResources().getStringArray(R.array.ecg_speed1);
        final String[] xSpeed1 = new String[xSpeedValue1.length];
        index = 0;
        maps.clear();
        DeviceUtil.posion = position;
        if (!isFirst) {
            DevManager.getInstance().sendLeadSelectionCommand((position + 1));
        } else {
            isFirst = false;
        }
        labelText = xSpeed1[position];

        mIsDrawing = true;
    }*/

    public void switchEnhance(float value) {
        mIsDrawing = false;


        index = 0;
        maps.clear();
        enhance = value;
        range = yPixelPerMm * value * zoom;


        mIsDrawing = true;

    }

    int dis = 0;

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height = waveSize * waveHeight
                + (waveSize - 1) * waveMargin;

        setMeasuredDimension(width, height - dis);

    }*/

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        maxLines = (int) (mWidth / xaxis);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDestroyed = false;
        drawBackWhenCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mIsDestroyed) {
                    if (mIsDrawing) {
                        draw();
                    }
                    SystemClock.sleep(10 * 5);
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDestroyed = true;

    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            drawCanvas(mCanvas);
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
            mIsDrawing = false;
        }
    }

    public void setTransparentMode(boolean open) {


        isTransparentModeOpen = open;
    }
    public float getMultiple( Context context) {
        return context.getResources().getDisplayMetrics().density*0.5f;
    }
    private void drawCanvas(Canvas canvas) {


        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        if (isTransparentModeOpen) return;

        drawBack(canvas);//画背景
        if (maps.size() > 0) {
            for (ArrayList<Float> data : maps.values()) {
                if (data.size() > 0) {
                    //    画搏动折线


                    for (int k = 0; k < data.size(); k++) {

                        if (data.size() >= maxLines) {
                            //透明点个数
                            if (k <= index && k >= index - transparentPoints) {
                                mPath.setColor(Color.TRANSPARENT);
                                canvas.drawLine((k - 1) * xaxis, data.get(k - 1), k * xaxis, data.get(k)/10, mPath);
                            } else {
                                mPath.setColor(Color.parseColor("#565756"));
                                canvas.drawLine((k - 1) * xaxis, data.get(k - 1), k * xaxis, data.get(k)/10, mPath);
                            }
                        } else {
                            canvas.drawLine((k - 1) * xaxis, data.get(k - 1), k * xaxis, data.get(k)/10, mPath);
                        }

                    }
                }

                break;//
            }
        }
    }

    public void clearData() {
        mIsDrawing = false;
        index = 0;
        maps.clear();
        mIsDrawing = true;
    }

    public void clearWave() {

        mIsDrawing = false;

        drawBackWhenCreate();

    }

    private void drawBackWhenCreate() {
        mCanvas = mHolder.lockCanvas();
        drawBack(mCanvas);
        if (mCanvas != null) {
            mHolder.unlockCanvasAndPost(mCanvas);
        }

    }

    private void drawBack(Canvas canvas) {

        if (canvas == null) {
            return;
        }
        canvas.drawColor(mBackgroundColor);
//        //画小网格
//
//        //竖线个数
//        int vSNum = mWidth / mSGridWidth;
//
//        //横线个数
//        int hSNum = mHeight / mSGridWidth;
//        mPaint.setColor(mSGridColor);
//        mPaint.setStrokeWidth(2);
//        //画竖线
//        for (int i = 0; i < vSNum + 1; i++) {
//            canvas.drawLine(i * mSGridWidth, 0, i * mSGridWidth, mHeight, mPaint);
//        }
//        //画横线
//        for (int i = 0; i < hSNum + 1; i++) {
//
//            canvas.drawLine(0, i * mSGridWidth, mWidth, i * mSGridWidth, mPaint);
//        }

        //竖线个数
        int vNum = (int) (mWidth / mXGridWidth + 0.5f);
        //横线个数
        int hNum = (int) (mHeight / mYGridWidth + 0.5f);


        //画竖线
        for (int i = 0; i < vNum + 1; i++) {
            if (i != 0) {
                mPaint.setColor(Color.parseColor("#F7F7F7"));
                for (int mm = 1; mm <= 4; mm++) {
                    canvas.drawLine(i * mXGridWidth - (mm / 5.0f) * mXGridWidth, 0, i * mXGridWidth - (mm / 5.0f) * mXGridWidth, mHeight, mPaint);
                }
            }


        }
        //画横线
        for (int i = 0; i < hNum + 1; i++) {
            if (i != 0) {
                mPaint.setColor(Color.parseColor("#F7F7F7"));
                for (int mm = 1; mm <= 4; mm++) {
                    canvas.drawLine(0, i * mYGridWidth - (mm / 5.0f) * mXGridWidth, mWidth, i * mYGridWidth - (mm / 5.0f) * mXGridWidth, mPaint);
                }
            }
            mPaint.setColor(mGridColor);
            canvas.drawLine(0, i * mYGridWidth, mWidth, i * mYGridWidth, mPaint);

        }

        mPaint.setColor(mGridColor);
        //画竖线
        for (int i = 0; i < vNum + 1; i++) {
            canvas.drawLine(i * mXGridWidth, 0, i * mXGridWidth, mHeight, mPaint);

        }


        //画左上角标签


        ArrayList<Integer> leads = new ArrayList<>();
        leads.addAll(maps.keySet());
        for (int i = 0; i < leads.size(); i++) {
            if (i == 0) {
                drawTag(canvas, 0, leads.get(i));
            } else {
//                drawTag(canvas, getBaseLineHeight(leads.get(i - 1)), leads.get(i));
            }
        }

    }

    private void drawTag(Canvas canvas, float y, int lead) {
        int position = findLocation(lead);


        float x = 0;

        if (enhance == 2.5f) {
            x = 1f;
        } else if (enhance == 5.0f) {
            x = 1f;

        } else if (enhance == 10.0f) {
            x = 1f;

        } else if (enhance == 20.0f) {
            x = 2f;

        } else if (enhance == 40.0f) {
            x = 3.2f;

        }

        //画左上角标签
        float lineHeight = 50;
        float marginTop = 50 * x;
        float marginLeft = 20;
        if (position == 0) {
            marginTop = 60;
        }


        String text = "";

        switch (lead) {
            case 0:
                text = "I";
                break;
            case 1:
                text = "II";
                break;
            case 2:
                text = "III";
                break;
            case 3:
                text = "avR";
                break;
            case 4:
                text = "avL";
                break;
            case 5:
                text = "avF";
                break;
            case 6:
                text = "V2";
                break;
            case 7:
                text = "V5";
                break;
        }
//marginTop + y + lineHeight水平点  marginTop - 40 + y + lineHeight最高
//        Log.d("drawTag", "drawTag: " + yPixelPerMm);


        /*canvas.drawLine(marginLeft,marginTop+y+lineHeight,5+marginLeft,marginTop+y+lineHeight,mTagPaint);
        canvas.drawLine(5+marginLeft,marginTop+y+lineHeight,5+marginLeft,marginTop-40+y+lineHeight,mTagPaint);
        canvas.drawLine(5+marginLeft,marginTop-40+y+lineHeight,15+marginLeft,marginTop-40+y+lineHeight,mTagPaint);
        canvas.drawLine(15+marginLeft,marginTop-40+y+lineHeight,15+marginLeft,marginTop+y+lineHeight,mTagPaint);
        canvas.drawLine(15+marginLeft,marginTop+y+lineHeight,20+marginLeft,marginTop+y+lineHeight,mTagPaint);
        canvas.drawText(text,20+marginLeft,marginTop+y+lineHeight,mTextPaint);*/

        float ra = 0;

        if (enhance == 2.5f) {
            ra = 2.5f;
        } else if (enhance == 5.0f) {
            ra = 3f;

        } else if (enhance == 10.0f) {
            ra = 4f;

        } else if (enhance == 20.0f) {
            ra = 5f;

        } else if (enhance == 40.0f) {
            ra = 6f;
        }
        int dis = 30;
        int dis1 = 100;


        float height = 0;

        if (enhance == 2.5f) {
            height = mYGridWidth * 0.5f;
        } else if (enhance == 5.0f) {
            height = mYGridWidth * 1;

        } else if (enhance == 10.0f) {
            height = mYGridWidth * 2;

        } else if (enhance == 20.0f) {
            height = mYGridWidth * 4;

        } else if (enhance == 40.0f) {
            height = mYGridWidth * 8;
        }

        float base = 10;

        if (lead == 6) {
            base = 10;
        }

        if (lead == 7) {
            base = getBaseLineHeight(lead) - waveHeight / 2;
        }
//        Log.d("yyy", "drawTag: "+height);
        canvas.drawLine(marginLeft, base + height, 5 + marginLeft, base + height, mTagPaint);
        canvas.drawLine(5 + marginLeft, base + height, 5 + marginLeft, base, mTagPaint);
        canvas.drawLine(5 + marginLeft, base, 15 + marginLeft, base, mTagPaint);
        canvas.drawLine(15 + marginLeft, base, 15 + marginLeft, base + height, mTagPaint);
        canvas.drawLine(15 + marginLeft, base + height, 20 + marginLeft, base + height, mTagPaint);
        canvas.drawText(labelText, 20 + marginLeft, base + height, mTextPaint);

/*
        canvas.drawLine(marginLeft, marginTop + y + lineHeight+dis1, 5 + marginLeft, marginTop + y + lineHeight+dis1, mTagPaint);
        canvas.drawLine(5 + marginLeft, marginTop + y + lineHeight+dis1, 5 + marginLeft, marginTop - (int)(ra*yPixelPerMm)+dis + y + lineHeight+dis1, mTagPaint);
        canvas.drawLine(5 + marginLeft, marginTop - (int)(ra*yPixelPerMm)+dis + y + lineHeight+dis1, 15 + marginLeft, marginTop -(int)(ra*yPixelPerMm) +dis+ y + lineHeight+dis1, mTagPaint);
        canvas.drawLine(15 + marginLeft, marginTop -(int)(ra*yPixelPerMm)+dis+ y + lineHeight+dis1, 15 + marginLeft, marginTop + y + lineHeight+dis1, mTagPaint);
        canvas.drawLine(15 + marginLeft, marginTop + y + lineHeight+dis1, 20 + marginLeft, marginTop + y + lineHeight+dis1, mTagPaint);
        canvas.drawText(text, 20 + marginLeft, marginTop + y + lineHeight+dis1, mTextPaint);
*/

        /*if(lead!=0){
            Rect rect=new Rect(0,(int)(marginTop - ra*yPixelPerMm+dis + y + lineHeight-mYGridWidth+yPixelPerMm*8),mWidth,(int)(marginTop - ra*yPixelPerMm +dis+ y + lineHeight-3));
            Paint paint=new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawRect(rect,paint);
        }*/
    }

    public float getBaseLineHeight(int key) {
        int position = findLocation(key);


        int dis = 200;

        if (enhance == 2.5f) {
            dis = 50;
        } else if (enhance == 5.0f) {
            dis = 100;

        } else if (enhance == 10.0f) {
            dis = 200;

        } else if (enhance == 20.0f) {
            dis = 400;

        } else if (enhance == 40.0f) {
            dis = 700;

        }
/*
        if (enhance == 2.5f) {
            x = 1.5f;
        } else if (enhance == 5.0f) {
            x = 1.5f;

        } else if (enhance == 10.0f) {
            x = 1.5f;

        } else if (enhance == 20.0f) {
            x = 3f;

        } else if (enhance == 40.0f) {
            x = 6f;

        }
*/

      /*  if (key == 7) {
            return waveHeight + dis;
        } else if (key == 6) {
            return waveHeight / 2;
        } else {
            return (position + 1) * (waveHeight / 2) + position * waveMargin * x;
        }*/
        return mHeight / 2;
//        return (position + 1) * (waveHeight / 2) + position * waveMargin * x;
//        return (position + 1) * (waveHeight / 2) + position * waveMargin + (position) * enhance * x;
    }

    private int findLocation(int key) {

        int position = 0;

        for (int a : maps.keySet()) {
            if (a == key) {
                return position;
            }
            position++;
        }

        return position;

    }

    public void notifyData(List<EcgWaveData> data) {
        waveSize = data.size();

        int tempType = -1;
        int tempIndex = index;

        for (EcgWaveData bean : data) {
            if (tempType == -1) {
                tempType = bean.getType();
            }

            int key = bean.getType();

//            Log.d("yyy", yPixelPerMm+":notifyData: "+bean.value+":"+enhance);
//            float value = -((bean.value) /110.0f* yPixelPerMm * enhance*2 );
            float value = -((bean.getValue()) * yPixelPerMm * enhance * zoom);

            if (tempType != key) {
                index = tempIndex;
                tempType = key;

            }

            if (maps.get(key) == null) {
                ArrayList<Float> list = new ArrayList<>();
                list.add(getBaseLineHeight(key) + value);
                maps.put(key, list);
            } else {
                ArrayList<Float> list = maps.get(key);
                if (list.size() >= maxLines) {
                    list.remove(index);
                    list.add(index, getBaseLineHeight(key) + value);
                } else {
                    list.add(getBaseLineHeight(key) + value);
                }
            }
            index++;
            if (index >= maxLines) {
                index = 0;
            }
        }

        /*if(maps.size()==3){
            dis=600;
        }
        if(maps.size()==4){
            dis=500;
        }
        if(maps.size()==5){
            dis=500;
        }*/
        mIsDrawing = true;
    }

}
