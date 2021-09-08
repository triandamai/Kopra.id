package com.trian.module.ui.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.ColorFontSw
import com.trian.module.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageLogin(nav: NavHostController,scope:CoroutineScope,modifier: Modifier = Modifier) {
    ComponentLogin(onNavigate={
        nav.navigate(Routes.DASHBOARD.name)
    })
}

@Composable
fun ComponentLogin(modifier: Modifier = Modifier,onNavigate:()->Unit){
    var username by remember {mutableStateOf<String>("") }
    var password by remember {mutableStateOf<String>("") }

    Column(
       modifier = modifier.background(color= Color.White),
    ) {
        TextField(
            value =username,
            onValueChange = {username = it},
            label ={
                Text(text = "Username")
            }
        )
        TextField(
            value =password,
            onValueChange = {password = it},
            label ={
                Text(text = "Password")
            }
        )
        Button(onClick =  onNavigate) {
            Text(text = "Login")
        }

    }
}

@Composable
fun PageLoginn(m:Modifier=Modifier,){
    val emailState = remember { mutableStateOf(TextFieldValue("LOL@GMAIL.COM"))}
    val passwordState = remember { mutableStateOf(TextFieldValue(""))}
    ComponentTopSection()

}

@Preview
@Composable
fun ComponentTopSection(m:Modifier=Modifier){
    Box(
        modifier = m
            .height(150.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 80.dp))
            .background(color = Color.White).shadow(
                elevation = 2.dp
            ),
    ) {
        Column(modifier = m.padding(top = 10.dp,start = 40.dp)) {
            Image(
                painter =
                painterResource(
                    id = R.drawable.logo_cexup
                ),
                contentDescription = "",
                modifier = m
                    .height(100.dp)
                    .width(100.dp)
            )
            Text(text = "It's good to see you again",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = ColorFontSw,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}