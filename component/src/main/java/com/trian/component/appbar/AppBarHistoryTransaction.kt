package com.trian.component.appbar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */

@Composable
fun AppBarHistoryTransaction(modifier: Modifier=Modifier){
    TopAppBar(
        modifier = modifier,
        title = {
                Text(text = "Riwayat Transaksi")
        },
        actions = {} ,
        navigationIcon={}
    )



}

@Composable
fun TabLayout(
    modifier: Modifier=Modifier,
    selectedTab:Int=0,
    tabItems:List<String> = listOf(),
    onTabSelected:(index:Int)->Unit
){
    TabRow(
        selectedTabIndex = 0,
        indicator = {
                    tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[selectedTab]).clip(
                    shape= RoundedCornerShape(topStart = 16.dp,topEnd = 16.dp)
                ),
                height = 4.dp,
                color = Color.Red
            )
        },
        tabs = {
            tabItems.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            color = when(selectedTab){
                                index -> Color.Red
                                else -> Color.DarkGray
                            }
                        )
                    }
                )
            }
        }
    )
}

/*
* custom indicator
* */
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
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewAppBarHistoryTransaction(){
    Scaffold(topBar = {
        Column {
            AppBarHistoryTransaction()
            TabLayout(onTabSelected = {})
        }

    }) {

    }
}