package com.trian.module.ui.pages.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.DeviceMobile24
import compose.icons.octicons.Megaphone24


@ExperimentalComposeUiApi
@Composable
fun PageLogin(m:Modifier = Modifier){
    var numberState by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
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
                    text="Kopra.Id",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value=MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
            }
        }
    ) {
        Column(
            modifier = m.padding(
                20.dp
            ),
        ) {
            Text(
                text = "Masukan nomor HP anda",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(10.dp))
            Text(
                text = "Kami akan mengirimkan kode konfirmasi",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(20.dp))
            TextField(
                value = numberState,
                onValueChange = {numberState=it},
                placeholder = {
                    Text(text = "088812345678",style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value=MaterialTheme.typography.h1.copy(
                            fontSize = 25.sp,
                            letterSpacing = 2.sp,
                        ),))
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
                textStyle = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value=MaterialTheme.typography.h1.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    ), ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                )
            )
            Spacer(modifier = m.height(50.dp))
            Button(
                onClick ={
                    keyboardController?.hide()
                },
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    text = "Masuk",
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

@ExperimentalComposeUiApi
@Composable
@Preview
private fun PreviewPageLogin(){
    PageLogin()
}