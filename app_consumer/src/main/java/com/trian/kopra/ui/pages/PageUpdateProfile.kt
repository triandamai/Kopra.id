package com.trian.kopra.ui.pages

import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Pencil24
import compose.icons.octicons.Person24

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PageUpdateProfile(m:Modifier = Modifier){
    var nameState by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("Select Date")}
    var isDialogDatePicker = remember { mutableStateOf(false) }
    var onShowDialogUpdateProfile = remember { mutableStateOf(false)}
    val keyboardController = LocalSoftwareKeyboardController.current

    DialogUpdateProfile(onShow = onShowDialogUpdateProfile)
    MyDatePicker(isDialogDatePicker = isDialogDatePicker){
        dates->date=dates
    }
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = m
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Text(
                    text="Edit Profile",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value= MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
                Box{}
            }
        },
        bottomBar = {}
    ) {
        Column(
            modifier = m.fillMaxWidth()
        ){
                Box(
                    modifier = m.mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = m.width(100.dp)
                            .height(100.dp)
                    ).clickable {
                        onShowDialogUpdateProfile.value = true
                    }
                ){
                    Card(
                        shape = CircleShape,
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.sendsucces),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = m.padding(10.dp)
                        )
                    }
                    Icon(Octicons.Pencil24,"")
                }
            Column(
                modifier = m.padding(10.dp)
            ){
                Text(
                    text = "Nama",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = m.height(5.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {
                        Text(text = "Nama anda...")
                    },
                    singleLine = true,
                    modifier = m
                        .fillMaxWidth()
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = m.height(10.dp))
                Text(
                    text = "Username",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = m.height(5.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {
                        Text(text = "Username")
                    },
                    singleLine = true,
                    modifier = m
                        .fillMaxWidth()
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = m.height(10.dp))
                Text(
                    text = "Alamat",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = m.height(5.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {
                        Text(text = "Alamat anda")
                    },
                    singleLine = false,
                    modifier = m
                        .fillMaxWidth()
                        .height(100.dp)
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    }
                )
                Spacer(modifier = m.height(10.dp))
                Text(
                    text = "Tanggal Lahir",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = m.height(5.dp))
                TextField(
                    value = date,
                    onValueChange = {date=it},
                    placeholder = {
                        Text(text = date)
                    },
                    singleLine = true,
                    modifier = m
                        .fillMaxWidth()
                        .clickable { isDialogDatePicker.value = true }
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Octicons.Person24,"")
                    },
                    readOnly = true,
                    enabled = false,
                )
                Spacer(modifier = m.height(20.dp))
                Button(
                    onClick ={
                        keyboardController?.hide()
                    },
                    modifier = m.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                    shape = RoundedCornerShape(10.dp)) {
                    Text(
                        text = "Update",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = m.padding(10.dp)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDatePicker(
    isDialogDatePicker:MutableState<Boolean>,
    onSelectedDate:(date:String)->Unit
) {
    if(isDialogDatePicker.value){
        Dialog(onDismissRequest = { isDialogDatePicker.value = false}) {
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
                        isDialogDatePicker.value = false
                    }
                }
            )
        }
    }
}

@Composable
fun DialogUpdateProfile(
    m:Modifier = Modifier,
    onShow:MutableState<Boolean>,
){
    if(onShow.value){
        Dialog(onDismissRequest = { onShow.value=false }) {
            Surface(
                modifier = m.padding(10.dp),
                shape = RoundedCornerShape(5),
                color = Color.White,
            ) {
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(
                        text = "Ambil gambar melalui : ",
                        fontSize = 18.sp,
                        fontWeight= FontWeight.Bold,
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = "Galeri",
                        fontSize = 16.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Left
                    )
                    Divider()
                    Text(
                        text = "Kamera",
                        fontSize = 16.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Left
                    )
                }
            }
        }
    }
}