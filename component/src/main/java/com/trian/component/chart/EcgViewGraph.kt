package com.trian.component.chart

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.util.Log
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.trian.component.R
import com.trian.component.utils.*
import com.trian.component.utils.ecg.WaveformView
import com.trian.domain.models.EcgWaveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread



@Composable
fun ECGGraph(list:List<Entry>,scope:CoroutineScope){
   AndroidView(
       modifier=Modifier.fillMaxSize(),
       factory = {
                    LineChart(ContextThemeWrapper(it,R.style.Chart)).apply {
                        setDrawGridBackground(false)
                        description.isEnabled = false

                        setTouchEnabled(false)
                        setScaleEnabled(false)
                        scaleY = 1.0f

                        setPinchZoom(false)

                        axisLeft.setDrawGridLines(false)
                        isEnabled=false
                        xAxis.setDrawGridLines(false)
                        xAxis.setDrawAxisLine(false)
    }
   }, update = {

           scope.launch(context = Dispatchers.IO){
               delay(1000)
               //it.resetTracking()
               val set = LineDataSet(list,"ECG")
               set.setColor(android.graphics.Color.RED)
               set.lineWidth=1.0f
               set.setDrawValues(false)
               set.mode=LineDataSet.Mode.LINEAR
               set.setDrawFilled(false)
               val data =LineData (set)
               it.data = data
               it.setVisibleYRange(-128f,127f,null);
               it.legend.isEnabled = false
             it.invalidate()
           }

   })
}

//@Composable
//fun EcgView(modifier: Modifier=Modifier){
//    AndroidView(
//      factory = {
//        RallyLineGraphChart(ContextThemeWrapper(it,R.style.Chart))
//    },
//        update = {
//            it.addDataPoints(listOf(
//                DataPoint(10f),
//                DataPoint(30f),
//                DataPoint(50f),
//                DataPoint(20f),
//                DataPoint(40f),
//            ))
//            it.invalidate()
//        }
//    )
//}

@Composable
fun EcgView(modifier: Modifier=Modifier,list:Float){
    AndroidView(
        modifier=modifier.fillMaxWidth().height(400.dp).clickable {  },
        factory = {
            //EcgWave(ContextThemeWrapper(it,R.style.Chart))
                  WaveformView(ContextThemeWrapper(it,R.style.Chart))
        },
        update = {
            it.set_data(list.toInt())
            it.invalidate()
        }
    )
}
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewEcgGraph(){
   // EcgViewGraph()

    EcgView(list = 100f)
}