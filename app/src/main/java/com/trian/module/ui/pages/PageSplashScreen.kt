package com.trian.module.ui.pages

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R

/**
 * Splash Screen Page
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 03/09/2021
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PageSplashScreen(
    nav:NavHostController,
    scope:CoroutineScope,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    Handler(Looper.getMainLooper())
        .postDelayed({
            nav.navigate(Routes.ONBOARD.name)
    },1000)
    Scaffold(modifier = modifier.fillMaxHeight(),
    bottomBar = {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "V2.0.0")
        }
    }) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo_cexup),
                modifier=modifier.width(50.dp).height(50.dp),
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