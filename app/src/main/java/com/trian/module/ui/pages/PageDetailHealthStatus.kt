package com.trian.module.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.trian.component.chart.HealthStatusChart
import com.trian.module.R

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(){
    Column {
        AndroidView(factory = {HealthStatusChart(android.view.ContextThemeWrapper(it, R.style.CustomCalendar))},update = {
            view->
        })
    }
}

@Preview
@Composable
fun PreviewDetailHealthStatus(){
    PageDetailHealthStatus()
}