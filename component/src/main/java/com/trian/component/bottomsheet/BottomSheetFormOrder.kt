package com.trian.component.bottomsheet

import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.trian.component.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import compose.icons.octicons.Pencil24
import kotlinx.coroutines.CoroutineScope


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomSheetFormOrder(m : Modifier = Modifier,scope: CoroutineScope,nav:NavHostController){
    val isDialogDatePicker= remember { mutableStateOf(false) }
    val note = remember { mutableStateOf(TextFieldValue("")) }
    val date = remember { mutableStateOf("Select Date")}
    val isTime = remember { mutableStateOf(false) }

    MyTimePicker(isDialogDatePicker = isDialogDatePicker,date = date)
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
                TextField(
                    value = note.value,
                    leadingIcon = { Icon(Octicons.Calendar24, contentDescription ="" ) },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    readOnly = true,
                    onValueChange = {},
                    placeholder = { Text(text = date.value) },
                    enabled = false,
                    modifier = m
                        .clickable { isDialogDatePicker.value = true }
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = m.height(15.dp))

            Spacer(modifier = m.height(15.dp))
            Button(
                onClick ={
                         nav.navigate(Routes.KUESIONER)
                },
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(8.dp)) {
                        Text(
                            text = "Submit",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = m.padding(10.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyTimePicker(isDialogDatePicker:MutableState<Boolean>,date:MutableState<String>) {
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
                    view.setOnDateChangedListener{ datePicker, i, i2, i3 -> date.value=
                        "${i3.toString()}-${(i2+1).toString()}-${i.toString()}"
                    }
                }
            )
        }
    }
}