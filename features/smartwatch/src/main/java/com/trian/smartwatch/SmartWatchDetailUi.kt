package com.trian.smartwatch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardDetailSmartWatchUi
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.utils.calculateMaxMin
import com.trian.data.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailSmartWatchUi(
    modifier:Modifier=Modifier,
    viewModel: SmartWatchViewModel,
    nav:NavHostController,
    scope:CoroutineScope,
    page:String,
    onClickCalender: ()-> Unit
){

    val data = mutableListOf<Entry>()
    val data2 = mutableListOf<Entry>()
    var latest by remember {
        mutableStateOf("0")
    }
    var max by remember {
        mutableStateOf("0")
    }
    var min by remember {
        mutableStateOf("0")
    }

    when(page){
        Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE-> {
            val result by  viewModel.listTemperature
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                         measurement.value_temperature

                    )
                )
            }
            result.calculateMaxMin{
                empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value_temperature}"
                    max = "${x!!.value_temperature}"
                    min = "${n!!.value_temperature}"
                }
            }
        }
        Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE-> {
            val result by  viewModel.listHeartRate
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_heart_rate.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value_heart_rate}"
                    max = "${x!!.value_heart_rate}"
                    min = "${n!!.value_heart_rate}"
                }
            }
        }
        Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION-> {
            val result by  viewModel.listRespiration
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_respiration.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value_respiration}"
                    max = "${x!!.value_respiration}"
                    min = "${n!!.value_respiration}"
                }
            }
        }
        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_OXYGEN-> {
            val result by  viewModel.listBloodOxygen
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_blood_oxygen.toFloat()

                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value_blood_oxygen}"
                    max = "${x!!.value_blood_oxygen}"
                    min = "${n!!.value_blood_oxygen}"
                }
            }
        }
        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_PRESSURE-> {
            val result by  viewModel.listBloodPressure
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_systole.toFloat()

                    )
                )
                data2.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_diastole.toFloat()
                    )
                )

            }
            result.calculateMaxMin{
                    empty, lat, x, n ->
                if(!empty){
                    latest = "${lat!!.value_systole}/${lat!!.value_diastole}"
                    max = "${x!!.value_systole}/${lat!!.value_diastole}"
                    min = "${n!!.value_systole}/${lat!!.value_diastole}"
                }
            }
        }
        Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP-> {
            val result by  viewModel.listSleep
            result.forEachIndexed {
                    index, measurement ->
                data.add(
                    Entry(
                        index.toFloat(),
                        measurement.value_sleep_deep_count.toFloat()

                    )
                )

            }
        }
        else-> {

        }
    }



    Scaffold(
        topBar = {
            AppBarFeature(name = "Andi", image ="" , onBackPressed = { /*TODO*/ }, onProfile = {})
        },
        backgroundColor = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            //calender
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Icon(
                    Octicons.Calendar24,
                    contentDescription = "",
                    tint = ColorFontFeatures,
                    modifier = modifier.clickable { onClickCalender() }
                )
                Text(
                    text = "Mon, Sep 14",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            //latest value
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = latest, //diastole
                    fontSize = 32.sp,
                    color = ColorFontFeatures
                )
                Spacer(modifier = modifier.width(5.dp))
                Text(
                    text = when(page){
                        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_OXYGEN->"%"
                        Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE->"c"
                        Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION->"times/minute"
                        Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE->"bpm"
                        else->"mmHg"
                    },
                    fontSize = 16.sp,
                    color = ColorFontFeatures,
                    modifier = modifier.padding(top = 10.dp)
                )
            }

                CardDetailSmartWatchUi(
                    type = page,
                    onCalenderClick = {},
                    data = data,
                    data2 = data2,
                    vmax = max,
                    vmin = min
                )


        }

    }
}

@Preview
@Composable
fun DetailSmartWatchUiPreview(){
    TesMultiModuleTheme {
        DetailSmartWatchUi(
            onClickCalender = {},
            page="Bpm",
            viewModel = viewModel(),
            scope = rememberCoroutineScope(),
            nav = rememberNavController()
        )
    }
}