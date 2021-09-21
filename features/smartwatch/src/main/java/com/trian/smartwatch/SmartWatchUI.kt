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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.getLastdayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardAppVersion
import com.trian.component.cards.CardSmarthWatch
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import com.trian.smartwatch.utils.calculateMaxMin
import com.trian.smartwatch.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Info16
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.trian.component.cards.CardListDevice as CardListDevice1



@ExperimentalMaterialApi
@Composable
fun SmartWatchUi(
    viewModel:SmartWatchViewModel,
    modifier:Modifier=Modifier,
    nav:NavHostController,
    scope:CoroutineScope,
    shouldShowDevices:()->Unit
){
    //get status device taht connected or no
    val connectedStatus by viewModel.connectedStatus
    //first time view show equivalent to `onMounted`
    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getHistoryByDate(
                getLastdayTimeStamp(),
                getTodayTimeStamp()
            )
        }
    }
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
                            .clickable {
                                shouldShowDevices()

                            }
                            .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Octicons.Info16,
                                contentDescription = "Device")
                            Text(
                                text =connectedStatus,
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
                    val bpm by viewModel.listBloodOxygen
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }
                    bpm.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value_blood_oxygen
                            max = x!!.value_blood_oxygen
                            min = n!!.value_blood_oxygen
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
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_SPO2)
                    }
                }
                item{
                    val bpm by viewModel.listTemperature
                    var latest by remember {
                        mutableStateOf(0f)
                    }
                    var max by remember {
                        mutableStateOf(0f)
                    }
                    var min by remember {
                        mutableStateOf(0f)
                    }
                    bpm.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value_temperature
                            max = x!!.value_temperature
                            min = n!!.value_temperature
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
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE)
                    }
                }
                item{
                    val bpm by viewModel.listHeartRate
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }
                    bpm.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value_heart_rate
                            max = x!!.value_heart_rate
                            min = n!!.value_heart_rate
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
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE)
                    }
                }
                item{
                    val bpm by viewModel.listBloodPressure
                    var latest by remember {
                        mutableStateOf("0/0")
                    }
                    var max by remember {
                        mutableStateOf("0/0")
                    }
                    var min by remember {
                        mutableStateOf("0/0")
                    }
                    bpm.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = "${lat!!.value_systole}/${lat!!.value_diastole}"
                            max = "${x!!.value_systole}/${lat!!.value_diastole}"
                            min ="${n!!.value_systole}/${lat!!.value_diastole}"
                        }
                    }
                    CardSmarthWatch(
                        param = "Blood Pressure",
                        imageParam = "",
                        vlastest = "$latest",
                        vmax = "$max",
                        vmin = "$min",
                        satuan = "mmHg"
                    ) {
                        nav.navigate(Routes.SMARTWATCH_ROUTE.DETAIL_BPM)
                    }
                }
                item{
                    val bpm by viewModel.listRespiration
                    var latest by remember {
                        mutableStateOf(0)
                    }
                    var max by remember {
                        mutableStateOf(0)
                    }
                    var min by remember {
                        mutableStateOf(0)
                    }
                    bpm.calculateMaxMin {
                            isEmpty,lat, x, n ->
                        if(!isEmpty){
                            latest = lat!!.value_respiration
                            max = x!!.value_respiration
                            min = n!!.value_respiration
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
        SmartWatchUi(
            nav= rememberNavController(),
            viewModel = viewModel(),
            scope= rememberCoroutineScope()
        ){

        }
    }

}