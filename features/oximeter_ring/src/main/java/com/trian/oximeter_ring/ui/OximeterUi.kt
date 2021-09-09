package com.trian.oximeter_ring.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.appbar.AppBarFeature
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme


@ExperimentalMaterialApi
@Preview
@Composable
fun OximeterUiPreview(){
    TesMultiModuleTheme {
        OximeterUi()
    }

}

@ExperimentalMaterialApi
@Composable
fun OximeterUi(

){
    val spo2 :Float = 100f
    val analytic : String = "Normal"
    Column(
        modifier = Modifier
            .background(LightBackground)
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(200.dp)
                    .background(color = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularValueOxi(
                            percentage = spo2/100,
                            radius = 60.dp,
                            value = spo2.toUInt().toString(),
                            satuan = "%",
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Divider(
                        color = ColorFontFeatures,
                        modifier = Modifier
                            .fillMaxHeight(0.72f)
                            .width(2.dp),
                        thickness = 3.dp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = analytic,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                }

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color.White)
                    .height(250.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_chart),
                    contentDescription = "dummy chart",
                    modifier = Modifier.fillMaxSize()

                )

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(100.dp)
                    .background(color = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                onClick = {/*todo*/},
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight()
                            .background(Color(0xFFF395BA))
                    ) {

                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = 10.dp,
                                horizontal = 10.dp,
                            )
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Device Connected",
                                fontSize = 12.sp,
                                color = Color(0xFF6E798C)
                            )
                            Text(
                                text = "17 day ago",
                                fontSize = 12.sp,
                                color = Color(0xFF6E798C)
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Microlife A78",
                            fontSize = 20.sp,
                            color = Color(0xFF081F32)
                        )
                    }
                }

            }
        }

    }

}



@Composable
fun CircularValueOxi(
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
