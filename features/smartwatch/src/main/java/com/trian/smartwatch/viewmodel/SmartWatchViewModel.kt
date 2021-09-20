package com.trian.smartwatch.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.getLastdayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.local.Persistence
import com.trian.data.repository.IMeasurementRepository
import com.trian.domain.entities.Measurement
import com.trian.domain.models.Devices
import com.trian.domain.usecase.DevicesUseCase
import com.trian.domain.usecase.MeasurementUseCase
import com.trian.smartwatch.utils.*
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject
/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val measurementRepository: IMeasurementRepository,
    private val persistence: Persistence
) :ViewModel(){

    val listDevices:MutableState<DevicesUseCase> = mutableStateOf(DevicesUseCase(false))
    val listBloodPressure:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
    val listBloodOxygen:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
    val listRespiration:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
    val listHeartRate:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
    val listTemperature:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
    val listSleep:MutableState<MeasurementUseCase> = mutableStateOf(MeasurementUseCase(true))
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
            listDevices.value.error = "${e.message}"
            e.printStackTrace()
            Log.e("VM",e.message!!)
        }
    }

    //sync all data
    fun getHistoryByDate(from:Long,to:Long){
        val member_id = persistence.getUser()!!.user_id
        listBloodOxygen.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_OXYGEN,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
        listRespiration.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_RESPIRATION,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
        listTemperature.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_TEMPERATURE,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
        listBloodPressure.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_PRESSURE,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
        listHeartRate.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_HEARTRATE,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
        listSleep.value.measurements = measurementRepository.getHistory(
            SDKConstant.TYPE_SLEEP,
            member_id,
            getLastdayTimeStamp(),
            getTodayTimeStamp(),
        )
    }

    //sync from smartwatch to local
    fun syncSmartwatch(){
        val user_id = persistence.getUser()!!.user_id
        val mac = persistence.getItemString(SDKConstant.KEY_LAST_DEVICE)!!
        YCBTClient.healthHistoryData(HISTORY.RESP_TEMP_SPO2
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = mutableListOf<Measurement>()
           list.forEach {

               it.extractBloodOxygen(user_id,mac)
                   ?.let {
                       result.add(it)
                   }
               it.extractTemperature(user_id,mac)
                   ?.let {
                       result.add(it)
                   }
               it.extractRespiration(user_id,mac)
                   ?.let {
                       result.add(it)
                   }

            }
            viewModelScope.launch {
                measurementRepository.saveLocalMeasurement(result)
                listBloodOxygen.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
                listRespiration.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )

                listTemperature.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_TEMPERATURE,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }

        }

        YCBTClient.healthHistoryData(HISTORY.BPM
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

           val result = list.map {
               it.extractBloodPressure(user_id,mac)!!
            }
            viewModelScope.launch {
                measurementRepository.saveLocalMeasurement(result)
                listBloodPressure.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_PRESSURE,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }

        }

        YCBTClient.healthHistoryData(HISTORY.HR
        ) { i, v, data ->//get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result =list.map {
               it.extractHeartRate(user_id,mac)!!

            }
            viewModelScope.launch {
                measurementRepository.saveLocalMeasurement(result)

                listHeartRate.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_HEARTRATE,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }
        }

        //step
//        YCBTClient.healthHistoryData(HISTORY.STEP
//        ) { i, v, data ->
//            //get data from smartwatch
//            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
//            list.forEach {
//
//            }
//        }

        YCBTClient.healthHistoryData(HISTORY.SLEEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = list.map {
                it.extractSleepMonitor(user_id,mac)!!
            }

            viewModelScope.launch {
                measurementRepository.saveLocalMeasurement(result)
                listSleep.value.measurements = measurementRepository.getHistory(
                    SDKConstant.TYPE_SLEEP,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }
        }

    }
}