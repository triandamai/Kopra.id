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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)
                    .background(color = Color.White)
                    .coloredShadow(color = ColorFontFeatures),
                shape = RoundedCornerShape(12.dp),
                onClick = {/*todo*/},
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight()
                            .background(SelectDevicelogo)
                    ) {

                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = 10.dp,
                                horizontal = 10.dp,
                            )
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Device Connected",
                                fontSize = 12.sp,
                                color = FontDeviceConnected
                            )
                            Text(
                                text = "17 day ago",
                                fontSize = 12.sp,
                                color = FontDeviceConnected
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Microlife A78",
                            fontSize = 20.sp,
                            color = FontDeviceName
                        )
                    }
                }

            }
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
