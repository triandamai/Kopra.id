package com.trian.microlife.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import com.trian.component.R
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardListDevice
import com.trian.component.chart.BaseChartView
import com.trian.component.chart.VerticalCircularFeatures
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import com.trian.microlife.viewmodel.MicrolifeViewModel

@ExperimentalMaterialApi
@Preview
@Composable
fun BpUiPreview(){
    TesMultiModuleTheme {
    }

}

@ExperimentalMaterialApi
@Composable
fun BloodPressureUi(
    viewModel: MicrolifeViewModel,
    modifier: Modifier=Modifier,
    onSelectDevice:()->Unit
){
    val systole :Float = 100f
    val diastole: Float = 80f
    val analytic : String = "Normal"
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        bottomBar = {
            CardListDevice(
                status = "Device",
                dateStatus = "7 Days Ago" ,
                onClick = onSelectDevice
            )
        },
        backgroundColor = LightBackground

    ){
       LazyColumn() {
            item {
               Column(modifier = Modifier
                   .fillMaxWidth()
                   .background(Color.Transparent)
                   .clip(RoundedCornerShape(12.dp))
                   .padding(horizontal = 16.dp, vertical = 8.dp)) {
                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.SpaceEvenly,
                       modifier = Modifier
                           .clip(RoundedCornerShape(12.dp))
                           .coloredShadow(color= ColorFontFeatures)
                           .fillMaxWidth()
                           .height(300.dp)
                           .background(Color.White)
                           .padding(horizontal = 16.dp, vertical = 16.dp)
                   ) {
                       Column(
                           modifier = Modifier
                               .width(IntrinsicSize.Min)
                               .fillMaxHeight(),
                           verticalArrangement = Arrangement.SpaceAround,
                           horizontalAlignment = Alignment.CenterHorizontally
                       ) {
                           VerticalCircularFeatures(
                               percentage = systole/250,
                               radius = 60.dp,
                               value = systole.toString(),
                               satuan = "Systole",
                           )
                           Spacer(modifier = Modifier.height(10.dp))
                           VerticalCircularFeatures(
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
            }
            item {
               Column(modifier = modifier
                   .padding(horizontal = 16.dp, vertical = 16.dp)
                   .background(Color.Transparent)
               ) {
                   Column(modifier = modifier
                       .clip(RoundedCornerShape(12.dp))
                       .height(300.dp)
                       .background(Color.White)

                   ) {
                       BaseChartView(list = listOf(
                           Entry(1f, 10f),
                           Entry(2f, 2f),
                           Entry(3f, 7f),
                           Entry(4f, 20f),
                           Entry(6f, 6f),
                           Entry(7f, 10f),
                           Entry(8f, 8f),
                           Entry(9f, 3f),
                           Entry(10f, 2f)

                       ),description="")
                   }
               }
            }
           item {
               Column(modifier = modifier
                   .padding(horizontal = 16.dp, vertical = 16.dp)
                   .background(Color.Transparent)
               ) {
                   Column(modifier = modifier
                       .clip(RoundedCornerShape(12.dp))
                       .height(300.dp)
                       .background(Color.White)

                   ) {
               BaseChartView(list = listOf(
                   Entry(1f, 10f),
                   Entry(2f, 2f),
                   Entry(3f, 7f),
                   Entry(4f, 20f),
                   Entry(6f, 6f),
                   Entry(7f, 10f),
                   Entry(8f, 8f),
                   Entry(9f, 3f),
                   Entry(10f, 2f)
                   
               ),description="")
               }
               }
           }
           item {
               Spacer(modifier=modifier.height(90.dp))
           }


        }
    }

}