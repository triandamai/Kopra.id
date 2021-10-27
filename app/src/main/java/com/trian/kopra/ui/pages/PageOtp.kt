package com.trian.kopra.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.trian.common.utils.route.Routes
import com.trian.component.textfield.OTPTextFields
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.CoroutineScope

@ExperimentalComposeUiApi
@Composable
fun PageOtp(
    modifier:Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
    var otpValue by remember{ mutableStateOf("")}
    val keyboardController = LocalSoftwareKeyboardController.current
    fun sendCode(){
        mainViewModel.resendToken(otpValue){
            success, message ->
            if(success){
                navHostController.navigate(Routes.DASHBOARD)
            }
        }
    }
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
                    text="OTP",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value= MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
                Box{}
            }
        }
    ){
        Column(
            modifier = modifier.padding(20.dp)
        ){
            Text(
                text = "Masukan kode\nOTP",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "Kami telah mengirimkan kode OTP\nke nomor 088212345678",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = modifier.height(20.dp))
            OTPTextFields(length = 6){
                    getOtp ->
                otpValue = getOtp
            }
            Spacer(modifier = modifier.height(60.dp))
            Button(
                onClick ={
                    keyboardController?.hide()
                    sendCode()
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