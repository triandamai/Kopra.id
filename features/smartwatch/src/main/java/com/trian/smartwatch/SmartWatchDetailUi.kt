package com.trian.smartwatch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
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
import com.trian.component.chart.BaseChartView
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.FontDeviceName
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.component.utils.DetailSmartwatchUI
import com.trian.data.utils.calculateMaxMin
import com.trian.data.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    val satuan = when(page){
        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_PRESSURE->"mmHg"
        Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_OXYGEN->"%"
        Routes.SMARTWATCH_ROUTE.DETAIL_ECG->""
        Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE->"bpm"
        Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION->"times/minute"
        Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE->"c"
        else -> ""
    }
    var latest by remember {
        mutableStateOf("0")
    }
    var max by remember {
        mutableStateOf("0")
    }
    var min by remember {
        mutableStateOf("0")
    }
    val scaffoldState = rememberScaffoldState()

    //equivalent `onStart`,`onResume`
    LaunchedEffect(key1 = scaffoldState){
        when(page){
            Routes.SMARTWATCH_ROUTE.DETAIL_ECG->
                scope.launch(Dispatchers.IO){
                    viewModel.startEcgTest()
                }
        }

    }

    //equivalent `onDestroy`
    DisposableEffect(key1 = scaffoldState){
        onDispose {
            when(page) {
                Routes.SMARTWATCH_ROUTE.DETAIL_ECG ->
                    scope.launch(Dispatchers.IO) {
                        viewModel.endEcg()
                    }
            }
        }
    }

    //calculate min max
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
            DetailSmartwatchUI(
                appBar = {
                    AppBarFeature(name = "Andi", image ="" , onBackPressed = { /*TODO*/ }, onProfile = {})
                },
                scaffoldState = scaffoldState
            ){
                header {
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
                            text = satuan,
                            fontSize = 16.sp,
                            color = ColorFontFeatures,
                            modifier = modifier.padding(top = 10.dp)
                        )
                    }
                }
                body {
                        when (page) {
                            Routes.SMARTWATCH_ROUTE.DETAIL_BLOOD_PRESSURE -> {
                                Column(
                                    modifier = modifier
                                        .fillMaxHeight(0.4f)
                                        .background(Color.White)
                                        .padding(horizontal = 16.dp, vertical = 10.dp)

                                ) {
                                    BaseChartView(
                                        list = data,
                                        description = "Systole"
                                    )
                                }
                                Column(
                                    modifier = modifier
                                        .fillMaxHeight(0.7f)
                                        .background(Color.White)
                                        .padding(horizontal = 16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    BaseChartView(
                                        list = data2,
                                        description = "Diastole"
                                    )
                                }
                            }
                            else ->{
                                Column(
                                    modifier = modifier
                                        .background(Color.White)
                                        .fillMaxHeight(0.8f)
                                        .padding(horizontal = 16.dp, vertical = 10.dp)

                                ) {
                                    BaseChartView(
                                        list = data,
                                        description = page //deskripsi heartrate,temperature,SpO2,Respiratory
                                    )
                                }
                            }
                        }
                }
                footer {
                        when(page) {
                            Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP -> {
                                Column(
                                    modifier = modifier
                                        .background(FontDeviceName)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "122", //value hour of sleep duration
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "122",//value minute of sleep duration
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Sleep Duration",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "0", //value wake time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                            }
                                            Text(
                                                text = "Wake Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00:00", //value time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )

                                            }
                                            Text(
                                                text = "Fall Asleep Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00:00", //value time Awake Time
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                            }
                                            Text(
                                                text = "Awake Time",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                    Row(
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00", //value hour of light sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "00",//value minute of Light sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Light Sleep",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Text(
                                                    text = "00", //value hour of deep sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "H",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                                Spacer(modifier = modifier.width(5.dp))
                                                Text(
                                                    text = "00",//value minute of deep sleep
                                                    fontSize = 26.sp,
                                                    color = ColorFontFeatures
                                                )
                                                Spacer(modifier = modifier.width(2.dp))
                                                Text(
                                                    text= "M",
                                                    fontSize = 14.sp,
                                                    color = ColorFontFeatures,
                                                    modifier = modifier.padding(top = 10.dp)
                                                )
                                            }
                                            Text(
                                                text = "Deep Sleep",
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(16.dp))
                                }
                            }
                            else -> {
                                Row(
                                    modifier = modifier
                                        .background(FontDeviceName)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = modifier.fillMaxHeight()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = max, //value heartrate,temperature,SpO2,Respiratory
                                                fontSize = 26.sp,
                                                color = ColorFontFeatures
                                            )
                                            Spacer(modifier = modifier.width(5.dp))
                                            Text(
                                                text=satuan,
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                                modifier = modifier.padding(top = 10.dp)
                                            )
                                        }
                                        Text(
                                            text = "Max",
                                            fontSize = 14.sp,
                                            color = ColorFontFeatures,
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = modifier.fillMaxHeight()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = min, //value heartrate,temperature,SpO2,Respiratory
                                                fontSize = 26.sp,
                                                color = ColorFontFeatures
                                            )
                                            Spacer(modifier = modifier.width(5.dp))
                                            Text(
                                                text= satuan,//satuan heartrate,temperature,SpO2,Respiratory
                                                fontSize = 14.sp,
                                                color = ColorFontFeatures,
                                                modifier = modifier.padding(top = 10.dp)
                                            )
                                        }
                                        Text(
                                            text = "Min",
                                            fontSize = 14.sp,
                                            color = ColorFontFeatures,
                                        )
                                    }

                                }

                            }
                        }
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