package com.trian.smartwatch.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.common.utils.analytics.analyzeBPM
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
import kotlinx.coroutines.Dispatchers
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

    val listDevicesUseCase:MutableState<List<Devices>> = mutableStateOf(arrayListOf())
    val listBloodPressure:MutableState<List<Measurement>> =mutableStateOf(arrayListOf())
    val listBloodOxygen:MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listRespiration:MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listHeartRate:MutableState<List<Measurement>> =mutableStateOf(arrayListOf())
    val listTemperature:MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listSleep:MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val connectedStatus:MutableState<String> = mutableStateOf("Disconnected")
    val connected:MutableState<Boolean> = mutableStateOf(false)


    /**
     * start scan device smartwatch nearby
     * **/
    fun scanDevices(){

        try {
            YCBTClient.startScanBle({ i: Int, scanDeviceBean: ScanDeviceBean? ->


                //if device found add to viewModel
                scanDeviceBean?.let {

                    viewModelScope.launch {
                        Log.e("OI2",it.deviceName)
                       val tempDevices = mutableListOf<Devices>()
                        tempDevices.addAll(listDevicesUseCase.value.map { it })

                        Devices(
                            scanDeviceBean.deviceName,
                            scanDeviceBean.deviceMac,
                            scanDeviceBean.deviceRssi
                        ).also {
                            if(!tempDevices.contains(it)) {
                                tempDevices.add(it)
                            }
                        }

                        listDevicesUseCase.value = tempDevices

                    }

                }

            }, 6)
        }catch (e:Exception){

            e.printStackTrace()
            Log.e("VM",e.message!!)
        }
    }

    //sync all data
    suspend fun getHistoryByDate(from:Long,to:Long){
        val member_id = persistence.getUser()!!.user_id
        listBloodOxygen.value = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_OXYGEN,
            member_id,
            from,
            to
        )
        listRespiration.value = measurementRepository.getHistory(
            SDKConstant.TYPE_RESPIRATION,
            member_id,
            from,
            to,
        )
        listTemperature.value = measurementRepository.getHistory(
            SDKConstant.TYPE_TEMPERATURE,
            member_id,
            from,
            to
        )
        listBloodPressure.value = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_PRESSURE,
            member_id,
            from,
            to
        )
        listHeartRate.value = measurementRepository.getHistory(
            SDKConstant.TYPE_HEARTRATE,
            member_id,
            from,
            to
        )
        listSleep.value = measurementRepository.getHistory(
            SDKConstant.TYPE_SLEEP,
            member_id,
            from,
            to
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
            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result)
                listBloodOxygen.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
                listRespiration.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )

                listTemperature.value = measurementRepository.getHistory(
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
            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result)
                listBloodPressure.value = measurementRepository.getHistory(
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
            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result)

                listHeartRate.value = measurementRepository.getHistory(
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

            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result)
                listSleep.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_SLEEP,
                    user_id,
                    getLastdayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }
        }

    }
}