package com.trian.module.ui.pages


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.coloredShadow
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import compose.icons.octicons.Mail16
import compose.icons.octicons.Person24
import kotlinx.coroutines.CoroutineScope


@Composable
fun PageLogin(nav: NavHostController) {
    ComponentBodySection(onNavigate={
        nav.navigate(Routes.NESTED_DASHBOARD.HOME)
    },
        onNavigateToSignUp = {nav.navigate(Routes.REGISTER)},
        onNavigateToForgot = {nav.navigate(Routes.FORGET_PASSWORD)},
    )
}


@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun PreviewPageLogin(){
    val navHostController = rememberAnimatedNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navHostController.navigatorProvider += bottomSheetNavigator
    PageLogin(nav = navHostController)
}

@Composable
fun ComponentBodySection(
    m:Modifier=Modifier,
    onNavigate:()->Unit,
    onNavigateToSignUp:()->Unit,
    onNavigateToForgot:()->Unit
){
    val emailState = remember { mutableStateOf(TextFieldValue(""))}
    val passwordState = remember { mutableStateOf(TextFieldValue(""))}
    val passwordShow = remember { false }

    Column(
        modifier = m
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
            Spacer(modifier = m.height(30.dp))
            Text(text = "Username",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = emailState.value,
                leadingIcon = {Icon(Octicons.Person24, contentDescription ="" )},
                onValueChange = {emailState.value=it},
                placeholder = {Text(text = "Username")},
                singleLine = true,
                modifier = m.fillMaxWidth(),
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
        Column(){
            Text(text = "Password",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = passwordState.value,
                onValueChange = {passwordState.value=it},
                placeholder = {Text(text = "Your Secret Password")},
                singleLine = true,
                modifier = m
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
            Spacer(modifier = m.height(8.dp))
            TextButton(onClick = onNavigateToForgot,modifier = m.align(alignment = Alignment.End)) {
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
        Spacer(modifier = m.height(10.dp))
        Button(
            onClick =onNavigate,
            modifier = m.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
            shape = RoundedCornerShape(8.dp)) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                ),
                modifier = m.padding(10.dp))
        }
        Spacer(modifier = m.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = m.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Divider(color = ColorGray, thickness = 1.dp, modifier = m.width(50.dp))
            Text(
                text = "Or sign in with",
                modifier = m.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 12.sp,
                    color = ColorGray,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.1.sp,
                )
            )
            Divider(color = ColorGray, thickness = 1.dp,modifier = m.width(50.dp))
        }
        Spacer(modifier = m.height(20.dp))
        Button(
            onClick = onNavigate,
            modifier = m.fillMaxWidth(),
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
                    modifier = m.padding(10.dp))
            }
        }
        Spacer(modifier = m.height(15.dp))
        Row(verticalAlignment = Alignment.CenterVertically,modifier = m.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
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