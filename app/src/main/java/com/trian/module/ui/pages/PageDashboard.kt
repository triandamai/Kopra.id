package com.trian.module.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope


@Composable
fun PageDashboard(nav:NavHostController,scope:CoroutineScope,toFeature:()->Unit){
ComponentDashboard(onNavigate = { /*TODO*/ })
}

@Composable
fun ComponentDashboard(onNavigate:()->Unit,modifier: Modifier=Modifier){
    Column(modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Text(text = "Ini Dashboard")
        Button(onClick = onNavigate) {
            Text(text = "To Features")
        }
    }
}

@Preview
@Composable
fun PreviewComponentDashboard(){
    ComponentDashboard(onNavigate = { /*TODO*/ })
}