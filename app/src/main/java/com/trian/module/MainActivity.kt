package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.microlife.BloodPressureActivity
import com.trian.module.ui.pages.PageDashboard
import com.trian.module.ui.pages.PageOnBoarding
import com.trian.module.ui.pages.PageSplashScreen
import com.trian.module.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TesMultiModuleTheme {
                val navHostController = rememberNavController()
                NavHost(navController =navHostController,startDestination = Routes.SPLASH.name){
                    composable(Routes.SPLASH.name){
                        PageSplashScreen()
                    }
                    composable(Routes.ONBOARD.name){
                        PageOnBoarding()
                    }
                    composable(Routes.DASHBOARD.name){
                        PageDashboard()
                    }
                }
            }
        }


    }

    fun toMicrolife(){
        startActivity(Intent(this,BloodPressureActivity::class.java))
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TesMultiModuleTheme {
        Greeting("Android")
    }
}