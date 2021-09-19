package com.trian.smartwatch

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardAppVersion
import com.trian.component.cards.CardSmarthWatch
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.Info16
import com.trian.component.cards.CardListDevice as CardListDevice1



@ExperimentalMaterialApi
@Composable
fun SmartWatchUi(
    modifier:Modifier=Modifier,
    nav:NavHostController
){
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        backgroundColor = LightBackground

    ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                item {
                    Column(modifier = modifier
                        .background(Color.Transparent)
                        .padding(horizontal = 16.dp)
                    ) {
                        Row(modifier= modifier
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .background(GrayOpacity)
                            .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Octicons.Info16,
                                contentDescription = "Device")
                            Text(
                                text ="Device Connected: E86 - 0111",
                                modifier=modifier.padding(top=8.dp,bottom = 8.dp,start = 8.dp),
                            )
                        }
                    }
                }
                item {
                    Text(modifier= modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                        text = "Today")
                }
                item{
                    CardSmarthWatch(
                        param = "Blood Oxygen",
                        imageParam = "",
                        vlastest = "98",
                        vmax = "98",
                        vmin = "95",
                        satuan = "%"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_SPO2)
                    }
                }
                item{
                    CardSmarthWatch(
                        param = "Temperature",
                        imageParam = "",
                        vlastest = "33.6",
                        vmax = "37.3",
                        vmin = "33.6",
                        satuan = "C"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE)
                    }
                }
                item{
                    CardSmarthWatch(
                        param = "Heart Rate",
                        imageParam = "",
                        vlastest = "76",
                        vmax = "90",
                        vmin = "58",
                        satuan = "bpm"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE)
                    }
                }
                item{
                    CardSmarthWatch(
                        param = "Blood Pressure",
                        imageParam = "",
                        vlastest = "112.72",
                        vmax = "117/76",
                        vmin = "106/68",
                        satuan = "mmHg"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_BPM)
                    }
                }
                item{
                    CardSmarthWatch(
                        param = "Respiratory",
                        imageParam = "",
                        vlastest = "15",
                        vmax = "18",
                        vmin = "12",
                        satuan = "times/minute"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION)
                    }
                }
                item{
                    CardSmarthWatch(
                        param = "Sleep",
                        imageParam = "",
                        vlastest = "5.9",
                        vmax = "6.7",
                        vmin = "4.2",
                        satuan = "hours"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP)
                    }
                }
                item{
                    Spacer(modifier =
                    modifier.
                    height(16.dp))
                    CardAppVersion()
                    Spacer(modifier =
                    modifier.
                    height(40.dp))
                }

            }


    }

}
@ExperimentalMaterialApi
@Preview
@Composable
fun SmartwatchUiPreview(){
    TesMultiModuleTheme {
        SmartWatchUi(nav= rememberNavController())
    }

}