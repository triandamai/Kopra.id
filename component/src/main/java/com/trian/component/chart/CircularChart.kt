package com.trian.component.chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.*

/**
 * Component Circular Chart
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */

@Composable
fun CircularProgressBar(
    percentage: Float,
    value: String,
    unit: String,
    radius: Dp = 80.dp,
    animDuration : Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed: Boolean by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
            .padding(3.dp)) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = ColorFontFeatures,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(
            modifier = Modifier.size(radius * 2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                color = ColorFontFeatures,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = unit,
                color = ColorFontFeatures,
                fontSize = 16.sp,
            )
        }

    }


}
@Composable
fun CircularChartHealthStatus(
    result:String="Normal",
    percent:Float,
    number:Int,
    modifier: Modifier = Modifier,
){
    val currentPercentage = remember { Animatable(0.01f) }

    LaunchedEffect(percent) {
        currentPercentage.animateTo(
            percent,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1300)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(50.dp*2f)
    ){
        Canvas(modifier = modifier.size(50.dp*2f),){
            rotate(90f){
                drawArc(
                    color=Color.Gray.copy(alpha = 0.2f),
                    startAngle = 3f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(2.dp.toPx(),
                        cap = StrokeCap.Round)
                )
                drawArc(
                    /**
                     * there ara 4 common color in health meter
                     * green
                     * soft green
                     * yellow
                     * orange
                     * red
                     * **/
                    brush = Brush.sweepGradient(
                        colors=listOf(
                            BluePrimary,
                            GreenPrimary,
                            YellowPrimary,
                            OrangePrimary,
                            RedPrimary,
                        ),
                    ),
                    startAngle = 3f,
                    sweepAngle = 360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(8.dp.toPx(),
                        cap = StrokeCap.Round)
                )
            }


        }
        Text(
            text = result,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
            color= Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun Preview1(){
    CircularProgressBar(
        percentage = 20f,
        value = "23",
        unit = "Kg",
        radius = 90.dp
    )
}

@Preview
@Composable
fun Preview2(){
    CircularChartHealthStatus(
        percent =0.2f,
        number = 40
    )
}



@Composable
fun HorizontalCircularFeatures(
    percentage: Float,
    value: String,
    satuan: String,
    radius: Dp,
    animDuration : Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed: Boolean by remember {
        mutableStateOf(true)
    }
    val curPresentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
            .padding(3.dp)) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = ColorFontFeatures,
                startAngle = -90f,
                sweepAngle = 360 * curPresentage.value,
                useCenter = false,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Row(
            modifier = Modifier.size(radius * 2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                color = ColorFontFeatures,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = satuan,
                color = ColorFontFeatures,
                fontSize = 16.sp,
            )
        }

    }

}


@Composable
fun VerticalCircularFeatures(
    percentage: Float,
    value: String,
    satuan: String,
    radius: Dp,
    animDuration : Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed: Boolean by remember {
        mutableStateOf(true)
    }
    val curPresentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
            .padding(3.dp)) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = ColorFontFeatures,
                startAngle = -90f,
                sweepAngle = 360 * curPresentage.value,
                useCenter = false,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(
            modifier = Modifier.size(radius * 2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                color = ColorFontFeatures,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = satuan,
                color = ColorFontFeatures,
                fontSize = 16.sp,
            )
        }

    }


}
