package com.trian.module.ui.pages

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule

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
    Column {
        Text(text = "Ini Splash")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Ini Splash")
        }
    }


}
