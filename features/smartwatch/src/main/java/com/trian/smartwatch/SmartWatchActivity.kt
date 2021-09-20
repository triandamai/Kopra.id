
package com.trian.smartwatch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.bottomsheet.BottomSheetPermission
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.smartwatch.viewmodel.SmartWatchViewModel
import com.yucheng.ycbtsdk.Constants
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.HashMap

import javax.inject.Inject
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import androidx.navigation.plusAssign
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.gson.Gson
import com.trian.common.utils.route.Routes
import com.trian.common.utils.sdk.SDKConstant
import com.trian.component.bottomsheet.BottomSheetDevices
import com.trian.component.ui.theme.LightBackground
import com.trian.data.local.Persistence
import com.trian.smartwatch.services.SmartwatchService
import com.trian.smartwatch.services.SmartwatchWorker
import java.util.concurrent.TimeUnit

/**
 * Main Smartwatch Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@AndroidEntryPoint
class SmartWatchActivity : ComponentActivity() {


    private val vm:SmartWatchViewModel by viewModels()
    @Inject lateinit var permissionUtils: PermissionUtils
    @Inject lateinit var persistence: Persistence
    @Inject lateinit var gson:Gson

    override fun onStart() {
        super.onStart()

    }
    @ExperimentalMaterialNavigationApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //make statusbar custom color
            val systemUiController = rememberSystemUiController()
            val useDarkIcon = MaterialTheme.colors.isLight
            val scaffoldState = rememberModalBottomSheetState(
                ModalBottomSheetValue.Hidden
            )
            val navHostController = rememberAnimatedNavController()
            val coroutineScope = rememberCoroutineScope()
            val bottomSheetNavigator = rememberBottomSheetNavigator()


            //add bottomsheet to a navigation
            navHostController.navigatorProvider += bottomSheetNavigator
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = LightBackground,
                    darkIcons = useDarkIcon
                )

            }

            //dynamic change statusbar color
            fun setColorStatusBar(color:Color){
                systemUiController.setStatusBarColor(
                    color = color,
                )
            }

            // start permission
             fun onClickRequestPermission(){
                when(
                    permissionUtils.hasPermission()
                ){
                    true->{}
                    false->{

                        requestPermissionLauncher.launch(
                            permissionUtils.listPermission()
                        )
                    }
                }
            }
            TesMultiModuleTheme {
                ModalBottomSheetLayout(
                    bottomSheetNavigator
                ) {
                    AnimatedNavHost(
                        navController = navHostController,
                        startDestination = Routes.SMARTWATCH_ROUTE.MAIN
                    ) {
                        composable(Routes.SMARTWATCH_ROUTE.MAIN,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                           SideEffect {
                               //should show dialog that requestd permission if false
                               if (!permissionUtils.hasPermission()){
                                 navHostController.navigate(Routes.SMARTWATCH_ROUTE.BOTTOM_SHEET_PERMISSION)
                               }
                           }
                            SmartWatchUi(nav = navHostController,viewModel = vm,scope = coroutineScope){
                                navHostController.navigate(Routes.SMARTWATCH_ROUTE.BOTTOM_SHEET_DEVICES)
                                vm.scanDevices()
                            }

                        }

                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_BPM,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page=Routes.SMARTWATCH_ROUTE.DETAIL_BPM)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_SPO2,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page=Routes.SMARTWATCH_ROUTE.DETAIL_SPO2)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page = Routes.SMARTWATCH_ROUTE.DETAIL_HEART_RATE)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page = Routes.SMARTWATCH_ROUTE.DETAIL_RESPIRATION)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page = Routes.SMARTWATCH_ROUTE.DETAIL_TEMPERATURE)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_ECG,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page = Routes.SMARTWATCH_ROUTE.DETAIL_ECG)
                        }
                        composable(Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP,
                            enterTransition = { _, _ ->
                                fadeIn(animationSpec = tween(2000))
                            }
                        ) {
                            DetailSmartWatchUi(onClickCalender = {},page = Routes.SMARTWATCH_ROUTE.DETAIL_SLEEP)
                        }
                        bottomSheet(Routes.SMARTWATCH_ROUTE.BOTTOM_SHEET_DEVICES){

                            val devices by vm.listDevicesUseCase

                            BottomSheetDevices(
                                device=devices,
                                scope = coroutineScope,
                                modalBottomSheetState = scaffoldState
                            ){
                                navHostController.popBackStack()
                                persistence.setItem(SDKConstant.KEY_LAST_DEVICE,gson.toJson(it))
                                connectToDevice(it.mac){it1->
                                    when(it1){
                                        Constants.CODE.Code_OK->{
                                            vm.connectedStatus.value = "Connected ${it.name}"
                                            vm.syncSmartwatch()
                                        }
                                        Constants.CODE.Code_Failed->{
                                            vm.connectedStatus.value = "Failed Connect ${it.name}"
                                        }
                                        Constants.CODE.Code_TimeOut->{
                                            vm.connectedStatus.value = "TimeOut ${it.name}"
                                        }
                                        else->{
                                            vm.connectedStatus.value = "Disconnected"
                                        }
                                    }
                                }
                            }
                        }
                        bottomSheet(Routes.SMARTWATCH_ROUTE.BOTTOM_SHEET_PERMISSION){
                            BottomSheetPermission {
                                navHostController.popBackStack().also {
                                    onClickRequestPermission()
                                }

                            }
                        }
                    }
                }
            }
        }

        initBle()
        startServiceViaWorker()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {

        startServiceViaWorker()
        super.onDestroy()
    }

    /**
     * Permission launcher
     * */
   private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
                isGranted->

            Log.e("Permission is", isGranted.toString())

        }


    /**
     * initialize the sdk
     *
     * **/
    private fun initBle(){
        YCBTClient.registerBleStateChange {
            if(it == Constants.BLEState.Disconnect){

            }
            if(it == Constants.BLEState.Connected){

            }
            if(it == Constants.BLEState.ReadWriteOK){

            }
        }
        YCBTClient.deviceToApp { i, hashMap ->
            Log.e(SmartWatchActivity::class.java.simpleName,hashMap.toString())
        }
    }

    /**
     * start connect to device
     * @param mac similar to F8:DC:9G:4D
     * **/
    fun connectToDevice(mac:String,connectState:(code:Int)->Unit){
        YCBTClient.connectBle(mac){
            connectState(it)
        }
    }

    /**
     * send basic setting/default to smartwatch
     * */
    fun sendBaseSetting(){
        YCBTClient.settingLanguage(
            0x00
        ) { i: Int, v: Float, hashMap: HashMap<*, *>? -> }
        /****
         * HR monitoring mode setting
         * @param mode 0x00: Manual 0x01: Auto mode
         * @param intervalTime HR monitoring internal in Auto(Min)
         * @param dataResponse
         */
//        YCBTClient.settingHeartMonitor(0x001, 10, (i, v, hashMap) -> {
//
//        });
    }

    //start service
    fun startService(){
        if(!SmartwatchService.isServiceRunning){
            Intent(
                this,
                SmartwatchService::class.java
            ).also {
                ContextCompat.startForegroundService(
                    this,
                    it
                )
            }
        }
    }

    fun stopService(){
        if(SmartwatchService.isServiceRunning){
            Intent(this,SmartwatchService::class.java)
                .also {
                    stopService(it)
                }
        }
    }

    fun startServiceViaWorker(){
        val workManager= WorkManager.getInstance(this)

        val request =PeriodicWorkRequest.Builder(
            SmartwatchWorker::class.java,
            16,
            TimeUnit.MINUTES
        )
            .build()
        workManager.enqueueUniquePeriodicWork(
            "SMARTWATATCHWORKER",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )


    }


}

@Composable
fun Greeting6(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    TesMultiModuleTheme {
        Greeting6("Android")
    }
}