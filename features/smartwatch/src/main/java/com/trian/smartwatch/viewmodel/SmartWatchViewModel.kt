package com.trian.smartwatch.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.data.repository.ICexupRepository
import com.trian.domain.models.Devices
import com.trian.domain.usecase.DevicesUseCase
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.Response.BleConnectResponse
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val cexupRepository: ICexupRepository
) :ViewModel(){

    val listDevices:MutableState<DevicesUseCase> = mutableStateOf(DevicesUseCase(false))
    /**
     * start scan device smartwatch nearby
     * **/
    fun scanDevices(){

        listDevices.value.scanning = true
        try {
            YCBTClient.startScanBle({ i: Int, scanDeviceBean: ScanDeviceBean? ->

                listDevices.value.scanning = false
                //if device found add to viewModel
                scanDeviceBean?.let {

                    //E80DL 6B56 bulat biru
                    // E80DL 347D bulat hitam
                    // E86 AB5C Hitam kotak;
                    //E86 5D66 Merah kotak
                    Log.e("Scan",it.deviceMac)
                    Log.e("VM",scanDeviceBean!!.deviceName)
                    viewModelScope.launch {
                        listDevices.value= DevicesUseCase(
                            scanning = false,
                            devices = mutableListOf(Devices(
                                scanDeviceBean.deviceName,
                                scanDeviceBean.deviceMac,
                                scanDeviceBean.deviceRssi)
                            )
                        )
                    }

                }

            }, 6)
        }catch (e:Exception){
            listDevices.value.scanning = false
            e.printStackTrace()
            Log.e("VM",e.message!!)
            viewModelScope.launch {
                listDevices.value = DevicesUseCase(
                    scanning = false,
                    error="${e.message}"
                )
            }
        }
    }
}