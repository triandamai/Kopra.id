package com.trian.component.appbar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.GreenPrimary
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.Search24

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */

@Composable
fun AppBarListStore(
    modifier: Modifier=Modifier,
    onBackPressed:()->Unit,
    onSearchPressed:()->Unit

){
    TopAppBar(
        backgroundColor= GreenPrimary,
        modifier = modifier,
        navigationIcon={
                       IconToggleButton(checked = false, onCheckedChange = {
                           onBackPressed()
                       }) {
                           Icon(
                               imageVector = Octicons.ArrowLeft24,
                               tint=Color.White,
                               contentDescription = "")
                       }
        },
        title = {
                Text(
                    text = "Riwayat Transaksi",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
        },
        actions = {
            IconToggleButton(checked = false, onCheckedChange = {
               onSearchPressed()
            }) {
                Icon(imageVector = Octicons.Search24, contentDescription = "")
            }
        },
        elevation = 0.dp
    )

}




@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewAppBarListStore(){
    Scaffold(topBar = {
        Column {
            AppBarListStore(onBackPressed = {

            }){

            }
            TabLayout(onTabSelected = {})
        }

    }) {

    }
}