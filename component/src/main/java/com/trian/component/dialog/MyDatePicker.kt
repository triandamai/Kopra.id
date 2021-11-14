package com.trian.component.dialog

import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import org.joda.time.DateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDatePicker(
    isDialogDatePicker:Boolean,
    onCancel:()->Unit,
    onSelectedDate:(date:Long)->Unit
) {
    var currentDate by remember {
        mutableStateOf<Long>(0)
    }
    if(isDialogDatePicker){
        Dialog(onDismissRequest = onCancel) {
            Column(
                modifier = Modifier
                .background(Color.White)
                    .wrapContentSize()
            ){
                AndroidView({
                    DatePicker(ContextThemeWrapper(it, R.style.CustomCalendar))
                },
                    Modifier
                        .wrapContentSize()
                        .background(Color.White),
                    update = {
                            view->
                        view.setOnDateChangedListener{
                                datePicker, year, month, day ->

                            val dateTime=DateTime(
                                 year,
                                (month+1),
                                day,0,
                                0
                            )
                            currentDate = dateTime.millis

                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    TextButton(onClick = {
                        onSelectedDate(currentDate)
                        onCancel()
                    }) {
                        Text(text = "Ok")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MyTimePicker(
    onTimePicker:(hour:Int,minute:Int)->Unit,
    onCancel:()->Unit,
    isDialogTimePicker: Boolean
) {
    var currentHour by remember {
        mutableStateOf(0)
    }
    var currentMin by remember {
        mutableStateOf(0)
    }
    if(isDialogTimePicker){
        Dialog(onDismissRequest = onCancel) {
            Column(modifier = Modifier.background(Color.White)) {
                AndroidView({
                    TimePicker(android.view.ContextThemeWrapper(it, R.style.CustomCalendar))
                },
                    Modifier
                        .wrapContentSize()
                        .background(Color.White),
                    update = {
                            view->
                        view.setOnTimeChangedListener{
                                _,hour,min->
                            currentHour = hour
                            currentMin = min
                            onTimePicker(hour,min)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    TextButton(onClick = {
                        onTimePicker(currentHour,currentMin)
                        onCancel()
                    }) {
                        Text(text = "Ok")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}
