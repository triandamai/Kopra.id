package com.trian.module.ui.pages


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
){
    val listState = rememberLazyListState()
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


    //when page onMounted/created get today date
    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getDetailHealthStatus(
                getLastDayTimeStamp(),
                getTodayTimeStamp()
            )
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
                      HeaderHealthStatus()
                    }
                    item {
                        val bloodOxygen by viewModel.listBloodOxygen
                        var currentDate by viewModel.dateBloodOxygen
                        ItemHealthChart(
                            index=0,
                            dateString=currentDate.from.formatReadableDate(),
                            name = "SpO2",
                            data = bloodOxygen,
                            maxAxis = 140f,
                            minAxis = 30f,
                            onPickDate = {

                            },
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
                        var currentDate by viewModel.dateTemperature
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name = "Temperature",
                            data = temperature,
                            maxAxis = 50f,
                            minAxis = 10f,
                            onPickDate = {

                            },
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
                        var currentDate by viewModel.dateHeartRate
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Heart Rate",
                            data = heartRate,
                            maxAxis = 130f,
                            minAxis = 20f,
                            onPickDate = {

                        },
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
                        var currentDate by viewModel.dateBloodPressure

                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Systole",
                            data = systole,
                            maxAxis = 150f,
                            minAxis = 80f,
                            onPickDate = {},
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
                        val diastole by viewModel.listDiastole
                        var currentDate by viewModel.dateBloodPressure
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Diastole",
                            data = diastole,
                            maxAxis = 120f,
                            minAxis = 50f,
                            onPickDate = {},
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
                        var currentDate by viewModel.dateRespiration
                        ItemHealthChart(
                            index=1,
                            dateString=currentDate.from.formatReadableDate(),
                            name="Respiration",
                            data = respiration,
                            maxAxis = 30f,
                            minAxis = 5f,
                            onPickDate = {},
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
                            maxAxis = 140f,
                            minAxis = 10f,
                            onPickDate = {},
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
        changeStatusBar = {}

    )
}