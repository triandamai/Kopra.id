package com.trian.module.ui.pages

import android.view.ContextThemeWrapper
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.utils.getLastdayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.ItemHealthChart
import com.trian.component.chart.BaseChartView

import com.trian.component.ui.theme.LightBackground
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.Bell24
import compose.icons.octicons.Sync24
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    nav: NavHostController,
    scope: CoroutineScope
){
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

    //when page onMounted/created get today date
    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getDetailHealthStatus(
                getLastdayTimeStamp(),
                getTodayTimeStamp()
            )
        }
    }


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
                    item {
                        val bloodOxygen by viewModel.listBloodOxygen
                        ItemHealthChart(
                            index=0,
                            name = "SpO2",
                            data = bloodOxygen,
                            maxAxis = 140f,
                            minAxis = 30f,
                            onPickDate = {

                            },
                            onArrowClicked = {
                                isNext: Boolean ->
                            }
                        )
                    }
                    item {
                        val temperature by viewModel.listTemperature
                        ItemHealthChart(
                            index=1,name = "Temperature",data = temperature,maxAxis = 50f,minAxis = 10f, onPickDate = {

                            },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                    item {
                        val heartRate by viewModel.listHeartRate
                        ItemHealthChart(index=1,name="Heart Rate",data = heartRate,maxAxis = 140f,minAxis = 50f, onPickDate = {

                        },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                    item {
                        val systole by viewModel.listSystole

                        ItemHealthChart(index=1,name="Systole",data = systole,maxAxis = 240f,minAxis = 80f, onPickDate = {

                        },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                    item {
                        val diastole by viewModel.listDiastole
                        ItemHealthChart(index=1,name="Diastole",data = diastole,maxAxis = 150f,minAxis = 50f, onPickDate = {

                        },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                    item {
                        val respiration by viewModel.listRespiration
                        ItemHealthChart(index=1,name="Respiration",data = respiration,maxAxis = 100f,minAxis = 5f,
                            onPickDate = {

                            },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                    item {
                        val sleep by viewModel.listSleep
                        ItemHealthChart(index=1,name="Sleep",data = sleep,maxAxis = 140f,minAxis = 10f, onPickDate = {

                        },
                            onArrowClicked = {
                                    isNext: Boolean ->
                            })
                    }
                })
        }
    }


}




@Preview
@Composable
fun PreviewDetailHealthStatus(){
    PageDetailHealthStatus(
        viewModel = viewModel(),
        nav = rememberNavController(),
        scope = rememberCoroutineScope(),

    )
}