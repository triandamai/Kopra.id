package com.trian.module.ui.pages


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.data.viewmodel.MainViewModel
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import compose.icons.octicons.Key24
import compose.icons.octicons.Person24
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Page Login
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
@Composable
fun PageLogin(nav: NavHostController,scope:CoroutineScope,viewModel: MainViewModel) {
    ComponentBodySection(
    onNavigate={
        nav.navigate(Routes.NESTED_DASHBOARD.HOME)
    },onNavigateToSignUp = {
        nav.navigate(Routes.REGISTER)
    }, onNavigateToForgot = {
        nav.navigate(Routes.FORGET_PASSWORD)
    },
        scope = scope,
        viewModel = viewModel
    )
}

@Composable
fun ComponentBodySection(
    modifier:Modifier=Modifier,
    scope:CoroutineScope,
    viewModel: MainViewModel,
    onNavigate:()->Unit,
    onNavigateToSignUp:()->Unit,
    onNavigateToForgot:()->Unit
){
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var passwordShow by remember {
        mutableStateOf(false)
    }

    val loginStatus by viewModel.loginStatus.observeAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(){
            Text(
                text="Sign In",
                style=MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp
                )
            )
            Spacer(modifier = modifier.height(30.dp))
            Text(text = "Username",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = modifier.height(10.dp))
            TextField(
                value = emailState,
                leadingIcon = {Icon(Octicons.Person24, contentDescription ="" )},
                onValueChange = {
                    emailState=it
                },
                placeholder = {Text(text = "Username")},
                singleLine = true,
                modifier = modifier.fillMaxWidth(),
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
                leadingIcon = {Icon(Octicons.Key24, contentDescription ="" )},
                onValueChange = {
                    passwordState=it
                },
                placeholder = {Text(text = "Your Secret Password")},
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                    ),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if(passwordShow) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = { IconButton(onClick = {}) {
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
            Spacer(modifier = modifier.height(8.dp))
            TextButton(onClick = onNavigateToForgot,modifier = modifier.align(alignment = Alignment.End)) {
                Text(
                    text = "Forgot Password ?",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        color = BluePrimary
                    ),
                )
            }
        }
        Spacer(modifier = modifier.height(10.dp))
        Button(
            onClick ={
                scope.launch {
                    viewModel.login(username = emailState,password = passwordState){
                        delay(400).also {
                            onNavigate()
                        }
                    }
                }
            },
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
            shape = RoundedCornerShape(8.dp)) {
            when(loginStatus){
                is NetworkStatus.Loading->{
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
                is NetworkStatus.Success->{
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        ),
                        modifier = modifier.padding(10.dp))
                }
                is NetworkStatus.Error->{
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        ),
                        modifier = modifier.padding(10.dp))
                }
                else -> {
                    Text(
                    text = "Sign In",
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
        Spacer(modifier = modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Divider(color = ColorGray, thickness = 1.dp, modifier = modifier.width(50.dp))
            Text(
                text = "Or sign in with",
                modifier = modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 12.sp,
                    color = ColorGray,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.1.sp,
                )
            )
            Divider(color = ColorGray, thickness = 1.dp,modifier = modifier.width(50.dp))
        }
        Spacer(modifier = modifier.height(20.dp))
        Button(
            onClick = onNavigate,
            modifier = modifier.fillMaxWidth(),
            border = BorderStroke(color = Color.Gray,width = 1.dp,),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    "",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Continue with google",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = ColorGray
                    ),
                    modifier = modifier.padding(10.dp))
            }
        }
        Spacer(modifier = modifier.height(15.dp))
        Row(verticalAlignment = Alignment.CenterVertically,modifier = modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                Text(text = "Don't have an account yet?",style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    color = ColorGray
                ),
            )
            TextButton(onClick = onNavigateToSignUp) {
                Text(
                    text = "Sign Up",
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

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun PreviewPageLogin(){
    val navHostController = rememberAnimatedNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navHostController.navigatorProvider += bottomSheetNavigator
    PageLogin(nav = navHostController,scope = rememberCoroutineScope(),viewModel = viewModel())
}