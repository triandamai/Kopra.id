package com.trian.component.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Ecg wave
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * Created At 27/09/2021
 */

class SoundWave(context:Context):View(context){

    private var paint = Paint()
    private var datas = ArrayList<Float>()

    private var spikes = ArrayList<RectF>()

    private var maxSpikes = 0

    private var radius = 6f
    private var w=9f
    private var d =6f

    private var sw =0f

    private var sh = 400f


    var scale = 0f

    private var xPixelPerMm //x轴每毫米像素点个数
            = 0f
    private var xdpi //x轴横向屏幕像素密度
            = 0f
    private var mGridColor = Color.parseColor("#FF9999")

    private var ydpi //y轴纵向向屏幕像素密度
            = 0f
    private var yPixelPerMm //y轴每毫米像素点个数
            = 0f
    private var mXGridWidth //固定为5毫米(X轴5毫米距离像素点个数)
            = 0f
    private var mYGridWidth //固定为5毫米(Y轴5毫米距离像素点个数)
            = 0f

    init {
        paint.color = Color.BLUE//Color.rgb(244,81,30)

        sw = resources.displayMetrics.widthPixels.toFloat()

        maxSpikes = (sw/(w+d)).toInt()

        scale = resources.displayMetrics.density
        xdpi = resources.displayMetrics.xdpi
        ydpi = resources.displayMetrics.ydpi
        xPixelPerMm = xdpi / 25.4f
        yPixelPerMm = ydpi / 25.4f

        mXGridWidth = xPixelPerMm * 5f
        mYGridWidth = yPixelPerMm * 5f
    }

    fun addData(data:Float){
        var normalize = Math.min(data.toInt()/7,400).toFloat()
        datas.add(normalize)

        spikes.clear()
        var maxDatas = datas.takeLast(maxSpikes)
        for (i in maxDatas.indices){
            var left = sw-i*(w+d)
            var top = sh/2 - maxDatas[i]/2
            var right = left + w

            var bottom = top + maxDatas[i]

            spikes.add(RectF(left,top,right,bottom))
        }

        invalidate()
    }

    private fun drawBack(canvas: Canvas?) {
        if (canvas == null) {
            return
        }
        canvas.drawColor(Color.WHITE)


        //竖线个数
        val vNum = (sw / mXGridWidth + 0.5f).toInt()
        //横线个数
        val hNum = (sh / mYGridWidth + 0.5f).toInt()


        var mPaint = Paint()
        //画竖线
        for (i in 0 until vNum + 1) {
            if (i != 0) {
                mPaint.setColor(Color.parseColor("#F7F7F7"))
                for (mm in 1..4) {
                    canvas.drawLine(
                        i * mXGridWidth - mm / 5.0f * mXGridWidth,
                        0f,
                        i * mXGridWidth - mm / 5.0f * mXGridWidth,
                        sh,
                        mPaint
                    )
                }
            }
        }
        //画横线
        for (i in 0 until hNum + 1) {
            if (i != 0) {
                mPaint.setColor(Color.parseColor("#F7F7F7"))
                for (mm in 1..4) {
                    canvas.drawLine(
                        0f,
                        i * mYGridWidth - mm / 5.0f * mXGridWidth,
                        sw,
                        i * mYGridWidth - mm / 5.0f * mXGridWidth,
                        mPaint
                    )
                }
            }
            mPaint.setColor(mGridColor)
            canvas.drawLine(0f, i * mYGridWidth, sw, i * mYGridWidth, mPaint)
        }
        mPaint.setColor(mGridColor)
        //画竖线
        for (i in 0 until vNum + 1) {
            canvas.drawLine(i * mXGridWidth, 0f, i * mXGridWidth,sh, mPaint)
        }


//        //画左上角标签
//        val leads = java.util.ArrayList<Int>()
//        leads.addAll(maps.keys)
//        for (i in leads.indices) {
//            if (i == 0) {
//                drawTag(canvas, 0f, leads[i])
//            } else {
////                drawTag(canvas, getBaseLineHeight(leads.get(i - 1)), leads.get(i));
//            }
//        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("ONDRAW",spikes.toString())

        drawBack(canvas = canvas)


        spikes.forEach {
            canvas?.drawRoundRect(
                it,radius,radius,paint
            )
        }



    }
}