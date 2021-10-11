package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.formatHoursMinute
import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.repository.MeasurementRepository
import com.trian.data.repository.UserRepository
import com.trian.data.utils.explodeBloodPressure
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.User
import com.trian.domain.models.bean.HistoryDatePickerModel
import com.trian.domain.models.request.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main ViewModel Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository,
    private val userRepository: UserRepository,
    private val dispatcherProvider: DispatcherProvider
) :ViewModel()
{
    /**
     * data that show in chart detail healt status
     * **/
    val listSystole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameBloodPressure: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val listDiastole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listBloodOxygen: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameBloodOxygen: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val listRespiration: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameRespiration: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val listHeartRate: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameHeartRate: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val listTemperature: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameTemperature: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val listSleep: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listNameSleep: MutableState<List<String>> = mutableStateOf(arrayListOf())
    val user:MutableState<User?> = mutableStateOf(null)


    /**
     * when button login hit will notify every change this state
     * **/
    private val loginResponse = MutableLiveData<DataStatus<ResponseUser>>()
    val loginStatus get()=loginResponse

    /**
     * when button register hit will notify every change this state
     ***/
    private val registerResponse = MutableLiveData<DataStatus<Any>>()
    val registerStatus get()=registerResponse

    /**
     * when button forgot hit will notify every change this state
     ***/
    private val forgotPasswordResponse = MutableLiveData<DataStatus<Any>>()
    val forgotPassword get() = forgotPasswordResponse

    /**
     * state for date each health status
     ***/
    var dateBloodOxygen:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateBloodPressure:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateHeartRate:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateTemperature:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateRespiration:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateSleep:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))
    var dateCalorie:MutableState<HistoryDatePickerModel> = mutableStateOf(HistoryDatePickerModel(from = getLastDayTimeStamp(), to = getTodayTimeStamp()))

    /**
     * state for sync data health
     * **/
    
    var onSync:MutableState<Boolean> = mutableStateOf(false)
    
    init {
        viewModelScope.launch {
            user.value= userRepository.getCurrentUser()
        }

    }
    //
    val latestBloodPressure: MutableState<Measurement> = mutableStateOf(
        Measurement(
            member_id = "",
            nurse_id = "",
            device_id = "",
            value = "0/0",
            type = SDKConstant.TYPE_BLOOD_PRESSURE,
            created_at = 0,
            end_at=0,
            updated_at = 0
    ))
    val latestBloodOxygen: MutableState<Measurement> = mutableStateOf( Measurement(
        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_BLOOD_OXYGEN,
        created_at = 0,
        end_at=0,
        updated_at = 0
    ))
    val latestRespiration: MutableState<Measurement> = mutableStateOf( Measurement(
        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_RESPIRATION,
        created_at = 0,
        end_at=0,
        updated_at = 0
    ))
    val latestHeartRate: MutableState<Measurement> = mutableStateOf( Measurement(

        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_HEARTRATE,
        created_at = 0,
        end_at=0,
        updated_at = 0
    ))
    val latestTemperature: MutableState<Measurement> = mutableStateOf( Measurement(

        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_TEMPERATURE,
        created_at = 0,
        end_at=0,
        updated_at = 0
    ))



    //check login
    suspend fun checkAlreadyLoggedIn(isLoggedIn:suspend (value:Boolean)->Unit){
        userRepository.getCurrentUser()?.let {
            isLoggedIn(true)
        }?: run {
            isLoggedIn(false)
        }
    }
    //register

    fun register(
        name:String,
        username: String,
        email: String,
        password: String,
        address:String,
        success:suspend ()->Unit
    ) = viewModelScope.launch {
        registerResponse.value = DataStatus.Loading("")
        if(name.isBlank() ||
            username.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            address.isBlank() ){
            registerResponse.value = DataStatus.NoData(500,"Some Field is Required")
        }else{
            registerResponse.value = when(val result = userRepository.registerUser(RequestRegister(name, address, username, email, password))){
                is DataStatus.HasData -> {
                    success()
                    result
                }
                else -> result
            }
        }
    }


    //login
     fun login(username:String,password:String,success:suspend ()->Unit)= viewModelScope.launch {
        loginResponse.value = DataStatus.Loading("")
        loginResponse.value =   when(val result = userRepository.loginUser(username,password)){
            is DataStatus.HasData -> {
                success()
                result
            }
            else -> result
        }

    }

    //login google
    fun loginGoogle(name: String,email:String,success:suspend ()->Unit)=viewModelScope.launch{
            loginResponse.value = DataStatus.Loading("")
            loginResponse.value = when(val result = userRepository.loginGoogle(name,email)){
                is DataStatus.HasData -> {
                    Log.e("RESULT",result.toString())
                    success()
                    result
                }

                else -> result
            }
    }


    fun forgotPassword(email: String,success: suspend () -> Unit)= viewModelScope.launch {
        forgotPasswordResponse.value = DataStatus.Loading("")
        forgotPasswordResponse.value = userRepository.forgotPassword(email = email)
    }




    fun signOut(callback:()->Unit)=viewModelScope.launch(dispatcherProvider.io()) {
            userRepository.signOut()
            measurementRepository.deleteAll()
            delay(400)
            callback()
    }


    /**
     * 
     * **/
        fun startSyncMeasurementFromApi(){
            onSync.value = true
            viewModelScope.launch(dispatcherProvider.io()){
                user.value?.let {
                        it->
                        val result = measurementRepository.fetchApiMeasurement(it.user_code)
                        result.data?.let { it ->
                            val result = it.data.map {
                                it.toMeasurement()
                            }
                            measurementRepository.saveLocalMeasurement(result,true)
                            getDetailHealthStatus(getLastDayTimeStamp(),getTodayTimeStamp())
                            delay(1000)
                            onSync.value= false
                    }?:run{
                            onSync.value= false
                    }
                }?:run{
                    onSync.value = false
                }
            }
        }    
    
    /**
     * get latest data health and show to card health status
     * **/
    suspend fun getHealthStatus(){
        user.value?.let {
            measurementRepository.getLatestMeasurement(SDKConstant.TYPE_BLOOD_PRESSURE,it.user_code)
                .firstOrNull()?.let {
                    latestBloodPressure.value = it
                }
            measurementRepository.getLatestMeasurement(SDKConstant.TYPE_BLOOD_OXYGEN,it.user_code)
                .firstOrNull()?.let {
                    latestBloodOxygen.value = it
                }
            measurementRepository.getLatestMeasurement(SDKConstant.TYPE_RESPIRATION,it.user_code)
                .firstOrNull()?.let {
                    latestRespiration.value = it
                }
            measurementRepository.getLatestMeasurement(SDKConstant.TYPE_HEARTRATE,it.user_code)
                .firstOrNull()?.let {
                    latestHeartRate.value = it
                }
            measurementRepository.getLatestMeasurement(SDKConstant.TYPE_TEMPERATURE,it.user_code)
                .firstOrNull()?.let {
                    latestTemperature.value = it
                }
        }

    }
    //sync all data
     fun getDetailHealthStatus(from:Long, to:Long){
        val default = HistoryDatePickerModel(from, to)
        dateBloodOxygen.value = default
        dateBloodPressure.value = default
        dateCalorie.value = default
        dateHeartRate.value = default
        dateRespiration.value = default
        dateSleep.value = default
        dateTemperature.value = default
        getBloodOxygenHistory()
        getBloodPressureHistory()
        getHeartRateHistory()
        getRespirationHistory()
        getTemperatureHistory()
        getSleepHistory()
        getCalorieHistory()

    }

    /**
     * get history blood oxygen
     * **/
     fun getBloodOxygenHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                val name = mutableListOf<String>()
                 listBloodOxygen.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    it.user_code,
                    dateBloodOxygen.value.from,
                    dateBloodOxygen.value.to
                ).mapIndexed() {
                        index,measurement->
                    name.add(measurement.created_at.formatHoursMinute())
                    Entry(index.toFloat(),measurement.value.toFloat())
                }
                listNameBloodOxygen.value = name
            }
        }


    }
    /**
     * get history blood pressure
     * **/
     fun getBloodPressureHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                val systole = mutableListOf<Entry>()
                val diastole = mutableListOf<Entry>()
                val name = mutableListOf<String>()
                measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_PRESSURE,
                    it.user_code,
                    dateBloodPressure.value.from,
                    dateBloodPressure.value.to
                )
                    .forEachIndexed { index, measurement ->
                        val bpm = measurement.value.explodeBloodPressure()
                        name.add(measurement.created_at.formatHoursMinute())
                        systole.add(Entry(index.toFloat(), bpm.systole.toFloat()))
                        diastole.add(Entry(index.toFloat(), bpm.diastole.toFloat()))
                    }
                listSystole.value = systole
                listDiastole.value = diastole
                listNameBloodPressure.value = name


            }
        }

    }
    /**
     * get history heart rate
     * **/
     fun getHeartRateHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                val name = mutableListOf<String>()
                listHeartRate.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_HEARTRATE,
                    it.user_code,
                    dateHeartRate.value.from,
                    dateHeartRate.value.to
                )
                    .mapIndexed { index, measurement ->
                        name.add(measurement.created_at.formatHoursMinute())
                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
                listNameHeartRate.value = name
            }
        }
    }

    /**
     * get history temperature per day
     * **/
     fun getTemperatureHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                val name = mutableListOf<String>()
                listTemperature.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_TEMPERATURE,
                    it.user_code,
                    dateTemperature.value.from,
                    dateTemperature.value.to
                )
                    .mapIndexed { index, measurement ->
                        name.add(measurement.created_at.formatHoursMinute())
                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
                listNameTemperature.value = name
            }
        }

    }

    /**
     * get history respiration
     * **/
     fun getRespirationHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                val name = mutableListOf<String>()
                listRespiration.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    it.user_code,
                    dateRespiration.value.from,
                    dateRespiration.value.to
                )
                    .mapIndexed { index, measurement ->
                        name.add(measurement.created_at.formatHoursMinute())
                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
                listNameRespiration.value = name
            }
        }

    }

    /**
     * get history sleep
     * **/
     fun getSleepHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                measurementRepository.getHistory(
                    SDKConstant.TYPE_SLEEP,
                    it.user_code,
                    dateSleep.value.from,
                    dateSleep.value.to
                )
                    .forEachIndexed { index, measurement ->

                        // Entry(index.toFloat(),measurement.value.toFloat())
                    }
            }
        }

    }

    /**
     * get history calorie
     * **/
     fun getCalorieHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {

            }
        }
    }


}