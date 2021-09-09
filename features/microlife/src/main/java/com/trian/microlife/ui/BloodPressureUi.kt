package com.trian.microlife.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.appbar.AppBarFeature
import com.trian.component.ui.theme.*
import com.trian.microlife.viewmodel.MicrolifeViewModel

@ExperimentalMaterialApi
@Preview
@Composable
fun BpUiPreview(){
    TesMultiModuleTheme {
//        BloodPressureUi()
    }

}

@ExperimentalMaterialApi
@Composable
fun BloodPressureUi(
    dataTemp: MicrolifeViewModel
){
    val systole :Float = 100f
    val diastole: Float = 80f
    val analytic : String = "Normal"
    Column(
        modifier = Modifier
            .background(LightBackground)
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)

    ) {
        AppBarFeature(name = "Andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(IntrinsicSize.Min)
                    .background(color = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 40.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularValueTermo(
                            percentage = systole/250,
                            radius = 60.dp,
                            value = systole.toString(),
                            satuan = "Systole",
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CircularValueTermo(
                            percentage = diastole/250,
                            radius = 60.dp,
                            value = diastole.toString(),
                            satuan = "Diastole",
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Divider(
                        color = ColorFontFeatures,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp),
                        thickness = 3.dp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = analytic,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                }

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color.White)
                    .height(250.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_chart),
                    contentDescription = "dummy chart",
                    modifier = Modifier.fillMaxSize()

                )

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = Color.White)
                    .height(250.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_chart),
                    contentDescription = "dummy chart",
                    modifier = Modifier.fillMaxSize()

                )

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(100.dp)
                    .background(color = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
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
                            .background(Color(0xFFF395BA))
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
        }

    }

}