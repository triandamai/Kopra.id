package com.trian.component.dialog

import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    if(isDialogDatePicker){
        Dialog(onDismissRequest = onCancel) {
            AndroidView({
                DatePicker(ContextThemeWrapper(it, R.style.CustomCalendar))
            },
                Modifier
                    .wrapContentSize()
                    .background(Color.White),
                update = {
                        view->
                    view.setOnDateChangedListener{ datePicker, i, i2, i3 ->
                        onSelectedDate("${i3.toString()}-${(i2+1).toString()}-${i.toString()}")
                    }
                }
            )
        }
    }
}
