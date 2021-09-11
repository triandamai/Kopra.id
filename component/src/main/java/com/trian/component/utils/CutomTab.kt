package com.trian.component.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.dp

/**
 * Custom tab indicator
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */

@Composable
fun TextTab(tabSelected:Int,tabData:List<String>,onSelected:(index:Int)->Unit){
    ScrollableTabRow(
        backgroundColor= Color.White,
        selectedTabIndex = tabSelected,
        divider={},
        indicator = {
                tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[tabSelected]).clip(shape = RoundedCornerShape(topStart = 16.dp,topEnd = 16.dp)),
                height = 4.dp,
                color = Color.Red
            )
        },
        tabs = {
            tabData.forEachIndexed{
                    index, text ->
                Tab(
                    selected = tabSelected == index,
                    onClick = { onSelected(index) },
                    text = {
                        Text(
                            text = text,
                            color = when(tabSelected){
                                index-> Color.Red
                                else -> Color.DarkGray
                            },
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                )
            }
        })
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 80.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}