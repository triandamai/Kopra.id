package com.trian.component.chart

import android.annotation.SuppressLint

import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.marginTop
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.trian.component.R
import com.trian.component.utils.CustomChartMarker

/**
 * Base Chart
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 07/09/2021
 */
@SuppressLint("ResourceAsColor")
@Composable
fun BaseChartView(list:List<Entry>){
    AndroidView(
        modifier= Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = {
           LineChart(ContextThemeWrapper(it,R.style.Chart)).apply {
               setBackgroundResource(R.drawable.bg_chart)

               this.setPadding(
                   0,20,0,30
               )
               this.marginTop
               //disable axis
               xAxis.setDrawGridLines(false)
               xAxis.position = XAxis.XAxisPosition.BOTTOM


               axisRight.isEnabled = false
               axisRight.setDrawAxisLine(false)
               axisRight.setDrawGridLines(false)

               axisLeft.isEnabled = true
               axisLeft.setDrawAxisLine(false)
               axisLeft.setDrawGridLines(true)

               legend.isEnabled = false

               //Part5
               xAxis.labelRotationAngle = 0f



               //Part7
               axisRight.isEnabled = false
               xAxis.axisMaximum = 20+0.1f

               //Part8
               setTouchEnabled(true)
               setPinchZoom(true)

               //Part9
               description.text = "Days"
               setNoDataText("No Data to be shown!")

               //Part10
               animateX(1800, Easing.EaseInExpo)

               //add custom marker
                val markerView = CustomChartMarker(context,R.layout.layout_marker_chart)
                marker = markerView

                //set gradient line


               //Part3
               val vl = LineDataSet(list, "My Type")
               //make chart smooth
               vl.mode = LineDataSet.Mode.CUBIC_BEZIER
               vl.cubicIntensity = 0.1f
               //set transparency



               //
               vl.setColor(0xF06A50,1000)
               //set value in each circle
               vl.setDrawValues(false)
               //Part4 set color fill (area)
               vl.setDrawFilled(true)
               vl.setDrawCircles(true)

               vl.lineWidth = 3f

                vl.fillColor = 0xF06A50

               //remove circle
               vl.setDrawCircles(true)


               //Part6
               data = LineData(vl)

           }

        },
        update = {
            view->



            view.invalidate()
        }
    )
}

@Preview
@Composable
fun PreviewBaseChartView(){
    BaseChartView(list = arrayListOf<Entry>())
}