package com.trian.component.bottomsheet

import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import com.trian.component.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Pencil24
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomSheetFormOrder(m : Modifier = Modifier){
    val isDialogDatePicker= remember { mutableStateOf(false) }
    val note = remember { mutableStateOf(TextFieldValue("")) }
    val time = remember { mutableStateOf("Date Picker")}
    MyTimePicker(isDialogDatePicker = isDialogDatePicker,time = time)
    Column(modifier = m.background(color = Color.White)){
        Column(modifier = m
            .fillMaxSize()
            .padding(20.dp)){
            Column() {
                Text(text = "Note")
                Spacer(modifier = m.height(10.dp))
                TextField(
                    value = note.value,
                    leadingIcon = { Icon(Octicons.Pencil24, contentDescription ="" ) },
                    onValueChange = {note.value=it},
                    singleLine = false,
                    modifier = m
                        .fillMaxWidth()
                        .height(150.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Spacer(modifier = m.height(20.dp))
            Column() {
                Text(text = "Date")
                Spacer(modifier = m.height(10.dp))
                Text(
                    modifier = m
                        .fillMaxWidth()
                        .clickable { isDialogDatePicker.value = true },
                    text = time.value,
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyTimePicker(isDialogDatePicker:MutableState<Boolean>,time:MutableState<String>) {
    if(isDialogDatePicker.value){
        Dialog(onDismissRequest = { isDialogDatePicker.value=false}) {
            AndroidView({
                DatePicker(android.view.ContextThemeWrapper(it, R.style.CustomCalendar))
            },
                Modifier
                    .wrapContentSize()
                    .background(Color.White),
                update = {
                        view->
                    view.setOnDateChangedListener{ datePicker, i, i2, i3 -> time.value=
                        "${i3.toString()}-${(i2+1).toString()}-${i.toString()}"
                    }
                }
            )
        }
    }
}