package com.trian.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.Bell24
import compose.icons.octicons.Sync24
/**
 * Header Detail Health Status
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 03/10/2021
 */
@Composable
fun HeaderHealthStatus(
    modifier:Modifier =Modifier,
    onSynchronized: ()->Unit,
    onReminder:()->Unit
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
        val height = (width / 4) - 4.dp
        Column(modifier= modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(vertical = 8.dp)
            .width(width)
            .height(height)
            .clickable {
                onSynchronized()
            },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Icon(imageVector = Octicons.Sync24,contentDescription = "Sync data")
                Spacer(modifier = modifier.width(16.dp))
                Text(text = "Sync data")
            }
        }
        Column(modifier= modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(vertical = 8.dp)
            .width(width)
            .height(height)
            .clickable {
                onReminder()
            },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = modifier
                .fillMaxWidth()
                .clickable { }
                .padding(12.dp)) {
                Icon(imageVector = Octicons.Bell24,contentDescription = "Reminder Off")
                Spacer(modifier = modifier.width(16.dp))
                Text(text = "Reminder Off")

            }
        }
    }
}