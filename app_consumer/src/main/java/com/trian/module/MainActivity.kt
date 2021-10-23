package com.trian.module

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color

import androidx.navigation.plusAssign
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.accompanist.insets.ExperimentalAnimatedInsets
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

import com.trian.component.ui.theme.TesMultiModuleTheme
//import com.trian.data.worker.MeasurementSyncWorker

import com.trian.module.ui.pages.auth.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main Activity
 * Author Trian Damai Project
 * Created by Trian Damai
 * 28/08/2021
 **/


@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimatedInsets
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                    color = Color.White,
                    darkIcons = useDarkIcon
                )
            }

            fun setColorStatusBar(color:Color){
                systemUiController.setStatusBarColor(
                    color = color,
                )
            }

            TesMultiModuleTheme {

                ModalBottomSheetLayout(
                    bottomSheetNavigator
                ) {
                    AnimatedNavHost(
                        navController =navHostController,
                        startDestination = Routes.LOGIN
                    ){

                        composable(Routes.SPLASH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ){
                            setColorStatusBar(Color.White)
                            PageSplashScreen(
                                nav=navHostController,scope=coroutineScope,
                            )
                        }
                        composable(Routes.LOGIN,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageLogin()
                        }
                        navigation(startDestination = Routes.Dashboard.HOME ,route = Routes.DASHBOARD){


                        }

                        bottomSheet(Routes.SHEET_CANCEL_ORDER,){

                        }

                    }
                }
            }
        }
    }




    /**
     * restart activity
     * **/

    private fun restart(){
        val intent = intent
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        finish()
        startActivity(intent)
    }
    /**
     * sync data
     * **/
    private fun onTimeWorker(){
//        val work = OneTimeWorkRequest.Builder(MeasurementSyncWorker::class.java)
//            .build()

//        WorkManager.getInstance(this).enqueue(work)
    }
    companion object {
        const val RequestPermissionCode = 111
    }
}

