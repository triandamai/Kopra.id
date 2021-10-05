package com.trian.component.bottomsheet

import android.annotation.SuppressLint
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.trian.domain.models.TimeListDoctor
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import compose.icons.octicons.Pencil24
import kotlinx.coroutines.CoroutineScope


@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomSheetFormOrder(m : Modifier = Modifier,scope: CoroutineScope,nav:NavHostController){
    val isDialogDatePicker= remember { mutableStateOf(false) }
    val isDialogTimePicker= remember { mutableStateOf(false) }
    val note = remember { mutableStateOf(TextFieldValue("")) }
    val date = remember { mutableStateOf("Select Date")}
    val time = remember { mutableStateOf("Select Time") }

    MyDatePicker(isDialogDatePicker = isDialogDatePicker,date = date)
    MyTimePicker(isDialogTimePicker = isDialogTimePicker, time = time)
    Column(modifier = m.background(color = Color.White)){
        Column(modifier = m
            .fillMaxSize()
            .padding(20.dp)){
            Column() {
                Text(text = "Date")
                Spacer(modifier = m.height(10.dp))
                TextField(
                    value = date.value,
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
            Column() {
                Text(text = "Time")
                Spacer(modifier = m.height(10.dp))
                TextField(
                    value = time.value,
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
                    placeholder = { Text(text = time.value) },
                    enabled = false,
                    modifier = m
                        .clickable { isDialogTimePicker.value = true }
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = m.height(15.dp))
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
fun MyDatePicker(isDialogDatePicker:MutableState<Boolean>,date:MutableState<String>) {
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
                        isDialogDatePicker.value=false
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalFoundationApi
@Composable
fun MyTimePicker(
    isDialogTimePicker:MutableState<Boolean>,
    time:MutableState<String>,m:Modifier=Modifier,
    listTime:List<TimeListDoctor> = listOf(
        TimeListDoctor(
            id = "eyJpdiI6IjNYRW5paG9QYmdqXC9NdllsZ0RBdmF3PT0iLCJ2YWx1ZSI6IlpBendEb2VlY2x4eTZqeEZWK2RnakE9PSIsIm1hYyI6IjgyYzQyZGVhOWU2MTUzMTMxMzk4ZmY1OWIwOTkwMmNkZGJhNmJjNWY3YzQ5YjNhMjY3OTY0MjhjNTJmNTI1MzYifQ==",
            time = "09:00-12:00"
        ),
        TimeListDoctor(
            id = "eyJpdiI6IjNYRW5paG9QYmdqXC9NdllsZ0RBdmF3PT0iLCJ2YWx1ZSI6IlpBendEb2VlY2x4eTZqeEZWK2RnakE9PSIsIm1hYyI6IjgyYzQyZGVhOWU2MTUzMTMxMzk4ZmY1OWIwOTkwMmNkZGJhNmJjNWY3YzQ5YjNhMjY3OTY0MjhjNTJmNTI1MzYifQ==",
            time = "14:00-15:00"
        ),
    ),
) {

    if(isDialogTimePicker.value){
        Dialog(onDismissRequest = { isDialogTimePicker.value=false}) {
           Surface(
               modifier = m.background(color=Color.White)
           ) {
              Column(modifier = m.padding(10.dp)){
                  Text(
                      text = "Select Time",
                      style = MaterialTheme.typography.h1.copy(
                          fontWeight = FontWeight.Bold,
                          fontSize = 16.sp,
                          letterSpacing = 1.sp,
                      ),
                  )
                  Spacer(modifier = m.height(10.dp))
                  LazyVerticalGrid(cells = GridCells.Fixed(2)){
                      itemsIndexed(listTime){
                              index, item ->
                          Button(
                              onClick = {
                                  time.value = item.time
                                  isDialogTimePicker.value = false
                                        },
                              colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                              shape = RoundedCornerShape(8.dp),
                              modifier = m.padding(5.dp)
                          ) {
                              Text(
                                  text = item.time,
                                  style = MaterialTheme.typography.h1.copy(
                                      fontWeight = FontWeight.Medium,
                                      fontSize = 14.sp,
                                      letterSpacing = 1.sp,
                                      color = Color.White
                                  ),
                                  modifier = m.padding(5.dp))
                          }
                      }
                  }
              }
           }
        }
    }
}