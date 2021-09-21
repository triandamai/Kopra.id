package com.trian.waist.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.trian.component.cards.CardListDevice
import com.trian.component.chart.HorizontalCircularFeatures
import com.trian.component.ui.theme.*

@ExperimentalMaterialApi
@Preview
@Composable
fun WaistUiPreview(){
    TesMultiModuleTheme {
       WaistUi()
    }

}

@ExperimentalMaterialApi
@Composable
fun WaistUi(

){
    val spo2 :Float = 100f
    val analytic : String = "Normal"
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfile = {})
        },
        bottomBar = {
            CardListDevice(status = "Device" ,onClick = {})
        },
        backgroundColor = LightBackground

    ){
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp)
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
                        HorizontalCircularFeatures(
                            percentage = spo2/300,
                            radius = 60.dp,
                            value = spo2.toString(),
                            satuan = "Cm",
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
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp)
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
        }
    }

}


