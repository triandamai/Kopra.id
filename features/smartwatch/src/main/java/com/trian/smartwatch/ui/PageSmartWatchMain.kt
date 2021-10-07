package com.trian.smartwatch.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.formatReadableDate
import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.component.appbar.AppbarFeatureSmartWatch
import com.trian.component.cards.CardAppVersion
import com.trian.component.cards.CardSmarthWatch
import com.trian.component.ui.theme.*
import com.trian.data.utils.calculateMaxMin
import com.trian.data.utils.calculateSleepSummary
import com.trian.data.utils.explodeBloodPressure
import com.trian.data.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Info16
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun PageMainSmartwatch(
    viewModel: SmartWatchViewModel,
    modifier:Modifier=Modifier,
    nav:NavHostController,
    scope:CoroutineScope,
    changeStatusBar:(Color)->Unit,
    shouldShowDevices:()->Unit
){

    val listState = rememberLazyListState()
    //make app bar floating(show elevation) when scroll down the list
    var shouldFloatAppBar by remember{
        mutableStateOf(true)
    }
    shouldFloatAppBar = if(listState.firstVisibleItemIndex > 0){
        changeStatusBar(Color.White)
        true
    }else{
        changeStatusBar(LightBackground)
        false
    }

    //get status device taht connected or no
    val connectedStatus by viewModel.connectedStatus
    val connected by viewModel.connected
    val user by viewModel.currentUser
    val name = user?.name ?: run {
        ""
    }
    //first time view show equivalent to `onMounted`
    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getHistoryByDate(
                getLastDayTimeStamp(),
                getTodayTimeStamp()
            )
        }
    }
    Scaffold(
        topBar = {
            AppbarFeatureSmartWatch(
                name = name,
                shouldFloating = shouldFloatAppBar,
                onBackPressed = {},
                onSettingPressed = {nav.navigate(Routes.SmartwatchRoute.SETTING_SMARTWATCH)}
            )
        },
        backgroundColor = LightBackground

    ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                state = listState
            ){
                item {
                    Column(modifier = modifier
                        .background(Color.Transparent)
                        .padding(start = 16.dp,end=16.dp,top=16.dp)
                    ) {
                        Row(modifier= modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .background(brush = Brush.horizontalGradient(
                                colors = when(connected){
                                   true-> listOf(
                                        Color(0xFF2b51fa),
                                        Color(0xFF4d9efd)
                                    )
                                    else -> listOf(
                                        Color(0xFFff276a),
                                        Color(0xFffc5a4e)
                                    )
                                }))
                            .clickable {
                                shouldShowDevices()
                            }
                            .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Octicons.Info16,
                                tint=Color.White,
                                contentDescription = "Device Connected Icon")
                            Text(
                                text =connectedStatus,
                                color=Color.White,
                                modifier=modifier.padding(top=8.dp,bottom = 8.dp,start = 8.dp),
                            )
                        }
                    }
                }
                item {
                    Text(modifier= modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                        style= TextStyle(
                            fontWeight = FontWeight.SemiBold
                        ),
                        text = "Today, ${getTodayTimeStamp().formatReadableDate()}"
                    )
                }
                item{
                    val bloodOxygen by viewModel.listBloodOxygen
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }

                    bloodOxygen.calculateMaxMin {
                            isEmpty,lat, x, n ->

                        if(!isEmpty){
                            latest = lat!!.value.toInt()
                            max = x!!.value.toInt()
                            min = n!!.value.toInt()
                        }
                    }
                    CardSmarthWatch(
                        param = "Blood Oxygen",
                        imageParam = "",
                        vlastest = "$latest",
                        vmax = "$max",
                        vmin = "$min",
                        satuan = "%"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_BLOOD_OXYGEN)
                    }
                }
                item{
                    val temperature by viewModel.listTemperature
                    var latest by remember {
                        mutableStateOf(0f)
                    }
                    var max by remember {
                        mutableStateOf(0f)
                    }
                    var min by remember {
                        mutableStateOf(0f)
                    }
                    temperature.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value.toFloat()
                            max = x!!.value.toFloat()
                            min = n!!.value.toFloat()
                        }
                    }
                    CardSmarthWatch(
                        param = "Temperature",
                        imageParam = "",
                        vlastest = "$latest",
                        vmax = "$max",
                        vmin = "$min",
                        satuan = "C"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_TEMPERATURE)
                    }
                }
                item{
                    val heartRate by viewModel.listHeartRate
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }
                    heartRate.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value.toInt()
                            max = x!!.value.toInt()
                            min = n!!.value.toInt()
                        }
                    }
                    CardSmarthWatch(
                        param = "Heart Rate",
                        imageParam = "",
                        vlastest = "$latest",
                        vmax = "$max",
                        vmin = "$min",
                        satuan = "bpm"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_HEART_RATE)
                    }
                }
                item{
                    val bloodPressure by viewModel.listBloodPressure
                    var latest by remember {
                        mutableStateOf("0/0")
                    }
                    var max by remember {
                        mutableStateOf("0/0")
                    }
                    var min by remember {
                        mutableStateOf("0/0")
                    }
                    bloodPressure.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            val extractLat = lat!!.value.explodeBloodPressure()
                            val extractX = x!!.value.explodeBloodPressure()
                            val extractN = n!!.value.explodeBloodPressure()
                            latest = "${extractLat.systole}/${extractLat.diastole}"
                            max = "${extractX.systole}/${extractX.diastole}"
                            min ="${extractN.systole}/${extractN.diastole}"
                        }
                    }
                    CardSmarthWatch(
                        param = "Blood Pressure",
                        imageParam = "",
                        vlastest = latest,
                        vmax = max,
                        vmin = min,
                        satuan = "mmHg"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_BLOOD_PRESSURE)
                    }
                }
                item{
                    val respiratory by viewModel.listRespiration
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }
                    respiratory.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value.toInt()
                            max = x!!.value.toInt()
                            min = n!!.value.toInt()
                        }
                    }
                    CardSmarthWatch(
                        param = "Respiratory",
                        imageParam = "",
                        vlastest = "$latest",
                        vmax = "$max",
                        vmin = "$min",
                        satuan = "times/minute"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_RESPIRATION)
                    }
                }
                item{

                    val sleep by viewModel.listSleep
                    var total by remember {
                        mutableStateOf("0.0")
                    }
                    var deep by remember {
                        mutableStateOf("0.0")
                    }
                    var light by remember {
                        mutableStateOf("0.0")
                    }

                    if(sleep.isNotEmpty()){
                        sleep[0].calculateSleepSummary {
                                totalDuration,
                                deepSleep,
                                lightSleep,
                                wakeTime,
                                fallSleepTime,
                                awakeTime
                            ->
                            total = totalDuration
                            deep = deepSleep
                            light = lightSleep
                        }
                    }

                    CardSmarthWatch(
                        param = "Sleep",
                        imageParam = "",
                        vlastest = total,
                        vmax = deep,
                        vmin = light,
                        satuan = "hours",
                        latestLabel = "Total Sleep Duration",
                        maxLabel = "Deep Sleep",
                        minLabel = "Light SLeep"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_SLEEP)
                    }
                }

                item{
                    CardSmarthWatch(
                        param = "ECG",
                        imageParam = "",
                        vlastest = "5.9",
                        vmax = "6.7",
                        vmin = "4.2",
                        satuan = "hours"
                    ) {
                        nav.navigate(Routes.SmartwatchRoute.DETAIL_ECG)
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
        PageMainSmartwatch(
            nav= rememberNavController(),
            viewModel = viewModel(),
            scope= rememberCoroutineScope(),
            changeStatusBar = {}
        ){

        }
    }

}