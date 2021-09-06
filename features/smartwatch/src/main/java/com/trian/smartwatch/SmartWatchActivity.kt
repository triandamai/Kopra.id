package com.trian.smartwatch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.bottomsheet.BottomSheetPermission
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.smartwatch.viewmodel.SmartWatchViewModel
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.Constants
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.HashMap

import javax.inject.Inject
import android.Manifest

/**
 * Main Smartwatch Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@AndroidEntryPoint
class SmartWatchActivity : ComponentActivity() {

    companion object {
        val listPermission= arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN
            )
    }

    private val vm:SmartWatchViewModel by viewModels()
    @Inject lateinit var permissionUtils: PermissionUtils

    override fun onStart() {
        super.onStart()

    }
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
            val scope:CoroutineScope = rememberCoroutineScope()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcon
                )
                //show dialog if false
                val hasPermission = permissionUtils.checkHasPermission()
                if(!hasPermission){
                    scope.launch {
                        scaffoldState.show()
                    }
                }
            }
            TesMultiModuleTheme {
              ModalBottomSheetLayout(
                  sheetState = scaffoldState,
                  sheetContent = {
                      //ini isi bottom sheetnya
                      BottomSheetPermission {
                          //when allow button clicked
                          onClickRequestPermission()
                      }
                  }) {
                  Scaffold {
                      //ini conten
                  }
              }
            }
        }

        //should show dialog that requestd permission if false
        if(permissionUtils.checkHasPermission()){
            initBle()
            startScanDevice()
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        YCBTClient.disconnectBle()
    }


    /**
     * Permission launcher
     * */
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            isGranted->

                Log.e("Permission is","${isGranted.toString()}")

        }
    /**
     * start permission
     * **/
    fun onClickRequestPermission(){
        when(
            permissionUtils.checkHasPermission()
        ){
            true->{}
            false->{
                requestPermissionLauncher.launch(
                    listPermission
                )
            }
        }
    }
    /**
     * initialize the sdk
     *
     * **/
    private fun initBle(){
        YCBTClient.initClient(this,false)
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
     * start scan device smartwatch nearby
     * **/
    private fun startScanDevice(){
        Log.e("Start Scan","yeah")
        YCBTClient.startScanBle({ i: Int, scanDeviceBean: ScanDeviceBean? ->
            //if device found add to viewModel
            scanDeviceBean?.let {
                //E80DL 6B56 bulat biru
                // E80DL 347D bulat hitam
                // E86 AB5C Hitam kotak;
                //E86 5D66 Merah kotak
                Log.e("Scan",it.deviceMac)
            }?: run {

            }

        }, 6)
    }
    /**
     * start connect to device
     * @param mac similar to F8:DC:9G:4D
     * **/
    fun connectToDevice(mac:String){
        YCBTClient.connectBle(mac){
            when(it){
                Constants.CODE.Code_OK->{}
                Constants.CODE.Code_Failed->{}
                Constants.CODE.Code_TimeOut->{}
                else->{}
            }
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