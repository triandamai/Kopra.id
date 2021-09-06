package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.bmi.BmiActivity
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.microlife.BloodPressureActivity
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.microlife.ThermometerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel  by viewModels()
    @Inject lateinit var permissionUtils:PermissionUtils

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            //make statusbar custom color
            val systemUiController = rememberSystemUiController()
            val useDarkIcon = MaterialTheme.colors.isLight

             SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcon
                )
            }
            TesMultiModuleTheme {

                NavHost(navController =navHostController,startDestination = Routes.SPLASH.name){
                    composable(Routes.SPLASH.name){
                        PageSplashScreen(navHostController,coroutineScope)
                    }
                    composable(Routes.ONBOARD.name){
                        PageOnBoarding(navHostController, coroutineScope)
                    }
                    composable(Routes.DASHBOARD.name){
                        PageDashboard(navHostController,coroutineScope) {

                        }
                    }
                    composable(Routes.LOGIN.name){
                        PageLogin(navHostController,coroutineScope)
                    }
                    composable(Routes.REGISTER.name){
                        PageRegister()
                    }
                }
            }
           // startActivity(Intent(this, BmiActivity::class.java))
        }


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