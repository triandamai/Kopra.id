package com.trian.module.ui.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
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
    Column(modifier = modifier.background(color= Color.White)) {
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

@Preview
@Composable
fun PreviewComponentLogin(){
    ComponentLogin {

    }
}