package com.trian.module.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope


@Composable
fun PageDashboard(nav:NavHostController,scope:CoroutineScope,toFeature:()->Unit,modifier: Modifier=Modifier){
    Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
        Text(text = "Ini Dashboard")
        Button(onClick = { toFeature() }) {
            Text(text = "To Features")
        }
    }
}