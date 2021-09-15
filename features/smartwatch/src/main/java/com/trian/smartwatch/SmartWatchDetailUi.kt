package com.trian.smartwatch

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardDetailSmartWatchUi
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme

@Composable
fun DetailSmartWatchUi(
    onClickCalender: ()-> Unit
){
    Scaffold(
        topBar = {
            AppBarFeature(name = "Andi", image ="" , onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        backgroundColor = Color.White
    ) {
        CardDetailSmartWatchUi(type = "Heart Rate", onCalenderClick = {})
    }
}

@Preview
@Composable
fun DetailSmartWatchUiPreview(){
    TesMultiModuleTheme {
        DetailSmartWatchUi(onClickCalender = {})
    }
}