package com.trian.module.ui.pages.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Splash Screen Page
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 03/09/2021
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PageSplashScreen(
    modifier: Modifier = Modifier,
    nav:NavHostController,
    scope:CoroutineScope,

    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    LaunchedEffect(key1 = scaffoldState) {
        scope.launch {

        }
    }

    Scaffold(modifier = modifier.fillMaxHeight(),
    bottomBar = {
        //version
    }) {
        Column(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_cexup),
                modifier= modifier
                    .width(150.dp)
                    .height(150.dp),
                contentDescription = "Logo Cexup"
            )
        }
    }


}
@Preview
@Composable
fun PreviewSplashScreen(){
    PageSplashScreen(nav = rememberNavController() , scope = rememberCoroutineScope())
}