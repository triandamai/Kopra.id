package com.trian.module.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.data.Entry
import com.trian.component.appbar.AppBarDetail
import com.trian.component.chart.BaseChartView

import com.trian.component.ui.theme.BackgroundTemp
import com.trian.component.ui.theme.ColorBackground
import com.trian.module.R

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(modifier:Modifier=Modifier){
    Scaffold(
        backgroundColor = ColorBackground,
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
                        ItemHealthChart()
                    })
                })
        }
    }

}

@Composable
fun ItemHealthChart(modifier:Modifier = Modifier){
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
    Column(modifier = modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .clip(RoundedCornerShape(12.dp))
        .height(300.dp)) {
            BaseChartView(entries)
    }
}

@Preview
@Composable
fun PreviewDetailHealthStatus(){
    PageDetailHealthStatus()
}