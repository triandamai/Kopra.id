package com.trian.kopra

import android.content.Intent
import android.net.Uri
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
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument

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
import com.trian.component.ui.theme.GreenPrimary

import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.viewmodel.MainViewModel
import com.trian.kopra.ui.pages.*

import com.trian.kopra.ui.pages.auth.*
import com.trian.kopra.ui.pages.chat.PageChatScreen
import com.trian.kopra.ui.pages.reminder.PageCreateReminder
import com.trian.kopra.ui.pages.reminder.PageListReminder
import com.trian.kopra.ui.pages.store.*
import com.trian.kopra.ui.pages.transaction.*
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

            fun setColorStatusBar(color:Color){
                systemUiController.setStatusBarColor(
                    color = color,
                    darkIcons =
                    if(color == Color.White)
                        true
                    else
                        useDarkIcon
                )
            }

            LaunchedEffect(Unit) {
                systemUiController.setStatusBarColor(
                    color = Color.White,
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
                            setColorStatusBar(GreenPrimary)
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
                        composable("${Routes.CHATSCREEN}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },
                            arguments = listOf(navArgument("slug"){ type = NavType.StringType})
                            ){
                            setColorStatusBar(GreenPrimary)
                            PageChatScreen(
                                navHostController = navHostController,
                                scope = coroutineScope,
                                mainViewModel = mainViewModel
                            )
                        }
                        composable("${Routes.DETAIL_TOKO}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageDetailStore(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.DETAIL_MY_TOKO,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageDetailStoreSeller(
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
                            PageUpdateProfile(
                                permissionUtils = permissionUtils,
                                nav = navHostController,
                                mainViewModel = mainViewModel,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.COMPLETE_PROFILE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageCompleteProfile(
                                permissionUtils = permissionUtils,
                                nav = navHostController,
                                mainViewModel = mainViewModel,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.CREATE_TOKO,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(GreenPrimary)
                            PageCreateToko(
                                mainViewModel=mainViewModel,
                                permissionUtils=permissionUtils,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.UPDATE_TOKO,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(GreenPrimary)
                            PageUpdateToko(
                                mainViewModel=mainViewModel,
                                permissionUtils=permissionUtils,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.LIST_STORE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(GreenPrimary)
                            PageListStore(mainViewModel = mainViewModel,navHostController = navHostController,scope = coroutineScope)
                        }

                        composable("${Routes.OTP_VIEW}/{phone}",
                        enterTransition = {
                            _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        },
                            arguments = listOf(navArgument("phone"){ type = NavType.StringType})
                        ){
                            setColorStatusBar(Color.White)
                            PageOtp(
                                navHostController = navHostController,
                                scope = coroutineScope,
                                mainViewModel = mainViewModel
                            )
                        }
                        composable(Routes.SEARCH_STORE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageSearchStore(
                                navHostController = navHostController,
                                scope = coroutineScope,
                                mainViewModel = mainViewModel
                            )
                        }
                        composable(Routes.ABOUT,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(GreenPrimary)
                            PageAbout(
                                navHostController = navHostController,
                            ){
                                phone ->
                                openWhatsApp(phone)
                            }
                        }
                        composable("${Routes.DETAIL_PRODUCT}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },
                            arguments = listOf(navArgument("slug"){ type = NavType.StringType})
                        ){
                            setColorStatusBar(Color.White)
                            PageDetailProduct(
                                mainViewModel=mainViewModel,
                                nav=navHostController,
                                scope=coroutineScope,
                            )
                        }
                        composable("${Routes.DETAIL_PRODUCT_SELLER}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },
                            arguments = listOf(navArgument("slug"){ type = NavType.StringType})
                        ){
                            setColorStatusBar(Color.White)
                            PageDetailProductSeller(
                                mainViewModel=mainViewModel,
                                nav=navHostController,
                                scope=coroutineScope,
                            )
                        }
                        composable("${Routes.CHECKOUT}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },
                            arguments = listOf(navArgument("slug"){ type = NavType.StringType})){
                            setColorStatusBar(Color.White)
                            PageCheckout(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = coroutineScope
                            )
                        }
                        composable("${Routes.ORDER_INFORMATION}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }, arguments = listOf(navArgument("slug"){ type = NavType.StringType})){
                            setColorStatusBar(Color.White)
                            PageOrderInformation(nav = navHostController)
                        }

                        composable(Routes.ADD_PRODUCT,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageCreateProduct(
                            permissionUtils=permissionUtils,
                            mainViewModel=mainViewModel,
                            navHostController= navHostController
                            )
                        }
                        composable(Routes.CREATE_REMINDER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageCreateReminder(
                                nav = navHostController,
                                mainViewModel = mainViewModel
                            )
                        }
                        composable(Routes.LIST_REMINDER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageListReminder(
                                nav = navHostController,
                                mainViewModel = mainViewModel
                            )
                        }
                        composable("${Routes.UPDATE_PRODUCT}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },arguments = listOf(navArgument("slug"){ type = NavType.StringType})){
                            setColorStatusBar(Color.White)
                            PageUpdateProduct(
                                permissionUtils=permissionUtils,
                                mainViewModel=mainViewModel,
                                navHostController= navHostController
                            )
                        }
                        composable("${Routes.DETAIL_ORDER}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },arguments = listOf(navArgument("slug"){ type = NavType.StringType})){
                            setColorStatusBar(Color.White)
                            PageDetailOrder(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope=coroutineScope

                            )
                        }
                        composable("${Routes.DETAIL_ORDER_SELLER}/{slug}",
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            },arguments = listOf(navArgument("slug"){ type = NavType.StringType})){
                            setColorStatusBar(Color.White)
                            PageDetailOrderSeller(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope=coroutineScope

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
                                ){}
                            }
                            composable(Routes.Dashboard.LIST_TRANSACTION,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(GreenPrimary)
                                PageDashboard(
                                    page=Routes.Dashboard.LIST_TRANSACTION,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                ){}
                            }
                            composable(Routes.Dashboard.PROFILE,
                                enterTransition = {
                                        _,_ ->
                                    fadeIn(animationSpec = tween(600))
                                }){
                                setColorStatusBar(GreenPrimary)
                                PageDashboard(
                                    page=Routes.Dashboard.PROFILE,
                                    mainViewModel = mainViewModel,
                                    navHostController = navHostController,
                                    scope = coroutineScope
                                ){
                                    restart()
                                }
                            }

                        }

                        bottomSheet(Routes.SHEET_CANCEL_ORDER,){

                        }

                    }
                }
            }
        }
    }


    private fun openWhatsApp(phone:String){
        val message = "halo admin kopra.id"
         Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send/?phone=${phone}&text=${message}&app_absent=0")).also {
             startActivity(it)
         }
    }

    private fun sendOTP(otp:String,navHostController: NavHostController){
        mainViewModel.sendOTP(otp,this){
                success: Boolean, _, message: String ->
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

