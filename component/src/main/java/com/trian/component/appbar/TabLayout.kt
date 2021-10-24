package com.trian.component.appbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trian.component.utils.extension.customTabIndicatorOffset

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
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
