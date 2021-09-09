package com.trian.component.cards

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.chart.CircularProgresBar
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.ColorFontFeatures


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
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
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
                        modifier = Modifier.padding(5.dp).height(IntrinsicSize.Min)
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



