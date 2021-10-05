package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.motionapps.kotlin_ecg_detectors.Detectors
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.local.Persistence
import com.trian.data.repository.MeasurementRepository
import com.trian.data.utils.*
import com.trian.domain.entities.Measurement
import com.trian.domain.models.Devices
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.Constants
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashMap
import javax.inject.Inject
import com.yucheng.ycbtsdk.AITools


/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository,
    private val persistence: Persistence,
    private val gson: Gson
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
        val memberId = persistence.getUser()!!.user_code
        listBloodOxygen.value = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_OXYGEN,
            memberId,
            from,
            to
        )

        listRespiration.value = measurementRepository.getHistory(
            SDKConstant.TYPE_RESPIRATION,
            memberId,
            from,
            to,
        )
        listTemperature.value = measurementRepository.getHistory(
            SDKConstant.TYPE_TEMPERATURE,
            memberId,
            from,
            to
        )
        listBloodPressure.value = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_PRESSURE,
            memberId,
            from,
            to
        )
        listHeartRate.value = measurementRepository.getHistory(
            SDKConstant.TYPE_HEARTRATE,
            memberId,
            from,
            to
        )
        listSleep.value = measurementRepository.getHistory(
            SDKConstant.TYPE_SLEEP,
            memberId,
            from,
            to
        )
    }

    //sync from smartwatch to local
    fun syncSmartwatch(){
        val userId = persistence.getUser()!!.user_code
        val device = persistence.getItemString(SDKConstant.KEY_LAST_DEVICE)!!
        val mac = gson.fromJson(device,Devices::class.java).mac
        YCBTClient.healthHistoryData(
            HISTORY.RESP_TEMP_SPO2
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = mutableListOf<Measurement>()
            list.forEach {


                it.extractBloodOxygen(userId, mac)
                    ?.let {

                        result.add(it)
                    }
                it.extractTemperature(userId, mac)
                    ?.let {

                        result.add(it)
                    }
                it.extractRespiration(userId, mac)
                    ?.let {
                        result.add(it)
                    }

                Log.e("RESULT",it.toString())

            }
            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result,false)


                listBloodOxygen.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    userId,
                    getLastDayTimeStamp(),
                    getTodayTimeStamp(),
                )


                listRespiration.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    userId,
                    getLastDayTimeStamp(),
                    getTodayTimeStamp(),
                )

                listTemperature.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_TEMPERATURE,
                    userId,
                    getLastDayTimeStamp(),
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
                it.extractBloodPressure(userId, mac)!!
            }

            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result,false)
                listBloodPressure.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_PRESSURE,
                    userId,
                    getLastDayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }

        }

        YCBTClient.healthHistoryData(
            HISTORY.HR
        ) { i, v, data ->//get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = list.map {
                it.extractHeartRate(userId, mac)!!
            }
            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result,false)

                listHeartRate.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_HEARTRATE,
                    userId,
                    getLastDayTimeStamp(),
                    getTodayTimeStamp(),
                )
            }
        }

      //  step
        YCBTClient.healthHistoryData(HISTORY.STEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            list.forEach {

            }
        }

        YCBTClient.healthHistoryData(
            HISTORY.SLEEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = list.map {
                it.extractSleepMonitor(userId, mac)!!
            }

            viewModelScope.launch(context = Dispatchers.IO) {
                measurementRepository.saveLocalMeasurement(result,false)
                listSleep.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_SLEEP,
                    userId,
                    getLastDayTimeStamp(),
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