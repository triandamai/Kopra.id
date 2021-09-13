package com.trian.component.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.trian.component.R

/**
 * `Custom Marker`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 08/09/2021
 */
@SuppressLint("ViewConstructor")
class CustomChartMarker(context: Context, layoutResource:Int):MarkerView(context,layoutResource) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val value = e?.y?.toDouble() ?: 0.0
        var resText = ""
        resText = if(value.toString().length > 8){
            "Val : "+value.toString().substring(0,7)
        }else{
            "Val : $value"
        }

        val tv = findViewById<TextView>(R.id.tvValue)
        tv.text = resText
        super.refreshContent(e, highlight)
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        return MPPointF(-width/2f,-height - 10f)
    }
}