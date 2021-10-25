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
        navigationIcon={},
        elevation = 0.dp
    )

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