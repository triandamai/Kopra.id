package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.trian.module.ui.pages.*
import com.trian.module.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel  by viewModels()
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
                    composable(Routes.LOGIN.name){
                        PageLogin()
                    }
                    composable(Routes.REGISTER.name){
                        PageRegister()
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