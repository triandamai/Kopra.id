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
    m: Modifier = Modifier
){
    when(type){
        "Bpm"-> LazyColumn(
            modifier = m
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            //calender
            item{
                Row (
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                        ){
                    Icon(
                        Octicons.Calendar24,
                        contentDescription = "",
                        tint = ColorFontFeatures
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
            }

            //lastet value
            item {
                Row(
                    modifier = m
                        .fillMaxWidth()
                        .fillMaxHeight()
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
            }
            //Chart And Max, Min
            item {
                Column(modifier = m
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent)
                ) {
                    Column(modifier = m
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .background(Color.White)
                        .padding(horizontal = 5.dp)



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
                        .height(300.dp)
                        .background(Color.White)
                        .padding(horizontal = 5.dp),
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
                }
                Row(
                    modifier = m
                        .padding(horizontal = 16.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                        .background(FontDeviceName)
                        .fillMaxWidth()
                        .height(150.dp),
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
        -> LazyColumn(
            modifier = m
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            //calender
            item{
                Row (
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ){
                    Icon(
                        Octicons.Calendar24,
                        contentDescription = "",
                        tint = ColorFontFeatures
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
            }

            item {

                Row(
                    modifier = m
                        .fillMaxWidth()
                        .fillMaxHeight()
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
            }
            //chart sleep
            item {
                Column(modifier = m
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent)
                ) {
                    Column(modifier = m
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .background(Color.White)
                        .padding(horizontal = 5.dp)

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
                }
                Column(
                    modifier = m
                        .padding(horizontal = 16.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                        .background(FontDeviceName)
                        .fillMaxWidth(),
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
                            modifier = m.fillMaxHeight()
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
                            modifier = m.fillMaxHeight()
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
                            modifier = m.fillMaxHeight()
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
                            modifier = m.fillMaxHeight()
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
                            modifier = m.fillMaxHeight()
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
                            modifier = m.fillMaxHeight()
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

       else-> LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            item {
                Row(
                    modifier = m
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "122", //value
                        fontSize = 32.sp,
                        color = ColorFontFeatures
                    )
                    Spacer(modifier = m.width(5.dp))
                    Text(
                        text= "bpm", //satuan bpm,C,times/minutes,%
                        fontSize = 16.sp,
                        color = ColorFontFeatures,
                        modifier = m.padding(top = 10.dp)
                    )
                }
            }
            item {
                Column(modifier = m
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent)
                ) {
                    Column(modifier = m
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .background(Color.White)
                        .padding(horizontal = 5.dp)



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
                            description = "Heart Rate"
                        )
                    }
                }
                Row(
                    modifier = m
                        .padding(horizontal = 16.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                        .background(FontDeviceName)
                        .fillMaxWidth()
                        .height(150.dp),
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
                                text = "122",
                                fontSize = 32.sp,
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text= "bpm",//satuan bpm,C,times/minutes,%
                                fontSize = 16.sp,
                                color = ColorFontFeatures,
                                modifier = m.padding(top = 10.dp)
                            )
                        }
                        Text(
                            text = "Max",
                            fontSize = 16.sp,
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
                                text = "122",
                                fontSize = 32.sp,
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text= "bpm",//satuan bpm,C,times/minutes,%
                                fontSize = 16.sp,
                                color = ColorFontFeatures,
                                modifier = m.padding(top = 10.dp)
                            )
                        }
                        Text(
                            text = "Min",
                            fontSize = 16.sp,
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
        CardDetailSmartWatchUi(type = "Sleep Monitor")
    }
}


