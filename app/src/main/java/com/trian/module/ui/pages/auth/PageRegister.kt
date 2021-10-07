package com.trian.module.ui.pages.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import kotlinx.coroutines.CoroutineScope

/**
 * Register Register
 * Author PT Cexup Telemedicine
 * Created by Kholid Barat daya
 * 03/08/2021
 */
@ExperimentalAnimatedInsets
@ExperimentalComposeUiApi
@Composable
fun PageRegister(
    modifier: Modifier=Modifier,
    nav: NavHostController,
    viewModel: MainViewModel,
    scope:CoroutineScope
) {
    var scrollState = rememberScrollState()

    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var nameState by remember { mutableStateOf("")}
    var usernameState by remember { mutableStateOf("")}
    var addressState by remember { mutableStateOf("")}
    var passwordShow by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val registerStatus by viewModel.registerStatus.observeAsState()

    fun processRegistration(){
        viewModel.register(
            name = nameState,
            email=emailState,
            username = usernameState,
            password = passwordState,
            address = addressState
        ){
            nav.navigate(Routes.CONFIRMATION_REGISTRATION){
                launchSingleTop=true
                popUpTo(Routes.REGISTER){
                    inclusive= true
                }
            }
        }
    }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 18.dp)
                .verticalScroll(state = scrollState),
        ) {
            Text(
                text="Create\nAccount",
                style= MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 50.sp
                )
            )
            Spacer(modifier = modifier.height(30.dp))
            Column(){
                Text(text = "Your name",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),)
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    value = nameState,
                    onValueChange = {nameState=it},
                    placeholder = {Text(text = "Full Name")},
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
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Column(){
                Text(text = "Email",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),)
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    value = emailState,
                    onValueChange = {emailState=it},
                    placeholder = {
                        Text(text = "Your mail")
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                        )
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Column(){
                Text(text = "Username",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),)
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    value = usernameState,
                    onValueChange = {usernameState=it},
                    placeholder = {Text(text = "Username")},
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                        )
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Column(){
                Text(text = "Password",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),)
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    value = passwordState,
                    onValueChange = {passwordState=it},
                    placeholder = {Text(text = "Your Secret Password")},
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                        )
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    visualTransformation = if(passwordShow) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {
                        passwordShow = !passwordShow
                    }) {
                        Icon(
                            imageVector = Octicons.Eye24,
                            contentDescription =  "",
                            tint = if(passwordShow) ColorFontFeatures else ColorGray
                        )
                    } },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Column(){
                Text(text = "Address",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),)
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    value = addressState,
                    onValueChange = {addressState=it},
                    placeholder = {
                        Text(text = "Your address")
                    },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White,
                        )
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Row(horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    enabled = true,
                    colors = CheckboxDefaults.colors(BluePrimary)
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(text = "I agree to the Terms & Conditions\nand Privacy Policy",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                ),)
            }
            Spacer(modifier = modifier.height(20.dp))
            Button(
                onClick = {
                    processRegistration()
                    keyboardController?.hide()
                },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                when (registerStatus) {
                    is NetworkStatus.Loading -> {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                    is NetworkStatus.Success -> {
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(10.dp))
                    }
                    is NetworkStatus.Error -> {
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(10.dp))
                    }
                    else -> {
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(10.dp))
                    }
                }

            }
            Spacer(modifier = modifier.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account?",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    color = ColorGray
                ))
                TextButton(onClick = {
                    nav.popBackStack()
                }) {
                    Text(
                        text = "Sign In",
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
}

@ExperimentalComposeUiApi
@ExperimentalAnimatedInsets
@Preview(showBackground = true)
@Composable
fun PreviewRegister(){

    PageRegister(
        nav= rememberNavController(),
        scope= rememberCoroutineScope(),
        viewModel = viewModel()
    )
}
