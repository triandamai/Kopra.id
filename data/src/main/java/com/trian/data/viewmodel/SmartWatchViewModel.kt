package com.trian.data.viewmodel

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motionapps.kotlin_ecg_detectors.Detectors
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.EcgUtils
import com.trian.common.utils.utils.SamplesECG
import com.trian.common.utils.utils.getLastdayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.component.utils.getBeat
import com.trian.data.local.Persistence
import com.trian.data.repository.IMeasurementRepository
import com.trian.data.utils.*
import com.trian.domain.entities.Measurement
import com.trian.domain.models.Devices
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.Constants
import com.yucheng.ycbtsdk.Response.BleDataResponse
import com.yucheng.ycbtsdk.Response.BleRealDataResponse
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject
import com.yucheng.ycbtsdk.AITools
import com.yucheng.ycbtsdk.Utils.i
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset


/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val measurementRepository: IMeasurementRepository,
    private val persistence: Persistence,
   ) : ViewModel(){

    val listDevicesUseCase: MutableState<List<Devices>> = mutableStateOf(arrayListOf())
    val listBloodPressure: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listBloodOxygen: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listRespiration: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listHeartRate: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listTemperature: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listSleep: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val connectedStatus: MutableState<String> = mutableStateOf("Disconnected")
    val connected: MutableState<Boolean> = mutableStateOf(false)

    val ecgWave: MutableState<Float> = mutableStateOf(0f)


    /**
     * start scan device smartwatch nearby
     * **/
    fun scanDevices(){

        try {
            YCBTClient.startScanBle({ i: Int, scanDeviceBean: ScanDeviceBean? ->


                //if device found add to viewModel
                scanDeviceBean?.let {

                    viewModelScope.launch {
                        Log.e("OI2", it.deviceName)
                        val tempDevices = mutableListOf<Devices>()
                        tempDevices.addAll(listDevicesUseCase.value.map { it })

                        Devices(
                            scanDeviceBean.deviceName,
                            scanDeviceBean.deviceMac,
                            scanDeviceBean.deviceRssi
                        ).also {
                            if (!tempDevices.contains(it)) {
                                tempDevices.add(it)
                            }
                        }

                        listDevicesUseCase.value = tempDevices

                    }

                }

            }, 6)
        }catch (e:Exception){

            e.printStackTrace()
            Log.e("VM", e.message!!)
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
        YCBTClient.healthHistoryData(
            HISTORY.RESP_TEMP_SPO2
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = mutableListOf<Measurement>()
            list.forEach {



                it.extractBloodOxygen(user_id, mac)
                    ?.let {

                        result.add(it)
                    }
                it.extractTemperature(user_id, mac)
                    ?.let {
                        result.add(it)
                    }
                it.extractRespiration(user_id, mac)
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

        YCBTClient.healthHistoryData(
            HISTORY.BPM
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = list.map {
                it.extractBloodPressure(user_id, mac)!!
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

        YCBTClient.healthHistoryData(
            HISTORY.HR
        ) { i, v, data ->//get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = list.map {
                it.extractHeartRate(user_id, mac)!!
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

        YCBTClient.healthHistoryData(
            HISTORY.SLEEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = list.map {
                it.extractSleepMonitor(user_id, mac)!!
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

    //start ecg test
    fun startEcgTest(){
       val  aiTools = AITools.getInstance()
        aiTools.init()
        YCBTClient.appEcgTestStart({
                code, ratio, resultMap ->
                Log.e("VM","BLE DATA RESPONSE code -> $code ratio -> $ratio result -> ${resultMap.toString()}")
        }
        ) {
                dataType, resultMap ->
          //  Log.e("VM REAL $dataType",resultMap.toString())
           when(dataType){
               Constants.DATATYPE.Real_UploadHeart->{
                   Log.e("VM REAL HEART",resultMap.toString())
               }
               Constants.DATATYPE.Real_UploadBlood->{
                   Log.e("VM REAL Blood",resultMap.toString())
               }
               Constants.DATATYPE.Real_UploadECG->{

                       val tData = resultMap.get("data") as ArrayList<Int>

                        try {

//                          val inv = SamplesECG().getSamplesInmV(aa.toIntArray())

                            val detector = Detectors(50.0)//signal frequency
                            val result = detector.christovDetector(tData.map { it.toDouble() }.toDoubleArray())

                          //  tData.map {
                                ecgWave.value = (0..100).random().toFloat()
                           // }
//                            result.map {
//                                Log.e("RESULT",it.toString())
//                                ecgWave.value = it.toFloat()*10
//                            }


                        }catch (e:Exception){
                            e.printStackTrace()

                        }



//                       val result = mutableListOf<Float>()
//                       for (i in tData.indices){
//                           if(tData[i] > 0){
//                               val calc = (tData[i].toDouble() / 60000) * 100
//                               result.add((calc / 2).toFloat())
//                           }else{
//                               val calc = (tData[i].inv().toDouble() / 60000) * 100
//                               result.add((calc *2).toFloat())
//                           }
//
//                       }








               }
               Constants.DATATYPE.Real_UploadPPG->{
                   val ppgBytes = resultMap.get("data")
                   Log.e("VM REAL data ppg",ppgBytes.toString())
               }
                Constants.DATATYPE.Real_UploadECGHrv->{
                    val tData = resultMap.get("data")

                    Log.e("VM REAL data hrv",tData.toString())

                }
               Constants.DATATYPE.Real_UploadECGRR->{
                   val tData = resultMap.get("data")
                   Log.e("VM REAL data rr",tData.toString())

               }
               else ->{
                   Log.e("VM REAL $dataType",resultMap.toString())
               }
           }
        }
    }

    fun endEcg(){
        YCBTClient.appEcgTestEnd { i, fl, hashMap ->

        }
    }
}