package com.trian.component.chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Component Services
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun CircularChart(){}

@Composable
fun CircularChartHealthStatus(
    percent:Float,
    number:Int,
    m: Modifier = Modifier,
){
    val currentPercentage = remember { Animatable(0.8f) }

    LaunchedEffect(percent) {
        currentPercentage.animateTo(
            percent,
            animationSpec = tween(durationMillis = 1000, delayMillis = 0)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = m.size(50.dp*2f)
    ){
        Canvas(modifier = m.size(50.dp*2f),){
            drawArc(
                color=Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage.value*number).toInt().toString(),
            color= Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}