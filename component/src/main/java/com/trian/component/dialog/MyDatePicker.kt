package com.trian.component.dialog

import android.os.Build
import android.widget.DatePicker
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDatePicker(
    isDialogDatePicker:Boolean,
    onCancel:()->Unit,
    onSelectedDate:(date:String)->Unit
) {
    var currentDate by remember {
        mutableStateOf("")
    }
    if(isDialogDatePicker){
        Dialog(onDismissRequest = onCancel) {
            Column(modifier = Modifier.background(Color.White)) {
                AndroidView({
                    DatePicker(ContextThemeWrapper(it, R.style.CustomCalendar))
                },
                    Modifier
                        .wrapContentSize()
                        .background(Color.White),
                    update = {
                            view->
                        view.setOnDateChangedListener{ datePicker, i, i2, i3 ->
                            currentDate = "${i3.toString()}-${(i2+1).toString()}-${i.toString()}"

                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    TextButton(onClick = {
                        onSelectedDate(currentDate)
                    }) {
                        Text(text = "Ok")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
