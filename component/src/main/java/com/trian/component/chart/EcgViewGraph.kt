package com.trian.component.chart

import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.Canvas
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
import com.trian.component.R
import com.trian.component.utils.DataPoint
import com.trian.component.utils.ECGView
import com.trian.component.utils.RallyLineGraphChart

@Composable
fun EcgViewGraph(modifier:Modifier=Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){

        AndroidView(factory = {
            ECGView(
                context = ContextThemeWrapper(it, R.style.Chart)
            )
        }){}
    }
}

@Composable
fun EcgView(modifier: Modifier=Modifier){
    AndroidView(
      factory = {
        RallyLineGraphChart(ContextThemeWrapper(it,R.style.Chart))
    },
        update = {
            it.addDataPoints(listOf(
                DataPoint(10f),
                DataPoint(30f),
                DataPoint(50f),
                DataPoint(20f),
                DataPoint(40f),
            ))
            it.invalidate()
        }
    )
}

@Preview
@Composable
fun PreviewEcgGraph(){
   // EcgViewGraph()

    EcgView()
}