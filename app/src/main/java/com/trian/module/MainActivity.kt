package com.trian.module

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.bottomsheet.BottomSheetServices
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel  by viewModels()
    @Inject lateinit var permissionUtils:PermissionUtils




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberAnimatedNavController()
            val coroutineScope = rememberCoroutineScope()
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            //make statusbar custom color
            val systemUiController = rememberSystemUiController()
            val useDarkIcon = MaterialTheme.colors.isLight

           //add bottomsheet to a navigation
            navHostController.navigatorProvider += bottomSheetNavigator
             SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcon
                )
            }
            TesMultiModuleTheme {
                ModalBottomSheetLayout(
                    bottomSheetNavigator
                ) {
                    AnimatedNavHost(
                        navController =navHostController,
                        startDestination = Routes.SPLASH
                    ){

                        composable(Routes.SPLASH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ){
                            PageSplashScreen(navHostController,coroutineScope)
                        }
                        composable(Routes.ONBOARD,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageOnBoarding(nav=navHostController,scope = coroutineScope)
                        }
                        composable(Routes.LOGIN,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageLogin(nav=navHostController)
                        }
                        navigation(startDestination = Routes.NESTED_DASHBOARD.HOME ,route = Routes.DASHBOARD){
                            composable(Routes.NESTED_DASHBOARD.HOME){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    toFeature = {},
                                    page=Routes.NESTED_DASHBOARD.HOME)
                            }
                            composable(Routes.NESTED_DASHBOARD.ACCOUNT){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    toFeature = {},
                                    page=Routes.NESTED_DASHBOARD.ACCOUNT)
                            }
                            composable(Routes.NESTED_DASHBOARD.RESERVATION){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    toFeature = {},
                                    page=Routes.NESTED_DASHBOARD.RESERVATION)
                            }
                            composable(Routes.NESTED_DASHBOARD.CALL_DOCTOR){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    toFeature = {},
                                    page=Routes.NESTED_DASHBOARD.CALL_DOCTOR)
                            }
                        }


                        composable(Routes.REGISTER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageRegister(navHostController)
                        }
                        composable(Routes.DETAIL_HEALTH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageDetailHealthStatus()
                        }
                        bottomSheet(Routes.SHEET_SERVICE,){
                            BottomSheetServices()
                        }

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