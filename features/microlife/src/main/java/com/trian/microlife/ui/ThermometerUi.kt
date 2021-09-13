package com.trian.microlife.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import com.trian.component.R
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardListDevice
import com.trian.component.chart.BaseChartView
import com.trian.component.chart.HorizontalCircularFeatures
import com.trian.component.ui.theme.*
import com.trian.microlife.viewmodel.MicrolifeViewModel


@ExperimentalMaterialApi
@Preview
@Composable
fun ThermometerUiPreview(){
    TesMultiModuleTheme {
//        ThermometerUi()
    }

}

@ExperimentalMaterialApi
@Composable
fun ThermometerUi(
    dataTemp: MicrolifeViewModel,
    modifier:Modifier=Modifier
){
    val value :Float = 35f
    val analytic : String = "Normal"
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        bottomBar = {
            CardListDevice(status = "Device", dateStatus = "7 Days Ago" ,onClick = {})
        },
        backgroundColor = LightBackground

    ){
        LazyColumn(content = {
            item {
                Column(modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 16.dp)
                    .height(200.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(vertical = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HorizontalCircularFeatures(
                                percentage = value/45,
                                radius = 60.dp,
                                value = value.toString(),
                                satuan = "C",
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
            }
            item {

                    Column(modifier = modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .background(Color.Transparent)
                    ) {
                        Column(modifier = modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(300.dp)
                            .background(Color.White)

                        ) {
                            BaseChartView(list = listOf(
                                Entry(1f, 10f),
                                Entry(2f, 2f),
                                Entry(3f, 7f),
                                Entry(4f, 20f),
                                Entry(6f, 6f),
                                Entry(7f, 10f),
                                Entry(8f, 8f),
                                Entry(9f, 3f),
                                Entry(10f, 2f)

                            ))
                        }
                    }

            }
        })

    }


}


