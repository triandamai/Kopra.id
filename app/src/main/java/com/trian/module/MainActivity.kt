package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
//import com.google.accompanist.navigation.animation.composable
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.bmi.BmiActivity
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint
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
//            val navHostController = rememberAnimatedNavController()
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
//            TesMultiModuleTheme {
//
//                AnimatedNavHost(
//                    navController =navHostController,
//                    startDestination = Routes.SPLASH.name){
//                    composable(Routes.SPLASH.name,
//                        enterTransition = {
//                            _,_ ->
//                            fadeIn(animationSpec = tween(2000))
//                        }
//                        ){
//                        PageSplashScreen(navHostController,coroutineScope)
//                    }
//                    composable(Routes.ONBOARD.name,
//                        enterTransition = {
//                                _,_ ->
//                            fadeIn(animationSpec = tween(2000))
//                        }){
//                        PageOnBoarding(navHostController, coroutineScope)
//                    }
//                    composable(Routes.DASHBOARD.name,
//                        enterTransition = {
//                                _,_ ->
//                            fadeIn(animationSpec = tween(2000))
//                        }){
//                        PageDashboard(navHostController,coroutineScope) {
//
//                        }
//                    }
//                    composable(Routes.LOGIN.name,
//                        enterTransition = {
//                                _,_ ->
//                            fadeIn(animationSpec = tween(2000))
//                        }){
//                        PageLogin(navHostController,coroutineScope)
//                    }
//                    composable(Routes.REGISTER.name,
//                        enterTransition = {
//                                _,_ ->
//                            fadeIn(animationSpec = tween(2000))
//                        }){
//                        PageRegister()
//                    }
//                }
//            }
            startActivity(Intent(this, BmiActivity::class.java))
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