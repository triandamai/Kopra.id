package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.repository.ArticleRepository
import com.trian.data.repository.DoctorRepository
import com.trian.data.repository.HospitalRepository
import com.trian.data.repository.UserRepository
import com.trian.domain.entities.User
import com.trian.domain.models.*
import com.trian.domain.models.request.WebBaseResponse
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
    private val specialistResponse = MutableLiveData<DataStatus<List<Speciality>>>()
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

    private val listOrderResponse = MutableLiveData<DataStatus<List<Order>>>()
    val listOrderStatus get() = listOrderResponse



    //get data all doctor
    fun doctor(success:suspend ()->Unit) =viewModelScope.launch {
        doctorResponse.value = DataStatus.Loading("")
        doctorResponse.value = doctorRepository.doctorList()
    }


    //spesialist
    fun specialist(slug:String,success:suspend ()->Unit)=viewModelScope.launch {
        specialistResponse.value = DataStatus.Loading("")
        specialistResponse.value = doctorRepository.specialist(slug)
    }


    //detail doctor
    fun detailDoctor(slug:String,success:suspend ()->Unit) =viewModelScope.launch {
        detailDoctorResponse.value = DataStatus.Loading("")
        detailDoctorResponse.value = doctorRepository.detailDoctor(slug)

    }


    fun article(success: suspend () -> Unit)=viewModelScope.launch {
        articleResponse.value = DataStatus.Loading("")
        articleResponse.value =articleRepository.article()

    }

    fun hospital(success: suspend () -> Unit)=viewModelScope.launch {
            hospitalResponse.value = DataStatus.Loading("")
            delay(400)
            hospitalResponse.value = when(val result = hospitalRepository.hospital()){
                is DataStatus.HasData->{
                    success()
                    Log.e("Resut",result.toString())
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
}