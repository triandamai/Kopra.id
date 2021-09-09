package com.trian.module.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.trian.component.appbar.AppBarDetail
import com.trian.component.chart.BaseChartView

import com.trian.component.ui.theme.LightBackground
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowRight24

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(modifier:Modifier=Modifier){
    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
            AppBarDetail(page = "Detail Health Status") {
            }
        }
    ) {
        Column(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()

        ) {
            LazyColumn(
                content = {
                    items(count = 6,itemContent = {
                        index:Int->
                        ItemHealthChart(index=index)
                    })
                })
        }
    }

}

@Composable
fun ItemHealthChart(modifier:Modifier = Modifier,index:Int){
    val entries = ArrayList<Entry>()
            //Part2
    entries.add(Entry(1f, 10f))
    entries.add(Entry(2f, 2f))
    entries.add(Entry(3f, 7f))
    entries.add(Entry(4f, 20f))
    entries.add(Entry(6f, 6f))
    entries.add(Entry(7f, 10f))
    entries.add(Entry(8f, 8f))
    entries.add(Entry(9f, 3f))
    entries.add(Entry(10f, 2f))
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
                    Text("Blood Pressure")
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
                    BaseChartView(entries)
            }
    }
}

@Preview
@Composable
fun PreviewDetailHealthStatus(){
    PageDetailHealthStatus()
}