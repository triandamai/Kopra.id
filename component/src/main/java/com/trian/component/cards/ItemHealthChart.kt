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
/**
 * Chart Detail Health Status
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 03/10/2021
 */
@Composable
fun ItemHealthChart(
    modifier:Modifier = Modifier,
    dateString: String = "01 Sep 2021",
    name:String,
    index:Int=0,
    data:List<Entry>,
    names:List<String>,
    maxAxis:Float,
    minAxis:Float,
    onArrowClicked:(isNext:Boolean)->Unit
){

    Row(modifier = modifier
        .fillMaxWidth()
        .background(Color.Transparent)
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
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            ) {
                    Text(name,
                            modifier = modifier.padding(top = 6.dp,bottom = 6.dp)
                        )
                    Row(
                        modifier=modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        IconToggleButton(checked = false,
                            onCheckedChange = {onArrowClicked(false)}
                        ) {
                            Icon(Octicons.ArrowLeft24,contentDescription = "Get LastDay")
                        }
                        Text(text = dateString)
                        IconToggleButton(checked = false,
                            onCheckedChange = {onArrowClicked(true)}
                        ) {
                            Icon(Octicons.ArrowRight24,contentDescription = "Get Next Day")
                        }
                    }
                    BaseChartView(data,name=names,description = "",maxAxis = maxAxis,minAxis = minAxis)
            }
    }


}