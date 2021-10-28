package com.trian.kopra

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
import androidx.navigation.NavHostController

import androidx.navigation.plusAssign
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
import com.trian.data.viewmodel.MainViewModel
import com.trian.kopra.ui.pages.*

import com.trian.kopra.ui.pages.auth.*
import com.trian.kopra.ui.pages.main.PageListTransaction
import com.trian.module.ui.pages.auth.PageRegister
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
    private val mainViewModel: MainViewModel by viewModels()


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
                        startDestination = Routes.SPLASH
                    ){

                        composable(Routes.SPLASH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ){
                            setColorStatusBar(Color.White)
                            PageSplashScreen(
                                mainViewModel = mainViewModel,
                                nav=navHostController,
                                scope=coroutineScope,
                            )
                        }
                        composable(Routes.LOGIN,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageLogin(
                                mainViewModel = mainViewModel,
                                nav = navHostController,
                                scope = coroutineScope
                            ){
                                navHostController.navigate(Routes.OTP_VIEW)
                                sendOTP(it,navHostController)
                            }
                        }
                        composable(Routes.REGISTER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageRegister(nav = navHostController)
                        }
                        composable(Routes.HISTORY_TRANSACTION,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageHistoryTransaction(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.DETAIL_TRANSACTION,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageDetailTransaction(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.UPDATE_PROFILE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageUpdateProfile()
                        }
                        composable(Routes.OTP_VIEW,
                        enterTransition = {
                            _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            setColorStatusBar(Color.White)
                            PageOtp(
                                navHostController = navHostController,
                                scope = coroutineScope,
                                mainViewModel = mainViewModel
                            )
                        }
                        navigation(startDestination = Routes.Dashboard.HOME ,route = Routes.DASHBOARD){
                            composable(Routes.Dashboard.HOME,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(Color.White)
                                PageDashboard(
                                    page=Routes.Dashboard.HOME,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                )
                            }
                            composable(Routes.Dashboard.LIST_CHAT,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(Color.White)
                                PageDashboard(
                                    page=Routes.Dashboard.LIST_CHAT,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                )
                            }
                            composable(Routes.Dashboard.LIST_TRANSACTION,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(Color.White)
                                PageDashboard(
                                    page=Routes.Dashboard.LIST_TRANSACTION,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                )
                            }
                            composable(Routes.Dashboard.PROFILE,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(Color.White)
                                PageDashboard(
                                    page=Routes.Dashboard.PROFILE,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                )
                            }

                        }

                        bottomSheet(Routes.SHEET_CANCEL_ORDER,){

                        }

                    }
                }
            }
        }
    }



    private fun sendOTP(otp:String,navHostController: NavHostController){
        mainViewModel.sendOTP(otp,this){
            success: Boolean, message: String ->
            if(success){
                navHostController.navigate(Routes.UPDATE_PROFILE)
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

}

