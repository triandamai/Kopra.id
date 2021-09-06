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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularChart(){

}

@Composable
fun CircularChartHealthStatus(
    percent:Float,
    number:Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp =50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp =8.dp,
    animDuration:Int=1000,
    animDelay:Int=0,
    m: Modifier = Modifier,
){
    val currentPercentage = remember { Animatable(0.8f) }

    LaunchedEffect(percent) {
        currentPercentage.animateTo(
            percent,
            animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = m.size(radius*2f)
    ){
        Canvas(modifier = m.size(radius*2f),){
            drawArc(
                color=color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage.value*number).toInt().toString(),
            color= Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}