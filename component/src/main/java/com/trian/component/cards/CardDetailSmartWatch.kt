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
fun CardDetailSmartWatch(
    param: String,
    valueHigh: String,
    valueLow: String,
    Satuan: String,
    type: TypeDetailSw,
    onBackPress: ()-> Unit,
    onForwardPress : () -> Unit,
    onDatePick: () -> Unit,


    ){
    val hasilHigh : Float = when(param){
        "Temperature" -> valueHigh.toFloat()/45
        "Blood Oxygen" -> valueHigh.toFloat()/100
        "Heart Rate" -> valueHigh.toFloat()/250
        "Respiratory" -> valueHigh.toFloat()/60
        else -> 0f
    }
    val hasilLow : Float = when(param){
        "Temperature" -> valueLow.toFloat()/45
        "BloodOxygen" -> valueLow.toFloat()/100
        "HeartRate" -> valueLow.toFloat()/250
        "Respiratory" -> valueLow.toFloat()/60
        else -> 0f
    }

    Box(modifier = Modifier
        .background(LightBackground)
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
                        painter = painterResource(id = com.trian.component.R.drawable.dummy_chart),
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
                            tint = ColorFontFeatures
                        )
                    }
                    Text(
                        text = "dd-mm-yy",
                        modifier = Modifier.clickable {onDatePick()},
                        fontSize = 26.sp,
                        color = ColorFontFeatures
                    )
                    IconButton(onClick = { onForwardPress() }) {
                        Icon(
                            Icons.Filled.ArrowForwardIos,
                            contentDescription = "forwarad",
                            tint = ColorFontFeatures
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
                    .height(IntrinsicSize.Min),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            )
            {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Lowest",
                                color = ColorFontFeatures,
                                textAlign = TextAlign.Center,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = param,
                                color = ColorFontFeatures,
                                textAlign = TextAlign.Center,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CircularProgresBar(
                            percentage = hasilLow,
                            satuan = Satuan,
                            value = valueLow.toString()
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Highest",
                                color = ColorFontFeatures,
                                textAlign = TextAlign.Center,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = param,
                                color = ColorFontFeatures,
                                textAlign = TextAlign.Center,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CircularProgresBar(
                            percentage = hasilHigh,
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
                            descrip = "Systole"
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
                            descrip = "Diastole"
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

        "Hear Rate" -> LazyColumn(
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
                        text= "bpm", //satuan
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
                            descrip = "Heart Rate"
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
                                text= "bpm",
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
                                text= "bpm",
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
        CardDetailSmartWatchUi(type = "Bpm")
    }
}


