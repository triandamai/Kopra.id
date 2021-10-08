package com.trian.data.viewmodel

import android.net.Network
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.repository.DoctorRepository
import com.trian.data.repository.MeasurementRepository
import com.trian.data.repository.UserRepository
import com.trian.data.utils.explodeBloodPressure
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.User
import com.trian.domain.models.Doctor
import com.trian.domain.models.Speciality
import com.trian.domain.models.bean.HistoryDatePickerModel
import com.trian.domain.models.request.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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
    private val doctorRepository: DoctorRepository,
    private val persistence: Persistence,
    private val dispatcherProvider: DispatcherProvider
) :ViewModel(){
    /**
     * data that show in chart detail healt status
     * **/
    val listSystole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listDiastole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listBloodOxygen: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listRespiration: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listHeartRate: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listTemperature: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listSleep: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val user:MutableState<User?> = mutableStateOf(null)
    val doctor:MutableState<List<Doctor>?> = mutableStateOf(null)
    val specialist:MutableState<List<Speciality>?> = mutableStateOf(null)
    val detailDoctor:MutableState<Doctor?> = mutableStateOf(null)

    /**
     * when button login hit will notify every change this state
     * **/
    private val loginResponse = MutableLiveData<NetworkStatus<WebBaseResponse<ResponseUser>>>()
    val loginStatus get()=loginResponse

    /**
     * when button register hit will notify every change this state
     ***/
    private val registerResponse = MutableLiveData<NetworkStatus<WebBaseResponse<Any>>>()
    val registerStatus get()=registerResponse

    /**
     * when button forgot hit will notify every change this state
     ***/
    private val forgotPasswordResponse = MutableLiveData<NetworkStatus<WebBaseResponse<Any>>>()
    val forgotPassword get() = forgotPasswordResponse

    /**
     * data doctor
     */
    private val doctorResponse = MutableLiveData<NetworkStatus<WebBaseResponse<List<Doctor>>>>()
    val doctorStatus get() = doctorResponse

    /**
     * data specialist doctor
     */
    private val specialistResponse = MutableLiveData<NetworkStatus<WebBaseResponse<List<Speciality>>>>()
    val specialistStatus get() = specialistResponse

    private val detailDoctorResponse = MutableLiveData<NetworkStatus<WebBaseResponse<Doctor>>>()
    val detailDoctorStatus get() = detailDoctorResponse

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
       user.value= persistence.getUser()
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
        persistence.getUser()?.let {
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
    ) {
        registerResponse.value = NetworkStatus.Loading(null)
        if(name.isBlank() ||
            username.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            address.isBlank() ){
            registerResponse.value = NetworkStatus.Error("Some Field is Required")
        }else{
            viewModelScope.launch {
                val result = userRepository.registerUser(
                    RequestRegister(
                        name, address, username, email, password
                    )
                )

                registerResponse.value = when (result) {
                    is NetworkStatus.Success -> {
                        result.data?.let {
                                it-> when(it.success) {
                            true->
                            {
                                success()
                                result
                            }
                            else ->
                                NetworkStatus.Error("")
                        }

                        }?:run {
                            NetworkStatus.Error("Failed Register")
                        }
                    }
                    else -> {
                        NetworkStatus.Error(result.errorMessage)
                    }
                }
            }
        }

    }

    //login
     fun login(username:String,password:String,success:suspend ()->Unit)=viewModelScope.launch {
        loginResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch{
          val result =  userRepository.loginUser(username,password)

            loginResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let { it1 ->
                        Log.e("RESULT",it1.toString())
                        if(it1.success) {
                            it1.user?.let {it2->
                                persistence.setUser(it2.toUser()!!)
                                persistence.setToken(it1.access_token!!)
                                success()
                                user.value = persistence.getUser()
                                NetworkStatus.Success(result.data)
                            }?:run{
                                NetworkStatus.Error("Failed Authenticated")
                            }
                        }else{
                            NetworkStatus.Error("Failed to fetch user")
                        }
                    } ?: run {
                        NetworkStatus.Error("Failed to fetch user")
                    }

                }
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
    }

    //login google
    fun loginGoogle(name: String,email:String,success:suspend ()->Unit){
        loginResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch{
            val result = userRepository.loginGoogle(name,email)
            loginResponse.value = when(result){
                is NetworkStatus.Success->{
                                result.data?.let { it1 ->
                                    if(it1.success) {
                                         it1.user?.let {it2->
                                            persistence.setUser(it2.toUser()!!)
                                            persistence.setToken(it1.access_token!!)
                                            success()
                                             user.value = persistence.getUser()
                                            NetworkStatus.Success(result.data)
                                        }?:run{
                                            NetworkStatus.Error("Failed Authenticated")
                                        }
                                    }else{
                                        NetworkStatus.Error("Failed to fetch user")
                                    }
                                } ?: run {
                                    NetworkStatus.Error("Failed to fetch user")
                                }

                        }
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
    }

    fun forgotPassword(email: String,success: suspend () -> Unit){
        forgotPasswordResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = userRepository.forgotPassword(email = email)
            forgotPasswordResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        Log.e("Result",it.data.toString())
                        NetworkStatus.Error("Error")
                    }?:run{
                        NetworkStatus.Error("Error")
                    }
                }
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
    }


    fun signOut(callback:()->Unit){

        viewModelScope.launch(dispatcherProvider.io()) {
            persistence.signOut()
            measurementRepository.deleteAll()
            delay(400)
            callback()
        }
    }


    //get data all doctor
    fun doctor(success:suspend ()->Unit){
        doctorResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = doctorRepository.doctorList()
            doctorResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        if(it.success){
                            success()
                            doctor.value = it.data
                            Log.e("Result",it.data.toString())
                            NetworkStatus.Success(result.data)
                        }else{
                            NetworkStatus.Error("Failed to fetch doctor")
                        }
                    }?: run {
                        NetworkStatus.Error("Failed Authenticated")
                    }
                }
                is NetworkStatus.Loading -> TODO()
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
    }

    //spesialist
    fun specialist(slug:String,success:suspend ()->Unit){
        specialistResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = doctorRepository.specialist(slug)
            specialistResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        if(it.success){
                            success()
                            specialist.value = it.data
                            Log.e("Result",it.data.toString())
                            NetworkStatus.Success(result.data)
                        }else{
                            NetworkStatus.Error("Failed to fetch specialist")
                        }
                    }?: run {
                        NetworkStatus.Error("Failed")
                    }
                }
                is NetworkStatus.Loading -> TODO()
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
    }

    //detail doctor
    fun detailDoctor(slug:String,success:suspend ()->Unit){
        detailDoctorResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = doctorRepository.detailDoctor(slug)
            detailDoctorResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        success()
                        detailDoctor.value = it.data
                        NetworkStatus.Success(result.data)
                    }?: run {
                        NetworkStatus.Error("Failed")
                    }
                }
                else -> {
                    NetworkStatus.Error(result.errorMessage)
                }
            }
        }
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
                 listBloodOxygen.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_OXYGEN,
                    it.user_code,
                    dateBloodOxygen.value.from,
                    dateBloodOxygen.value.to
                ).mapIndexed() {
                        index,measurement->

                    Entry(index.toFloat(),measurement.value.toFloat())
                }
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
                measurementRepository.getHistory(
                    SDKConstant.TYPE_BLOOD_PRESSURE,
                    it.user_code,
                    dateBloodPressure.value.from,
                    dateBloodPressure.value.to
                )
                    .forEachIndexed { index, measurement ->
                        val bpm = measurement.value.explodeBloodPressure()
                        systole.add(Entry(index.toFloat(), bpm.systole.toFloat()))
                        diastole.add(Entry(index.toFloat(), bpm.diastole.toFloat()))
                    }
                listSystole.value = systole
                listDiastole.value = diastole


            }
        }

    }
    /**
     * get history heart rate
     * **/
     fun getHeartRateHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                listHeartRate.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_HEARTRATE,
                    it.user_code,
                    dateHeartRate.value.from,
                    dateHeartRate.value.to
                )
                    .mapIndexed { index, measurement ->
                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
            }
        }
    }

    /**
     * get history temperature per day
     * **/
     fun getTemperatureHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                listTemperature.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_TEMPERATURE,
                    it.user_code,
                    dateTemperature.value.from,
                    dateTemperature.value.to
                )
                    .mapIndexed { index, measurement ->

                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
            }
        }

    }

    /**
     * get history respiration
     * **/
     fun getRespirationHistory(){
        viewModelScope.launch(dispatcherProvider.io()) {
            user.value?.let {
                listRespiration.value = measurementRepository.getHistory(
                    SDKConstant.TYPE_RESPIRATION,
                    it.user_code,
                    dateRespiration.value.from,
                    dateRespiration.value.to
                )
                    .mapIndexed { index, measurement ->

                        Entry(index.toFloat(), measurement.value.toFloat())
                    }
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