package com.trian.component.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.ColorBackground
import com.trian.component.ui.theme.ColorFontSw


@Composable
fun CardDetailSmartWatch(
    param: String,
    valueHigh: Float,
    valueLow: Float,
    Satuan: String,
    onBackPress: ()-> Unit,
    onForwardPress : () -> Unit,
    onDatePick: () -> Unit,


    ){
    val hasilHigh : Float = when(param){
        "Temperature" -> valueHigh/45
        else -> 0f
    }
    val hasilLow : Float = when(param){
        "Temperature" -> valueLow/45
        else -> 0f
    }

    Box(modifier = Modifier
        .background(ColorBackground)
        .fillMaxSize())
    {

        Column {

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp
                )
                .height(200.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            )
            {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .fillMaxHeight()) {

                    Image(
                        painter = painterResource(id = com.trian.component.R.drawable.chart_dummy),
                        contentDescription = "dummy chart",
                        modifier = Modifier.fillMaxSize()

                    )

                }

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp
                    )
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = { onBackPress }) {
                        Icon(
                            Icons.Filled.ArrowBackIos,
                            contentDescription = "arrowback",
                            tint = ColorFontSw
                        )
                    }
                    Text(
                        text = "dd-mm-yy",
                        modifier = Modifier.clickable {onDatePick},
                        fontSize = 26.sp,
                        color = ColorFontSw
                    )
                    IconButton(onClick = { onForwardPress }) {
                        Icon(
                            Icons.Filled.ArrowForwardIos,
                            contentDescription = "forwarad",
                            tint = ColorFontSw
                        )

                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp
                    )
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            )
            {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(5.dp).height(IntrinsicSize.Min)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Lowest",
                                color = ColorFontSw,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = param,
                                color = ColorFontSw,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CircularProgresBar(
                            percentage = hasilLow,
                            number = 100,
                            satuan = Satuan,
                            value = valueLow.toString()
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(5.dp).fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Highest",
                                color = ColorFontSw,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = param,
                                color = ColorFontSw,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CircularProgresBar(
                            percentage = hasilHigh,
                            number = 100,
                            satuan = Satuan,
                            value = valueHigh.toString()
                        )

                    }
                }
            }


        }
    }
}


@Composable
fun CircularProgresBar(
    percentage: Float,
    number: Int,
    value: String,
    satuan: String,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Green,
    strokeWidth : Dp = 8.dp,
    animDuration : Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed: Boolean by remember {
        mutableStateOf(false)
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
        modifier = Modifier.size(radius*2f).padding(3.dp)) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = ColorFontSw,
                startAngle = -90f,
                sweepAngle = 360 * curPresentage.value,
                useCenter = false,
                style = Stroke(7.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Text(
                text = value,
                color = ColorFontSw,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = satuan,
                color = ColorFontSw,
                fontSize = 18.sp,
            )
        }

    }


}
