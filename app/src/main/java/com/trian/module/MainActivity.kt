package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.trian.bmi.BmiActivity
import com.trian.bmi.CardDetailSmartWatch
import com.trian.common.utils.route.Routes
import com.trian.microlife.BloodPressureActivity
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.microlife.ThermometerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel  by viewModels()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                val navHostController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()

                NavHost(navController =navHostController,startDestination = Routes.SPLASH.name){
                    composable(Routes.SPLASH.name){
                        PageSplashScreen(navHostController,coroutineScope)
                    }
                    composable(Routes.ONBOARD.name){
                        PageOnBoarding(navHostController, coroutineScope)
                    }
                    composable(Routes.DASHBOARD.name){
                        PageDashboard(navHostController,coroutineScope,{
                            toMicrolife()
                        })
                    }
                    composable(Routes.LOGIN.name){
                        PageLogin(navHostController,coroutineScope)
                    }
                    composable(Routes.REGISTER.name){
                        PageRegister()
                    }
                }
            }
        }

        toMicrolife()


    }

    fun toMicrolife(){
        startActivity(Intent(this,BmiActivity::class.java))
//        startActivity(Intent(this,ThermometerActivity::class.java))
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