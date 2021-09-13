package com.trian.module.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.trian.component.appbar.AppBarDetail
import com.trian.component.chart.BaseChartView

import com.trian.component.ui.theme.LightBackground
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.Bell24
import compose.icons.octicons.Sync24

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(modifier:Modifier=Modifier){
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    val currentHeight = LocalContext
        .current
        .resources
        .displayMetrics
        .heightPixels.dp/
            LocalDensity.current.density

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
                    item {
                        Row(modifier= modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val width = (currentWidth / 2) - 20.dp
                            val height = width / 4
                            Column(modifier= modifier
                                .padding(vertical = 8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .width(width)
                                .height(height)
                                .background(Color.White),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center) {
                                Row(modifier = modifier
                                    .fillMaxWidth()
                                    .clickable { }
                                    .padding(8.dp)) {
                                    Icon(imageVector = Octicons.Sync24,contentDescription = "Sync data")
                                    Spacer(modifier = modifier.width(16.dp))
                                    Text(text = "Sync")
                                }
                            }
                            Column(modifier= modifier
                                .padding(vertical = 8.dp)
                                .width(width)
                                .height(height)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center) {
                                Row(modifier = modifier
                                    .fillMaxWidth()
                                    .clickable { }
                                    .padding(12.dp)) {
                                    Icon(imageVector = Octicons.Bell24,contentDescription = "Sync data")
                                    Spacer(modifier = modifier.width(16.dp))
                                    Text(text = "Reminder Off")

                                }
                            }
                        }
                    }
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