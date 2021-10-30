package com.trian.kopra.ui.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Pencil24
import compose.icons.octicons.Person24

@ExperimentalComposeUiApi
@Composable
fun PageCreateToko(modifier:Modifier= Modifier){
    var nameState by remember{ mutableStateOf("")}
    var date by remember{ mutableStateOf("")}
    var address by remember{ mutableStateOf("")}
    var deskripsi by remember{ mutableStateOf("")}
    var noTelepon by remember{ mutableStateOf("")}
    val keyboardController = LocalSoftwareKeyboardController.current
    var scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Text(
                    text="Update toko",
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
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ){
            Box(
                modifier = modifier
                    .mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                    .clickable {

                    }
                    .align(alignment = Alignment.CenterHorizontally)
            ){
                Card(
                    shape = CircleShape,
                    border = BorderStroke(width = 1.dp,color = LightBackground)
                ){
                     Image(
                        painter = painterResource(id = R.drawable.sendsucces),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = modifier.padding(10.dp)
                    )
                }
                Card(
                    shape = CircleShape,
                    border = BorderStroke(width = 0.5.dp,color = Color.Black),
                    modifier = modifier.align(alignment = Alignment.BottomEnd)
                ) {
                    Icon(
                        Octicons.Pencil24,"",
                        modifier = modifier.padding(5.dp)
                            .height(18.dp),
                    )
                }
            }
            Column(
                modifier = modifier.padding(10.dp)
            ){
                Text(
                    text = "Nama toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {
                        Text(text = "Nama anda...")
                    },
                    singleLine = true,
                    modifier = modifier
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
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Deskripsi toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = deskripsi,
                    onValueChange = {deskripsi=it},
                    placeholder = {
                        Text(text = "Deskripsi toko")
                    },
                    singleLine = false,
                    modifier = modifier
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
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Alamat toko",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = address,
                    onValueChange = {address=it},
                    placeholder = {
                        Text(text = "Alamat toko")
                    },
                    singleLine = false,
                    modifier = modifier
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
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Nomor telepon",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                TextField(
                    value = noTelepon,
                    onValueChange = {noTelepon=it},
                    placeholder = {
                        Text(text = "088212122121")
                    },
                    singleLine = true,
                    modifier = modifier
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
                    },
                )
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    onClick ={
                        keyboardController?.hide()
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Konfirmasi",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}