package com.trian.module.ui.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.utils.*
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.ItemHealthChart
import com.trian.component.header.HeaderHealthStatus
import com.trian.component.ui.theme.LightBackground
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.bean.HistoryDatePickerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Detail Health Status
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun PageDetailHealthStatus(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    nav: NavHostController,
    scope: CoroutineScope,
    changeStatusBar:(Color)->Unit,
    syncMeasurement: ()->Unit,
    offReminder:()->Unit
){
    val listState = rememberLazyListState()
    var shouldFloatAppBar by remember{
        mutableStateOf(true)
    }
    val onSync by viewModel.onSync

    shouldFloatAppBar = if(listState.firstVisibleItemIndex > 0){
        changeStatusBar(Color.White)
        true
    }else{
        changeStatusBar(LightBackground)
        false
    }


    //when page onMounted/created get data by today date
    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getDetailHealthStatus(
                getLastDayTimeStamp(),
                getTodayTimeStamp()
            )
        }
    }

    //show loading when on sync from api
    if(onSync){
        Dialog(
            onDismissRequest = { /*TODO*/ },
            properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier= modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }


    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
            AppBarDetail(
                page = "Detail Health Status",
                elevation = when(shouldFloatAppBar){
                    true -> 4.dp
                    else -> 0.dp
                },
                color = when(shouldFloatAppBar){
                    true -> Color.White
                    else -> Color.Transparent
                }
            ) {
                nav.popBackStack()
            }
        }
    ) {
        Column(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()

        ) {

            LazyColumn(
                state=listState,
                content = {
                    item {
                      HeaderHealthStatus(
                          onSynchronized = {
                           viewModel.startSyncMeasurementFromApi()
                          },
                          onReminder = offReminder
                      )
                    }
                    item {
                        val bloodOxygen by viewModel.listBloodOxygen
                        val bloodNameOxygen by viewModel.listNameBloodOxygen
                        var currentDate by viewModel.dateBloodOxygen

                        ItemHealthChart(
                            index=0,
                            dateString=currentDate.from.formatReadableDate(),
                            name = "SpO2",
                            data = bloodOxygen,
                            names=bloodNameOxygen,
                            maxAxis = 140f,
                            minAxis = 30f,
                            onArrowClicked = {
                                isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getBloodOxygenHistory()


                            }
                        )
                    }
                    item {
                        val temperature by viewModel.listTemperature
                        val nameTemperature by viewModel.listNameTemperature
                        var currentDate by viewModel.dateTemperature
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name = "Temperature",
                            data = temperature,
                            names=nameTemperature,
                            maxAxis = 50f,
                            minAxis = 10f,

                            onArrowClicked = {
                                    isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getTemperatureHistory()

                            })
                    }
                    item {
                        val heartRate by viewModel.listHeartRate
                        val nameHeartRate by viewModel.listNameHeartRate
                        var currentDate by viewModel.dateHeartRate
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Heart Rate",
                            data = heartRate,
                            names=nameHeartRate,
                            maxAxis = 130f,
                            minAxis = 20f,

                            onArrowClicked = {
                                    isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getHeartRateHistory()

                            })
                    }
                    item {
                        val systole by viewModel.listSystole
                        val nameSystole by viewModel.listNameBloodPressure
                        var currentDate by viewModel.dateBloodPressure

                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Systole",
                            data = systole,
                            names=nameSystole,
                            maxAxis = 150f,
                            minAxis = 80f,
                            onArrowClicked = { isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getBloodPressureHistory()

                            })
                    }
                    item {
                        val diastole by viewModel.listDiastole
                        val nameSystole by viewModel.listNameBloodPressure
                        var currentDate by viewModel.dateBloodPressure
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Diastole",
                            data = diastole,
                            names=nameSystole,
                            maxAxis = 120f,
                            minAxis = 50f,
                            onArrowClicked = {
                                    isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getBloodPressureHistory()

                            })
                    }
                    item {
                        val respiration by viewModel.listRespiration
                        val nameRespiration by viewModel.listNameRespiration
                        var currentDate by viewModel.dateRespiration
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Respiration",
                            data = respiration,
                            names=nameRespiration,
                            maxAxis = 30f,
                            minAxis = 5f,
                            onArrowClicked = {
                                    isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getRespirationHistory()

                            })
                    }
                    item {
                        val sleep by viewModel.listSleep
                        var currentDate by viewModel.dateSleep
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Sleep",
                            data = sleep,
                            names=listOf(),
                            maxAxis = 140f,
                            minAxis = 10f,
                            onArrowClicked = {
                                    isNext: Boolean ->
                                currentDate = if(isNext){
                                    val fromNext = currentDate.from.getNextDate()

                                    HistoryDatePickerModel(
                                        from =fromNext,
                                        to =  fromNext.getNextDate()
                                    )
                                }else{
                                    val fromPrev = currentDate.from.getPreviousDate()
                                    HistoryDatePickerModel(
                                        from= fromPrev,
                                        to= fromPrev.getNextDate()
                                    )
                                }

                                    viewModel.getSleepHistory()

                            })
                    }
                })
        }
    }


}




@Preview
@Composable
fun PreviewDetailHealthStatus(){
    PageDetailHealthStatus(
        viewModel = viewModel(),
        nav = rememberNavController(),
        scope = rememberCoroutineScope(),
        changeStatusBar = {},
        offReminder = {},
        syncMeasurement = {}

    )
}