package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.common.utils.network.DataStatus
import com.trian.data.repository.ArticleRepository
import com.trian.data.repository.DoctorRepository
import com.trian.data.repository.HospitalRepository
import com.trian.data.repository.UserRepository
import com.trian.domain.entities.User
import com.trian.domain.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelemedicineViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
    private val articleRepository: ArticleRepository,
    private val hospitalRepository: HospitalRepository,
    private val userRepository: UserRepository
):ViewModel() {

    val user:MutableState<User?> = mutableStateOf(null)
    init {
        viewModelScope.launch {
            user.value= userRepository.getCurrentUser()
        }
    }

    /**
     * data doctor
     */
    private val doctorResponse = MutableLiveData<DataStatus<List<Doctor>>>()
    val doctorStatus get() = doctorResponse

    /**
     * data specialist doctor
     */
    private val specialistResponse = MutableLiveData<DataStatus<List<Doctor>>>()
    val specialistStatus get() = specialistResponse

    /**
     * data detail doctor
     */
    private val detailDoctorResponse = MutableLiveData<DataStatus<Doctor>>()
    val detailDoctorStatus get() = detailDoctorResponse

    /**
     * data article
     */
    private val articleResponse = MutableLiveData<DataStatus<List<Article>>>()
    val articleStatus get() = articleResponse

    /**
     * data hospital
     */
    private val hospitalResponse = MutableLiveData<DataStatus<List<Hospital>>>()
    val hospitalStatus get() = hospitalResponse

    private val detailHospitalResponse = MutableLiveData<DataStatus<Hospital>>()
    val detailHospitalStatus = detailHospitalResponse

    private val listOrderResponse = MutableLiveData<DataStatus<List<Order>>>()
    val listOrderStatus get() = listOrderResponse

    private val listSpecialistResponse = MutableLiveData<DataStatus<List<Specialist>>>()
    val listSpecialistStatus get() = listSpecialistResponse

    private val detailOrderResponse = MutableLiveData<DataStatus<Order>>()
    val detailOrderStatus get() = detailOrderResponse

    private val timeListDoctorResponse = MutableLiveData<DataStatus<List<TimeListDoctor>>>()
    val timeListDoctorStatus get() = timeListDoctorResponse



    //get data all doctor
    fun getListDoctor(success:suspend ()->Unit) =viewModelScope.launch {
        doctorResponse.value = DataStatus.Loading("")
        doctorResponse.value = when(val result =doctorRepository.doctorList()){
            is DataStatus.HasData ->{
                success()
                Log.e("Result", result.data.toString())
                result
            }else -> result
        }
    }


    //spesialist
    fun specialist(slug:String,success:suspend ()->Unit)=viewModelScope.launch {
        specialistResponse.value = DataStatus.Loading("")
        specialistResponse.value = when( val result = doctorRepository.specialist(slug)){
            is DataStatus.HasData ->{
                success()
                Log.e("Result Specialist", result.data.toString())
                result
            }else -> result
        }
    }


    //detail doctor
    fun detailDoctor(slug:String,success:suspend ()->Unit) =viewModelScope.launch {
        detailDoctorResponse.value = DataStatus.Loading("")
        delay(400)
        detailDoctorResponse.value = doctorRepository.detailDoctor(slug)

    }


    fun getListArticle(success: suspend () -> Unit)=viewModelScope.launch {
        articleResponse.value = DataStatus.Loading("")
        articleResponse.value =articleRepository.getListArticle()
    }

    fun hospital(success: suspend () -> Unit)=viewModelScope.launch {
            hospitalResponse.value = DataStatus.Loading("")
            delay(400)
            hospitalResponse.value = when(val result = hospitalRepository.hospital()){
                is DataStatus.HasData->{
                    success()
                    Log.e("Resut",result.data.toString())
                    result
                }
                else -> result
            }
    }

    fun listOrder(success: suspend () -> Unit) = viewModelScope.launch {
        listOrderResponse.value = DataStatus.Loading("")
        delay(400)
        user.value?.let {
            val result = doctorRepository.listOrder(userId = it.user_id)
            listOrderResponse.value = when(result){
                is DataStatus.HasData->{
                    success()
                    Log.e("Result",result.toString())
                    result
                }
                else -> {
                    Log.e("Result",result.toString())
                    result
                }
            }
        }
    }
    fun getDetailHospital(slug: String, success: suspend () -> Unit) = viewModelScope.launch {
        detailHospitalResponse.value = DataStatus.Loading("")
        delay(400)
        detailHospitalResponse.value = when(val result = hospitalRepository.detailHospital(slug = slug)){
            is DataStatus.HasData ->{
                success()
                Log.e("Result",result.data.toString())
                result
            }else  -> result
        }
    }

    fun listSpeciality() = viewModelScope.launch{
        listSpecialistResponse.value = DataStatus.Loading("")
        delay(400)
        listSpecialistResponse.value = when(val result = doctorRepository.listSpeciality()){
            is DataStatus.HasData->{
                Log.e("Result list : ",result.data.toString())
                result
            }
            else->result

        }
    }

    fun detailOrder(transaction_id:String) = viewModelScope.launch {
        detailOrderResponse.value = DataStatus.Loading("")
        delay(400)
        detailOrderResponse.value = when(val result = doctorRepository.detailOrder(
            transaction_id
        )){
            is DataStatus.HasData->{
                Log.e("Result Detail Doctor", result.data.toString())
                result
            }
            else->result
        }
    }

    fun timeListDoctor(
        doctor_has_hospital_id: String,
        date: String,
        appoinment: String
    )=viewModelScope.launch {
        timeListDoctorResponse.value = DataStatus.Loading("")
        delay(400)
        timeListDoctorResponse.value = when(val result = doctorRepository.timeListDoctor(
            doctor_has_hospital_id, date, appoinment
        )){
            is DataStatus.HasData->{
                Log.e("Result time list",result.data.toString())
                result
            }
            else->result
        }
    }
}