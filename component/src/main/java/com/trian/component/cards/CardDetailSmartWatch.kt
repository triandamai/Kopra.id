package com.trian.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.route.Routes
import com.trian.component.chart.BaseChartView
import com.trian.component.ui.theme.*
import com.trian.domain.entities.Measurement
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import okhttp3.Route


/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */


@Composable
fun CardDetailSmartWatchUi(
    type:String,
    list: List<Measurement>,
    onCalenderClick: () -> Unit,
    modifier: Modifier = Modifier
){
    when(type){
        Routes.SMARTWATCH_ROUTE.DETAIL_BPM->
            //Chart And Max, Min
                Column(modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
                ) {
                    Column(modifier = modifier
                        .fillMaxHeight(0.4f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 10.dp)

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

                        ),
                            description = "Systole"
                        )
                    }
                    Column(modifier = modifier
                        .fillMaxHeight(0.7f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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

                        ),
                            description = "Diastole"
                        )
                    }
                    Row(
                        modifier = modifier
                            .background(FontDeviceName)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.fillMaxHeight()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "122", //value systole
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Text(
                                    text = "/",
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Text(
                                    text = "122",//value diastole
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Spacer(modifier = modifier.width(5.dp))
                                Text(
                                    text= "mmhg",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                    modifier = modifier.padding(top = 10.dp)
                                )
                            }
                            Text(
                                text = "Max",
                                fontSize = 14.sp,
                                color = ColorFontFeatures,
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.fillMaxHeight()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "122", //value systole
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Text(
                                    text = "/",
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Text(
                                    text = "122",//value diastole
                                    fontSize = 26.sp,
                                    color = ColorFontFeatures
                                )
                                Spacer(modifier = modifier.width(5.dp))
                                Text(
                                    text= "mmHg",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                    modifier = modifier.padding(top = 10.dp)
                                )
                            }
                            Text(
                                text = "Min",
                                fontSize = 14.sp,
                                color = ColorFontFeatures,
                            )
                        }

                    }
                }

        Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP
        ->

                Column(modifier = modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                ) {
                    Column(modifier = modifier
                        .fillMaxHeight(0.6f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 10.dp)

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

                        ),
                            description = "Sleep Monitor"
                        )
                    }
                    Column(
                        modifier = modifier
                            .background(FontDeviceName)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = modifier.height(16.dp))
                        Row(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "122", //value hour of sleep duration
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "122",//value minute of sleep duration
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                }
                                Text(
                                    text = "Sleep Duration",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "0", //value wake time
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                }
                                Text(
                                    text = "Wake Time",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }

                        }
                        Spacer(modifier = modifier.height(16.dp))
                        Row(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "00:00", //value time
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )

                                }
                                Text(
                                    text = "Fall Asleep Time",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "00:00", //value time Awake Time
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                }
                                Text(
                                    text = "Awake Time",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }

                        }
                        Spacer(modifier = modifier.height(16.dp))
                        Row(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "00", //value hour of light sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "00",//value minute of Light sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                }
                                Text(
                                    text = "Light Sleep",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "00", //value hour of deep sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = modifier.width(5.dp))
                                    Text(
                                        text = "00",//value minute of deep sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = modifier.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = modifier.padding(top = 10.dp)
                                    )
                                }
                                Text(
                                    text = "Deep Sleep",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }

                        }
                        Spacer(modifier = modifier.height(16.dp))
                    }
                }



       else->
           //Chart And Max, Min
           Column(modifier = modifier
               .background(Color.Transparent)
               .fillMaxSize()
           ) {
               Column(modifier = modifier
                   .fillMaxHeight(0.8f)
                   .background(Color.White)
                   .padding(horizontal = 16.dp, vertical = 10.dp)



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

                   ),
                       description = type //deskripsi heartrate,temperature,SpO2,Respiratory
                   )
               }
               Row(
                   modifier = modifier
                       .background(FontDeviceName)
                       .fillMaxWidth()
                       .fillMaxHeight(),
                   horizontalArrangement = Arrangement.SpaceAround,
                   verticalAlignment = Alignment.CenterVertically){
                   Column(
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = modifier.fillMaxHeight()
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically,
                       ) {
                           Text(
                               text = "80", //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = modifier.width(5.dp))
                           Text(
                               text= "Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                               fontSize = 14.sp,
                               color = ColorFontFeatures,
                               modifier = modifier.padding(top = 10.dp)
                           )
                       }
                       Text(
                           text = "Max",
                           fontSize = 14.sp,
                           color = ColorFontFeatures,
                       )
                   }
                   Column(
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = modifier.fillMaxHeight()
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically,
                       ) {
                           Text(
                               text = "56", //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = modifier.width(5.dp))
                           Text(
                               text= "Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                               fontSize = 14.sp,
                               color = ColorFontFeatures,
                               modifier = modifier.padding(top = 10.dp)
                           )
                       }
                       Text(
                           text = "Min",
                           fontSize = 14.sp,
                           color = ColorFontFeatures,
                       )
                   }

               }
           }


    }




}

@Preview
@Composable
fun CardDetailPreview(){
    TesMultiModuleTheme {
        CardDetailSmartWatchUi(
            type = "Temperature", onCalenderClick = {},
            list = listOf()
        )
    }
}


