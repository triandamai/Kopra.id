package com.trian.kopra.ui.pages.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.utils.formatDayDate
import com.trian.component.dialog.MyDatePicker
import com.trian.component.dialog.MyTimePicker
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.*

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageUpdateReminder(
    modifier:Modifier=Modifier,
    mainViewModel:MainViewModel,
    nav:NavHostController
){
    val scrollState = rememberScrollState()
    var date by mainViewModel.reminderFrom
    var due by mainViewModel.reminderDue
    var judulState by mainViewModel.reminderTitle
    val detailReminder by mainViewModel.detailReminder

    var isDialogDatePicker by remember { mutableStateOf(false) }
    var isDialogDateDuePicker by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val reminderId:String = nav.currentBackStackEntry?.arguments?.getString("slug") ?:""
    fun processCreateReminder(){
        mainViewModel.updateReminder(reminderId = reminderId) {
            if(it) {
                nav.popBackStack()
            }
        }
    }

    MyDatePicker(
        isDialogDatePicker = isDialogDatePicker,
        onCancel = {
            isDialogDatePicker = false
        }
    ){
            dates->

        date=dates
        isDialogDatePicker = false
    }

    MyDatePicker(
        isDialogDatePicker = isDialogDateDuePicker,
        onCancel = {
            isDialogDateDuePicker = false
        }
    ){
            dates->
        due=dates
        isDialogDateDuePicker = false
    }
    SideEffect {
        judulState = detailReminder.data?.title ?: ""
        due = detailReminder.data?.due ?: 0
        date = detailReminder.data?.updatedAt ?: 0
    }
    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getDetailReminder(reminderId)
    })


    Scaffold(
        topBar={
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconToggleButton(checked = false, onCheckedChange = {
                    nav.popBackStack()
                }) {
                    Icon(Octicons.ArrowLeft24,"")
                }
                Text(
                    text = "Buat Pengingat",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                )
                Box(){}
            }
        },
        bottomBar = {
            Button(
                onClick ={
                    keyboardController?.hide()
                    processCreateReminder()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    text = "Buat pengingat",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White

                    ),
                    modifier = modifier.padding(10.dp)
                )
            }
        }
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(scrollState),
        ) {
            Text("Judul")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = judulState,
                onValueChange = {judulState=it},
                placeholder = { Text(text = "Judul pengingat...") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Pencil24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Tanggal")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = date.formatDayDate(),
                onValueChange = {},
                placeholder = { Text(text = "Pilih tanggal") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding()
                    .clickable { isDialogDatePicker = true },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = ColorGray,
                ),
                leadingIcon = {
                    Icon(Octicons.Calendar24,"")
                },
                readOnly = true,
                enabled = false,
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Ingatkan Pada")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = due.formatDayDate(),
                onValueChange = {},
                placeholder = { Text(text = "Pilih tanggal") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding()
                    .clickable { isDialogDateDuePicker = true },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = ColorGray,
                ),
                leadingIcon = {
                    Icon(Octicons.Calendar24,"")
                },
                readOnly = true,
                enabled = false,
            )
        }
    }
}