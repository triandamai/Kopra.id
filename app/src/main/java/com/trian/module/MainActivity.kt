package com.trian.module

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
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
import com.trian.component.bottomsheet.*
import com.trian.module.ui.pages.*
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.local.Persistence
import com.trian.data.viewmodel.MainViewModel
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.data.worker.MeasurementSyncWorker
import com.trian.domain.models.Hospital
import com.trian.domain.models.ServiceType
import com.trian.module.ui.pages.auth.*
import com.trian.smartwatch.SmartWatchActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

/**
 * Main Activity
 * Author PT Cexup Telemedicine
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
    private val viewModel: MainViewModel by viewModels()
    private val telemedicineViewModel: TelemedicineViewModel by viewModels()
    @Inject lateinit var permissionUtils:PermissionUtils
    @Inject lateinit var persistence: Persistence


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
                                nav=navHostController,scope=coroutineScope,
                                viewModel = viewModel,
                            )
                        }
                        composable(Routes.ONBOARD,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageOnBoarding(nav=navHostController,scope = coroutineScope)
                        }
                        composable(Routes.LOGIN,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageLogin(nav=navHostController,scope = coroutineScope,viewModel = viewModel)
                        }
                        composable(Routes.FORGET_PASSWORD,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageForgetPassword(navHostController)
                        }
                        composable(Routes.SUCCESS_FORGET,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            setColorStatusBar(Color.White)
                            PageCompleteForget(navHostController)
                        }

                        navigation(startDestination = Routes.Dashboard.HOME ,route = Routes.DASHBOARD){
                            composable(Routes.Dashboard.HOME){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.Dashboard.HOME,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    restartActivity = {}
                                )
                            }
                            composable(Routes.Dashboard.ACCOUNT){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.Dashboard.ACCOUNT,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    restartActivity = {restart()}
                                )
                            }
                            composable(Routes.Dashboard.LIST_HOSPITAL){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.Dashboard.LIST_HOSPITAL,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    restartActivity = {}
                                )
                            }
                            composable(Routes.Dashboard.LIST_ORDER){
                                PageDashboard(
                                    nav=navHostController,
                                    scope=coroutineScope,
                                    viewModel=viewModel,
                                    toFeature = {goToFeature(it,navHostController)},
                                    page=Routes.Dashboard.LIST_ORDER,
                                    changeStatusBar = {setColorStatusBar(it)},
                                    restartActivity = {}
                                )
                            }
                        }

                        composable(Routes.DETAIL_HOSPITAL,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            DetailHospital( nav = navHostController)
                        }
                        composable(Routes.REGISTER,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageRegister(
                                nav=navHostController,
                                viewModel = viewModel,
                                scope = coroutineScope
                            )
                        }
                        composable(Routes.CONFIRMATION_REGISTRATION,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageRegisterConfirmation(
                                viewModel = viewModel,
                                scope = coroutineScope,
                                nav = navHostController
                            )
                        }
                        composable(Routes.DETAIL_HEALTH,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageDetailHealthStatus(
                                viewModel = viewModel,
                                nav=navHostController,
                                scope = coroutineScope,
                                changeStatusBar = {setColorStatusBar(it)},
                                offReminder = {}
                            )
                        }
                        composable(Routes.MOBILE_NURSE,
                            enterTransition = {
                                    _,_ ->
                                fadeIn(animationSpec = tween(2000))
                            }){
                            PageListFeature()
                        }
                        composable(Routes.DETAIL_DOCTOR, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageDetailDoctor(nav =navHostController)
                        }
                        composable(Routes.PRIVACY_POLICY, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PagePrivacyPolice()
                        }
                        composable(Routes.DETAIL_ORDER, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageDetailOrder(nav = navHostController,)
                        }
                        composable(Routes.ASSESMENT, enterTransition = {
                                _,_ ->
                            fadeIn(animationSpec = tween(2000))
                        }){
                            PageKuisioner()
                        }
                        bottomSheet(Routes.SHEET_SERVICE,){
                            BottomSheetServices(){
                                goToFeature(it,navHostController)
                            }
                        }
                        bottomSheet(Routes.SHEET_CANCEL_ORDER,){
                            BottomSheetCancelOrder()
                        }
                        bottomSheet(Routes.SHEET_FORM_ORDER,){
                            BottomSheetFormOrder(scope = coroutineScope,nav = navHostController)
                        }
                        bottomSheet(Routes.SHEET_PRIVACY_POLICY){
                            BottomSheetPrivacyPolicy(nav=navHostController,permissionUtils = permissionUtils)
                        }
                        bottomSheet(Routes.SHEET_DETAIL_HOSPITAL){
                            BottomSheetHospital(HospitalLogo = painterResource(id = R.drawable.logo_cexup), hospital = Hospital(
                                id = 1,
                                slug = "rs-tele-cexup",
                                description = "",
                                thumb = "",
                                thumb_original = "",
                                name = "RS Tele Cexup",
                                address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                                others = "",
                            ),)
                        }
                    }
                }
            }
        }
    }

    /**
     * start activity to each feature
     * **/
   private fun goToFeature(type: ServiceType,nav:NavHostController){
        when(type){
            ServiceType.HEALTH_TRACKER->{
                startActivity(
                    Intent(this@MainActivity,SmartWatchActivity::class.java)
                )
            }
            ServiceType.MEDICAL_RECORD->{}
            ServiceType.MEDICINE->{}
            ServiceType.COVID_MONITORING->{}
            ServiceType.MEDICAL_CHECKUP->{}
            ServiceType.RESERVATION->{}
            ServiceType.SHOP->{}
            ServiceType.TELECONSULTATION->{}
            ServiceType.MOBILE_NURSE->{
                nav.navigate(Routes.MOBILE_NURSE)
            }
            else->{}
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
        val work = OneTimeWorkRequest.Builder(MeasurementSyncWorker::class.java)
            .build()

        WorkManager.getInstance(this).enqueue(work)
    }

}

