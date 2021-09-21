package com.trian.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.route.Routes
import com.trian.component.chart.BaseChartView
import com.trian.component.ui.theme.*


/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */


@Composable
fun CardDetailSmartWatchUi(
    modifier: Modifier = Modifier,
    type:String,
    data:List<Entry> = listOf(),
    data2:List<Entry> = listOf(),
    vmax:String,
    vmin:String,
    onCalenderClick: () -> Unit,

){
    when(type){
        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_PRESSURE-> {
            //Chart And Max, Min
            Column(
                modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight(0.4f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 10.dp)

                ) {
                    BaseChartView(
                        list = data,
                        description = "Systole"
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxHeight(0.7f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    BaseChartView(
                        list = data2,
                        description = "Diastole"
                    )
                }
                Row(
                    modifier = modifier
                        .background(FontDeviceName)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier.fillMaxHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = vmax,//value diastole
                                fontSize = 26.sp,
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = modifier.width(5.dp))
                            Text(
                                text = "mmhg",
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
                                text = vmin,//value diastole
                                fontSize = 26.sp,
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = modifier.width(5.dp))
                            Text(
                                text = "mmHg",
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
        Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP
        ->
        Column(modifier = modifier
                    .fillMaxSize()
                    .background(Color.Transparent)) {
                    Column(modifier = modifier
                        .fillMaxHeight(0.6f)
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 10.dp)

                    ) {
                        BaseChartView(list = data,
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
                   BaseChartView(list = data,
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
                               text = vmax, //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = modifier.width(5.dp))
                           Text(
                               text= when(type){//"Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                                   Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE->"C"
                                   Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_OXYGEN->"%"
                                   else -> "Bpm"
                               },
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
                               text = vmin, //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = modifier.width(5.dp))
                           Text(
                               text= when(type){//"Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                                   Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE->"c"
                                   Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_OXYGEN->"%"
                                   Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION->"times/minute"
                                   Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE->"bpm"
                                   else -> "Bpm"
                               },//satuan heartrate,temperature,SpO2,Respiratory
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
            type = "Temperature",
            onCalenderClick = {},
            vmax="",
            vmin=""
        )
    }
}


