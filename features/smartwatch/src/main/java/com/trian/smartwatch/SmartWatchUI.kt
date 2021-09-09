package com.trian.smartwatch

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardListDevice
import com.trian.component.cards.CardSmarthWatch
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow

@ExperimentalMaterialApi
@Preview
@Composable
fun SmartwatchUiPreview(){
    TesMultiModuleTheme {
        SmartWatchUi()
    }

}

@ExperimentalMaterialApi
@Composable
fun SmartWatchUi(

){
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        bottomBar = {
                    CardListDevice(status = "Device", dateStatus = "7 Days Ago" )
        },
        backgroundColor = LightBackground

    ) {
        Column(
            modifier = Modifier
                .background(LightBackground)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                items(count = 6){
                    CardSmarthWatch(
                        param = "Temperature",
                        imageParam = "",
                        vlastest = "37",
                        vmax = "38",
                        vmin = "35",
                        satuan = "C"
                    ) {

                    }


                }

            }

        }
    }

}
