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
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.repository.MeasurementRepository
import com.trian.data.utils.*
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.User
import com.trian.domain.models.Devices
import com.trian.domain.models.bean.HistoryDatePickerModel
import com.yucheng.ycbtsdk.Bean.ScanDeviceBean
import com.yucheng.ycbtsdk.Constants
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val gson: Gson,
    private val dispatcherProvider: DispatcherProvider,
   ) : ViewModel(){

    /**
     *
     * **/
    val listDevicesUseCase: MutableState<List<Devices>> = mutableStateOf(arrayListOf())
    val listBloodPressure: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listBloodOxygen: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listRespiration: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listHeartRate: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listTemperature: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listSleep: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())
    val listStep: MutableState<List<Measurement>> = mutableStateOf(arrayListOf())

    //
    var currentDate:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    //
    val connectedStatus: MutableState<String> = mutableStateOf("Disconnected")
    val connected: MutableState<Boolean> = mutableStateOf(false)

    val ecgWave: MutableState<Float> = mutableStateOf(0f)

    val currentUser:MutableState<User?> = mutableStateOf(null)

    val distanceUnit:MutableState<Int> = mutableStateOf(SDKConstant.UNIT.KM)
    val temperatureUnit:MutableState<Int> = mutableStateOf(SDKConstant.UNIT.CELCIUS)
    val wearingPosition:MutableState<Int> = mutableStateOf(SDKConstant.WEARING_POSITION.LEFT)
    val themeWatch:MutableState<Int> = mutableStateOf(0)

    private val distanceKey = "DISTANCE"
    private val temperatureKey = "TEMPERATURE"
    private val wearingPositionKey = "WEARING_POS"
    private val themeWatchKey = "THEME_WATCH"
    init {
        currentUser.value = persistence.getUser()
        distanceUnit.value = persistence.getItemInt(distanceKey) ?: SDKConstant.UNIT.KM
        temperatureUnit.value = persistence.getItemInt(temperatureKey) ?: SDKConstant.UNIT.CELCIUS
        wearingPosition.value = persistence.getItemInt(wearingPositionKey) ?: SDKConstant.WEARING_POSITION.LEFT
        themeWatch.value = persistence.getItemInt(themeWatchKey) ?: 0
    }

    /**
     * start scan device smartwatch nearby
     * **/
    fun scanDevices(){

        try {
            YCBTClient.startScanBle({ i: Int, scanDeviceBean: ScanDeviceBean? ->


                //if device found add to viewModel
                scanDeviceBean?.let {

                    viewModelScope.launch {

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
     fun getHistoryByDate(from:Long,to:Long){
        val default = HistoryDatePickerModel(from, to)

        currentDate.value = default
        getBloodOxygenHistory()
        getRespirationHistory()
        getTemperatureHistory()
        getBloodPressureHistory()
        getHeartRateHistory()
        getSleepHistory()
        getStepHistory()

    }
    fun changeCurrentDate(from:Long,to:Long){
        val default = HistoryDatePickerModel(from, to)

        currentDate.value = default

    }
    //
    fun getBloodOxygenHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()) {

                listBloodOxygen.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )

            }
        }
    }

    //
    fun getRespirationHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()){
                listRespiration.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    //
    fun getTemperatureHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()) {
                listTemperature.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_TEMPERATURE,
                   it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    //
    fun  getBloodPressureHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()){
                listBloodPressure.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_PRESSURE,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    //
    fun getHeartRateHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()){
                listHeartRate.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_HEARTRATE,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    //
    fun getSleepHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()){
                listSleep.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_SLEEP,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    fun getStepHistory(){
        currentUser.value?.let {
            viewModelScope.launch(dispatcherProvider.io()){
                listStep.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_STEP,
                    it.user_code,
                    currentDate.value.from,
                    currentDate.value.to
                )
            }
        }
    }
    //sync from smartwatch to local
    fun syncSmartwatch(){
        val userId = persistence.getUser()!!.user_code
        val device = persistence.getItemString(SDKConstant.KEY_LAST_DEVICE)!!
        val mac = gson.fromJson(device,Devices::class.java).mac
        YCBTClient.healthHistoryData(
            SDKConstant.HISTORY.RESP_TEMP_SPO2
        ) { i, v, data ->

            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = mutableListOf<Measurement>()
            try {
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



                }
            }catch (e:Exception){

            }


            viewModelScope.launch(context = dispatcherProvider.io()) {

                measurementRepository.saveLocalMeasurement(result,false)
                getBloodOxygenHistory()
                getRespirationHistory()
                getTemperatureHistory()
            }

        }

        YCBTClient.healthHistoryData(
            SDKConstant.HISTORY.BPM
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            val result = list.map {
                it.extractBloodPressure(userId, mac)!!
            }

            viewModelScope.launch(context = dispatcherProvider.io()) {
                measurementRepository.saveLocalMeasurement(result,false)
                getBloodPressureHistory()
            }

        }

        YCBTClient.healthHistoryData(
            SDKConstant.HISTORY.HR
        ) { i, v, data ->//get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = list.map {
                it.extractHeartRate(userId, mac)!!
            }
            viewModelScope.launch(context = dispatcherProvider.io()) {
                measurementRepository.saveLocalMeasurement(result,false)

                getHeartRateHistory()
            }
        }

      //  step
        YCBTClient.healthHistoryData(
            SDKConstant.HISTORY.STEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>
            val result = list.map { 
                 it.extractStep(
                    userId,
                    mac
                )!!
            }
            viewModelScope.launch(context = dispatcherProvider.io() ) {
                measurementRepository.saveLocalMeasurement(result,false)
                getStepHistory()
            }
        }

        YCBTClient.healthHistoryData(
            SDKConstant.HISTORY.SLEEP
        ) { i, v, data ->
            //get data from smartwatch
            val list: ArrayList<HashMap<*, *>> = data.get("data") as ArrayList<HashMap<*, *>>

            try {


                val result = list.map {
                    Log.e("RESULT", it.extractSleepMonitor(userId, mac)!!.toString())
                    it.extractSleepMonitor(userId, mac)!!
                }
                viewModelScope.launch(context = dispatcherProvider.io()) {
                    measurementRepository.saveLocalMeasurement(result,false)
                    getSleepHistory()
                }
            }catch (e:Exception){
                Log.e("RESULT", e.message!!)
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

    //blood pressure calibration
    fun calibrateBloodPressure(sbp:Int,dbp:Int){
        YCBTClient.appBloodCalibration(sbp,dbp) { v, l, hashMap ->

        }
    }


    //change skin
    fun changeSkin(){
        YCBTClient.settingSkin(0x0){
                i, v, data->
        }
    }

    //change theme
    fun settingTheme(style:Int){
        persistence.setItem(themeWatchKey,style)
        YCBTClient.settingMainTheme(style){
            i,v,hashMap->{

        }
        }
    }


    /***
     * Setting of Left and Right hand
     * @param leftOrRight 0x00: Left hand 0x01: Right hand
     * @param dataResponse
     */
    fun settingWearingPosition(position:Int){
        persistence.setItem(wearingPositionKey,position)
        YCBTClient.settingHandWear(position){
            i,v,hashMap->
        }
    }

    /***
     * Target settings
     * @param goalType 0x00:steps 0x01:calories 0x02:distance 0x03:sleep
     * @param target target value(when the type is sleep，fill 0x00)
     * @param sleepHour sleep target:hour (when the type is non sleep，fill 0x00)
     * @param sleepMinute sleep target:minute (when the type is non sleep，fill 0x00)
     * @param dataResponse
     */
    fun settingGoalSleep(sleepHour: Int,sleepMinute: Int){
        settingGoal(SDKConstant.SETTING_GOAL.SLEEP,0x00,sleepHour,sleepMinute)
    }
    fun settingGoalStep(target: Int){
        settingGoal(SDKConstant.SETTING_GOAL.STEP,target,0x00,0x00)
    }
    fun settingCalorie(target: Int){
        settingGoal(SDKConstant.SETTING_GOAL.CALORIES,target,0x00,0x00)
    }
    fun settingGoalDistance(target: Int){
        settingGoal(SDKConstant.SETTING_GOAL.DISTANCE,target,0x00,0x00)
    }
    private fun settingGoal(goalType:Int,target:Int,sleepHour:Int,sleepMinute:Int,){
        YCBTClient.settingGoal(
            goalType,
            target,
            sleepHour,
            sleepMinute
        ){
            l,v,hashMap->
        }
    }
    //settingInterval
    fun settingInterval(interval:Int){
       // YCBTClient.setting
    }
    /***
     * Unit setting 0)
     * @param distanceUnit 0x00:km 0x01:mile
     * @param weightUnit  0x00:kg 0x01:lb 0x02:st
     * @param temperatureUnit  0x00: °C 0x01: °F
     * @param timeFormat  0x00:24hour0x01:12hour
     * @param dataResponse
     */
    fun settingUnit(timeFormat:Int=SDKConstant.UNIT.AM,weightUnit:Int=SDKConstant.UNIT.KG,distanceUnit:Int,temperatureUnit:Int,){
        persistence.setItem(temperatureKey,temperatureUnit)
        persistence.setItem(distanceKey,distanceUnit)
        YCBTClient.settingUnit(distanceUnit,weightUnit,temperatureUnit,timeFormat){
            l,v,hashMap->{

        }
        }
    }
}