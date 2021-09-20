package com.trian.module.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarDetail
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.FontDeviceConnected
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.Mail16
import compose.icons.octicons.Mail24

@Composable
fun PageForgetPassword(nav: NavHostController) {
    ComponentForgetPassword(
        onNavigate = {
            nav.navigate(Routes.SUCCESS_FORGET)
        },
        onLoginNavigate = {
            nav.navigate(Routes.LOGIN)
        },
    )
}
@Composable
fun ComponentForgetPassword(
    m:Modifier = Modifier,
    onNavigate: () -> Unit,
    onLoginNavigate: ()->Unit
){
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold( 
        topBar = {
            AppBarDetail(page = "Forget Password", onBackPress = {})
        },
    ) {
        Column(
            modifier = m
                .fillMaxSize()
        ) {
            Text(
                text = "Reset password",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = m
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    )
            )
            Text(
                text = "Enter the email associated with your account and we'll send an email with instructions to reset your password.",
                modifier = m
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Justify,
                color = FontDeviceConnected,
                fontSize = 16.sp
            )
            Column(
                modifier = m
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
            ){
                Text(text = "Email address",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = FontDeviceConnected,
                ),)
                Spacer(modifier = m.height(10.dp))
                TextField(
                    value = emailState.value,
                    leadingIcon = {Icon(Octicons.Mail16, contentDescription ="" )},
                    onValueChange = {emailState.value=it},
                    placeholder = {Text(text = "Your email address")},
                    singleLine = true,
                    modifier = m
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),

                )
            }
            Button(
                onClick =  onNavigate ,
                modifier = m
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Send",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                    modifier = m.padding(10.dp))
            }
            TextButton(
                onClick = onLoginNavigate,
                modifier = m
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "Back to login",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        color = BluePrimary
                    ),
                )
            }
        }
    }
}
@Composable
fun PageCompleteForget(nav: NavHostController) {
    ComponentCompleteForget(onNavigate= {
        nav.navigate(Routes.LOGIN)
    })
}
@Composable
fun ComponentCompleteForget(
    m : Modifier = Modifier,
    onNavigate: () -> Unit
){
    Column(
        modifier = m
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_email),
            contentDescription = "",
            modifier = m
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .size(150.dp)
        )
        Text(
            text = "Check Your Mail",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = m
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "We have sent a password recover instructions to your email.",
            modifier = m
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                )
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = FontDeviceConnected,
            fontSize = 16.sp
        )

        Button(
            onClick =  onNavigate,
            modifier = m
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Ok",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                ),
                modifier = m.padding(10.dp))
        }

    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewForgetPassword(){
    val navHostController = rememberAnimatedNavController()
    navHostController.navigatorProvider

    TesMultiModuleTheme {
//        PageCompleteSend(onNavigate = {})
        PageForgetPassword(nav = navHostController)

    }

}