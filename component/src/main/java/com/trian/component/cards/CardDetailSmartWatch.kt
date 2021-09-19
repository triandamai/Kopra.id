package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import com.trian.component.chart.BaseChartView
import com.trian.component.chart.CircularProgresBar
import com.trian.component.ui.theme.*
import com.trian.component.ui.theme.ColorFontCardFeatures
import compose.icons.Octicons
import compose.icons.octicons.Calendar16
import compose.icons.octicons.Calendar24


/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

enum class TypeDetailSw{
    BloodPreasure,
    Oximetri,
    HeartRate,
    Respiratory,
    Temperature,
    Sleep,
}


@Composable
fun CardDetailSmartWatchUi(
    //viewmodel:
    type:String,
    onCalenderClick: () -> Unit,
    m: Modifier = Modifier
){
    when(type){
        "Bpm"->
            Column(
            modifier = m
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            //calender
                Row (
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                        ){
                    Icon(
                        Octicons.Calendar24,
                        contentDescription = "",
                        tint = ColorFontFeatures,
                        modifier = m.clickable { onCalenderClick }
                    )
                    Text(
                        text = "Mon, Sep 14",
                        modifier = m.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = ColorFontFeatures,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            //lastet value
                Row(
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "122", //systole
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(2.dp))
                    Text(
                        text = "/",
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(2.dp))
                    Text(
                        text = "122", //diastole
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(5.dp))
                    Text(
                        text= "mmHg",
                        fontSize = 16.sp,
                        color = ColorFontFeatures,
                        modifier = m.padding(top = 10.dp)
                    )
                }
            //Chart And Max, Min
                Column(modifier = m
                    .background(Color.Transparent)
                    .fillMaxSize()
                ) {
                    Column(modifier = m
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
                    Column(modifier = m
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
                        modifier = m
                            .background(FontDeviceName)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = m.fillMaxHeight()
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
                                Spacer(modifier = m.width(5.dp))
                                Text(
                                    text= "mmhg",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                    modifier = m.padding(top = 10.dp)
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
                            modifier = m.fillMaxHeight()
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
                                Spacer(modifier = m.width(5.dp))
                                Text(
                                    text= "mmHg",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                    modifier = m.padding(top = 10.dp)
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
        "Sleep Monitor"
        -> Column(
            modifier = m
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            //calender
                Row (
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                ){
                    Icon(
                        Octicons.Calendar24,
                        contentDescription = "",
                        tint = ColorFontFeatures,
                        modifier = m.clickable { onCalenderClick  }
                    )
                    Text(
                        text = "Mon, Sep 14",
                        modifier = m.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = ColorFontFeatures,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            //Lastest
                Row(
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "12", //value
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(2.dp))
                    Text(
                        text= "Hour", //satuan
                        fontSize = 16.sp,
                        color = ColorFontFeatures,
                        modifier = m.padding(top = 10.dp)
                    )
                    Spacer(modifier = m.width(5.dp))
                    Text(
                        text = "59", //value
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(2.dp))
                    Text(
                        text= "Min", //satuan
                        fontSize = 16.sp,
                        color = ColorFontFeatures,
                        modifier = m.padding(top = 10.dp)
                    )
                }

                Column(modifier = m
                    .fillMaxSize()
                    .background(Color.Transparent)
                ) {
                    Column(modifier = m
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
                        modifier = m
                            .background(FontDeviceName)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = m.height(16.dp))
                        Row(
                            modifier = m
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
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = m.width(5.dp))
                                    Text(
                                        text = "122",//value minute of sleep duration
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
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
                        Spacer(modifier = m.height(16.dp))
                        Row(
                            modifier = m
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
                        Spacer(modifier = m.height(16.dp))
                        Row(
                            modifier = m
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
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = m.width(5.dp))
                                    Text(
                                        text = "00",//value minute of Light sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
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
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "H",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
                                    )
                                    Spacer(modifier = m.width(5.dp))
                                    Text(
                                        text = "00",//value minute of deep sleep
                                        fontSize = 26.sp,
                                        color = ColorFontFeatures
                                    )
                                    Spacer(modifier = m.width(2.dp))
                                    Text(
                                        text= "M",
                                        fontSize = 14.sp,
                                        color = ColorFontFeatures,
                                        modifier = m.padding(top = 10.dp)
                                    )
                                }
                                Text(
                                    text = "Deep Sleep",
                                    fontSize = 14.sp,
                                    color = ColorFontFeatures,
                                )
                            }

                        }
                        Spacer(modifier = m.height(16.dp))
                    }
                }



        }

       else-> Column(
           modifier = m
               .fillMaxSize(),
           verticalArrangement = Arrangement.spacedBy(10.dp)
       ){
           //calender
           Row (
               modifier = m
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp, vertical = 16.dp),
           ){
               Icon(
                   Octicons.Calendar24,
                   contentDescription = "",
                   tint = ColorFontFeatures,
                   modifier = m.clickable { onCalenderClick}
               )
               Text(
                   text = "Mon, Sep 14",
                   modifier = m.fillMaxWidth(),
                   textAlign = TextAlign.Center,
                   color = ColorFontFeatures,
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Bold
               )

           }
           //lastet value
           Row(
               modifier = m
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
           ) {
               Text(
                   text = "60", //value heartrate,temperature,SpO2,Respiratory
                   fontSize = 32.sp,
                   color = ColorFontFeatures
               )
               Spacer(modifier = m.width(5.dp))
               Text(
                   text= "Bpm", //satuan heartrate,temperature,SpO2,Respiratory
                   fontSize = 16.sp,
                   color = ColorFontFeatures,
                   modifier = m.padding(top = 10.dp)
               )
           }
           //Chart And Max, Min
           Column(modifier = m
               .background(Color.Transparent)
               .fillMaxSize()
           ) {
               Column(modifier = m
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
                   modifier = m
                       .background(FontDeviceName)
                       .fillMaxWidth()
                       .fillMaxHeight(),
                   horizontalArrangement = Arrangement.SpaceAround,
                   verticalAlignment = Alignment.CenterVertically){
                   Column(
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = m.fillMaxHeight()
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically,
                       ) {
                           Text(
                               text = "80", //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = m.width(5.dp))
                           Text(
                               text= "Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                               fontSize = 14.sp,
                               color = ColorFontFeatures,
                               modifier = m.padding(top = 10.dp)
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
                       modifier = m.fillMaxHeight()
                   ) {
                       Row(
                           verticalAlignment = Alignment.CenterVertically,
                       ) {
                           Text(
                               text = "56", //value heartrate,temperature,SpO2,Respiratory
                               fontSize = 26.sp,
                               color = ColorFontFeatures
                           )
                           Spacer(modifier = m.width(5.dp))
                           Text(
                               text= "Bpm",//satuan heartrate,temperature,SpO2,Respiratory
                               fontSize = 14.sp,
                               color = ColorFontFeatures,
                               modifier = m.padding(top = 10.dp)
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




}

@Preview
@Composable
fun CardDetailPreview(){
    TesMultiModuleTheme {
        CardDetailSmartWatchUi(type = "Temperature", onCalenderClick = {})
    }
}


