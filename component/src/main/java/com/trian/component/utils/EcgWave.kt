package com.trian.component.utils

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.View
import androidx.compose.ui.geometry.Offset

/**
 * Ecg wave
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * Created At 27/09/2021
 */

class EcgWave(context:Context):View(context){

    private var paint = Paint()


    private var spikes = ArrayList<LinePoint>()
    private var datas = ArrayList<Float>()

    private var maxIndex =0

    private var maxSpikes = 0
    private var interval = 10f
    private var w=9f
    private var d =3f

    private var sw =0f

    private var sh = 700f

    private var startY=0f

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
        paint.strokeWidth = 4f

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
        datas.add(data)
        val maxDatas = datas.takeLast(maxSpikes)

        var startX = 0f
        var stopX= interval
        var stopY = 0f

        if(maxDatas.isNotEmpty()){
            stopY = maxDatas[0]
        }
        spikes.clear()

        for (i in maxDatas.indices){
            spikes.add(
                LinePoint(
                    start = Offset(startX, startY),
                    end = Offset(stopX, stopY)
                )
            )
            startX += interval
            startY = maxDatas[i]

            if ((i + 1) < maxDatas.size - 1) {
                stopX += interval
                stopY = maxDatas[i + 1]
            }

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

        drawBack(canvas = canvas)

        spikes.forEachIndexed { index, fl ->

            canvas?.drawLine(
                fl.start.x,
                fl.start.y,
                fl.end.x,
                fl.end.y,
                paint
        )

    }


    }

}

data class LinePoint(
    val start:Offset,
    val end:Offset
)