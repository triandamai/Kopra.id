package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.sdk.SDKConstant
import com.trian.data.local.Persistence
import com.trian.data.local.room.MeasurementDao
import com.trian.data.repository.IMeasurementRepository
import com.trian.data.repository.IUserRepository
import com.trian.data.utils.explodeBloodPressure
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.User
import com.trian.domain.repository.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val measurementRepository: IMeasurementRepository,
    private val userRepository: IUserRepository,
    private val persistence: Persistence,
    private val measurementDao: MeasurementDao
) :ViewModel(){
    //detail chart
    val listSystole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listDiastole: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listBloodOxygen: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listRespiration: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listHeartRate: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listTemperature: MutableState<List<Entry>> = mutableStateOf(arrayListOf())
    val listSleep: MutableState<List<Entry>> = mutableStateOf(arrayListOf())

   private val loginResponse = MutableLiveData<NetworkStatus<BaseResponse<User>>>()
    val loginStatus get()=loginResponse
    //
    val latestBloodPressure: MutableState<Measurement> = mutableStateOf(
        Measurement(
            member_id = "",
            nurse_id = "",
            device_id = "",
            value = "0/0",
            type = SDKConstant.TYPE_BLOOD_PRESSURE,
            created_at = 0,
            updated_at = 0
    ))
    val latestBloodOxygen: MutableState<Measurement> = mutableStateOf( Measurement(
        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_BLOOD_OXYGEN,
        created_at = 0,
        updated_at = 0
    ))
    val latestRespiration: MutableState<Measurement> = mutableStateOf( Measurement(
        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_RESPIRATION,
        created_at = 0,
        updated_at = 0
    ))
    val latestHeartRate: MutableState<Measurement> = mutableStateOf( Measurement(

        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_HEARTRATE,
        created_at = 0,
        updated_at = 0
    ))
    val latestTemperature: MutableState<Measurement> = mutableStateOf( Measurement(

        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_TEMPERATURE,
        created_at = 0,
        updated_at = 0
    ))
    val latestSleep: MutableState<Measurement> = mutableStateOf( Measurement(

        member_id = "",
        nurse_id = "",
        device_id = "",
        value = "0",
        type = SDKConstant.TYPE_SLEEP,
        created_at = 0,
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
    //login
    suspend fun login(username:String,password:String,success:suspend ()->Unit)=viewModelScope.launch {
        loginResponse.value = NetworkStatus.Loading(null)
        userRepository.loginUser(username,password)
            .collect {
                loginResponse.value = it
                persistence.setUser(User(
                    id_user=0,
                    user_id="ini_id",
                    type="unknown",
                    no_type="unknown",
                    name= "Trian",
                    username="triandamai",
                    gender="laki-laki",
                    email="triannurizkillah@gmail.com",
                    phone_number="98767890",
                    address= "ajbsa",
                    thumb="sasa"
                ))
                when(it){
                    is NetworkStatus.Success->{

                        success()
                    }
                    else -> {
                        //for testing only
                        success()
                    }
                }
            }

    }

    suspend fun getHealthStatus(){
        val memberId = persistence.getUser()!!.user_id
        measurementRepository.getLatestMeasurement(SDKConstant.TYPE_BLOOD_PRESSURE,memberId)
            .firstOrNull()?.let {
                latestBloodPressure.value = it
            }
        measurementRepository.getLatestMeasurement(SDKConstant.TYPE_BLOOD_OXYGEN,memberId)
            .firstOrNull()?.let {
                latestBloodOxygen.value = it
            }
        measurementRepository.getLatestMeasurement(SDKConstant.TYPE_RESPIRATION,memberId)
            .firstOrNull()?.let {
                latestRespiration.value = it
            }
        measurementRepository.getLatestMeasurement(SDKConstant.TYPE_HEARTRATE,memberId)
            .firstOrNull()?.let {
                latestHeartRate.value = it
            }
        measurementRepository.getLatestMeasurement(SDKConstant.TYPE_TEMPERATURE,memberId)
            .firstOrNull()?.let {
                latestTemperature.value = it
            }
    }
    //sync all data
    suspend fun getDetailHealthStatus(from:Long, to:Long){
        val memberId = persistence.getUser()!!.user_id
        val getAll = measurementDao.getAll()
        getAll.forEach {
            Log.e("FUCK",it.toString())
        }
        listBloodOxygen.value = measurementRepository.getHistory(
            SDKConstant.TYPE_BLOOD_OXYGEN,
            memberId,
            from,
            to
        ).mapIndexed() {
            index,measurement->

             Entry(index.toFloat(),measurement.value.toFloat())
        }
        listRespiration.value = measurementRepository.getHistory(
            SDKConstant.TYPE_RESPIRATION,
            memberId,
            from,
            to,
        )
            .mapIndexed {
                    index,measurement->

                Entry(index.toFloat(),measurement.value.toFloat())
            }
        listTemperature.value = measurementRepository.getHistory(
            SDKConstant.TYPE_TEMPERATURE,
            memberId,
            from,
            to
        )
            .mapIndexed {
                    index,measurement->
                Log.e("MVM 243",measurement.toString())

                Entry(index.toFloat(),measurement.value.toFloat())
            }
        val systole = mutableListOf<Entry>()
        val diastole = mutableListOf<Entry>()
         measurementRepository.getHistory(
        SDKConstant.TYPE_BLOOD_PRESSURE,
        memberId,
        from,
        to
        )
        .forEachIndexed {
                index,measurement->
            val bpm = measurement.value.explodeBloodPressure()
            systole.add(Entry(index.toFloat(),bpm.systole.toFloat()))
            diastole.add(Entry(index.toFloat(),bpm.diastole.toFloat()))
        }
        listSystole.value = systole
        listDiastole.value = diastole

        listHeartRate.value = measurementRepository.getHistory(
            SDKConstant.TYPE_HEARTRATE,
            memberId,
            from,
            to
        )
            .mapIndexed {
                    index,mesaurement->
                Entry(index.toFloat(),mesaurement.value.toFloat())
            }
       // listSleep.value =
            measurementRepository.getHistory(
            SDKConstant.TYPE_SLEEP,
            memberId,
            from,
            to
        )
            .forEachIndexed {
                    index,measurement->

              // Entry(index.toFloat(),measurement.value.toFloat())
            }
    }

}