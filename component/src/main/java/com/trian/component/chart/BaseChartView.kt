package com.trian.component.chart

import android.annotation.SuppressLint

import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.trian.common.utils.utils.XAxisTimeFormatter

import com.trian.component.R
import com.trian.component.utils.CustomChartMarker
import java.time.format.DateTimeFormatter

/**
 * Base Chart
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 07/09/2021
 */
@SuppressLint("ResourceAsColor", "NewApi")
@Composable
fun BaseChartView(data:List<Entry>, description: String, maxAxis:Float=200f, minAxis:Float=10f){

    AndroidView(
        modifier= Modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = {
           LineChart(ContextThemeWrapper(it,R.style.Chart)).apply {
               setBackgroundResource(R.drawable.bg_chart)

               axisRight.apply {
                   isEnabled = false
                   setDrawAxisLine(false)
                   setDrawGridLines(false)
               }


               axisLeft.apply {
                   axisMaximum = maxAxis
                   axisMinimum = minAxis
                   //formatter
                   isEnabled = true
                   setDrawAxisLine(false)
                   setDrawGridLines(true)


               }
               xAxis.apply {
                   //disable axis
                   setDrawGridLines(false)
                   position = XAxis.XAxisPosition.BOTTOM
                   //
                   labelRotationAngle = 0f

                   granularity = 1f

                   axisMaximum = 20+0.1f
                   //

               }

               this.description.text = description
               this.description.textSize = 16f
               this.setPadding(
                   0,20,0,30
               )

               legend.apply {
                   isEnabled = false
               }
               //Part8
               setTouchEnabled(true)
               setPinchZoom(true)

               //Part9

               setNoDataText("No Data to be shown!")

               //Part10
               animateX(1800, Easing.EaseInExpo)

               //add custom marker
                val markerView = CustomChartMarker(context,R.layout.layout_marker_chart)
                marker = markerView

           }

        },
        update = {
            view->

            val lineDataSet = LineDataSet(data, "My Type")
            lineDataSet.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.1f
                //set transparency
                setColor(0xF06A50,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(true)

                lineWidth = 3f

                fillColor = 0xF06A50

                //remove circle
                setDrawCircles(true)
            }


            //set data
            view.data = LineData(lineDataSet)

            view.invalidate()
        }
    )
}

@Preview
@Composable
fun PreviewBaseChartView(){
    BaseChartView(data = arrayListOf<Entry>(), description = "Test")
}