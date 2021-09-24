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
import com.google.android.material.datepicker.MaterialDatePicker
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Pencil24
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun BottomSheetFormOrder(m : Modifier = Modifier){

    val note = remember { mutableStateOf(TextFieldValue("")) }
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
                        .clickable {  },
                    text = "Date Picker",
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyTimePicker(onDatePicker:(Date:Any)->Unit) {
    AndroidView({
        DatePicker(android.view.ContextThemeWrapper(it, R.style.CustomCalendar))
    },
        Modifier
            .wrapContentSize()
            .background(Color.Gray),
        update = {
                view->
            view.setOnDateChangedListener{ datePicker, i, i2, i3 -> onDatePicker(datePicker) }
        }
    )
}

@Composable
@Preview
private fun PreviewPageFormOrder(){
    BottomSheetFormOrder()
}