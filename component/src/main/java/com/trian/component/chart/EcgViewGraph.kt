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

/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 19/10/2021
 */
@Composable
fun EcgView(modifier: Modifier=Modifier,list:Float){
    AndroidView(
        modifier=modifier.fillMaxWidth().height(400.dp).clickable {  },
        factory = {
                  WaveformView(ContextThemeWrapper(it,R.style.Chart)).apply {
                      set_Yshrink(2)
                  }
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