package com.trian.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.trian.component.chart.BaseChartView
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowRight24

@Composable
fun ItemHealthChart(
    modifier:Modifier = Modifier,
    name:String,
    index:Int=0,
    data:List<Entry>,
    maxAxis:Float,
    minAxis:Float,
    onPickDate:()->Unit,
    onArrowClicked:(isNext:Boolean)->Unit
){


    Row(modifier = modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .clip(RoundedCornerShape(10.dp))
        .padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 4.dp,
            top = when (index) {
                0 -> 8.dp
                else -> 4.dp
            }
        )
        .height(300.dp)) {

            Column(modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
                .clickable { }
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            ) {
                    Text(name)
                    Row(
                        modifier=modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        IconToggleButton(checked = false, onCheckedChange = {
                        }) {
                            Icon(Octicons.ArrowLeft24,contentDescription = "7 Days Before")
                        }
                        Text(text = "01 - 08 Sep 2021")
                        IconToggleButton(checked = false, onCheckedChange = {
                        }) {
                            Icon(Octicons.ArrowRight24,contentDescription = "7 Days Before")
                        }
                    }
                    BaseChartView(data,description = "",maxAxis = maxAxis,minAxis = minAxis)
            }
    }


}