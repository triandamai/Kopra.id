package com.trian.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.repository.ArticleRepository
import com.trian.data.repository.DoctorRepository
import com.trian.data.repository.HospitalRepository
import com.trian.domain.models.Article
import com.trian.domain.models.Doctor
import com.trian.domain.models.Hospital
import com.trian.domain.models.Speciality
import com.trian.domain.models.request.WebBaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelemedicineViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
    private val articleRepository: ArticleRepository,
    private val hospitalRepository: HospitalRepository
):ViewModel() {
    val doctor: MutableState<List<Doctor>?> = mutableStateOf(null)
    val specialist: MutableState<List<Speciality>?> = mutableStateOf(null)
    val detailDoctor: MutableState<Doctor?> = mutableStateOf(null)
    val article: MutableState<List<Article>?> = mutableStateOf(null)
    val hospital:MutableState<List<Hospital>?> = mutableStateOf(null)

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

    /**
     * data detail doctor
     */
    private val detailDoctorResponse = MutableLiveData<NetworkStatus<WebBaseResponse<Doctor>>>()
    val detailDoctorStatus get() = detailDoctorResponse

    /**
     * data article
     */
    private val articleResponse = MutableLiveData<NetworkStatus<WebBaseResponse<List<Article>>>>()
    val articleStatus get() = articleResponse

    /**
     * data article
     */
    private val hospitalResponse = MutableLiveData<NetworkStatus<WebBaseResponse<List<Hospital>>>>()
    val hospitalStatus get() = hospitalResponse



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

    fun article(success: suspend () -> Unit){
        articleResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = articleRepository.article()
            articleResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        success()
                        article.value = it.data
                        NetworkStatus.Success(result.data)
                    }?: run {
                        NetworkStatus.Error("Error")
                    }
                }
                else -> NetworkStatus.Error("Error")
            }

        }
    }

    fun hospital(success: suspend () -> Unit){
        hospitalResponse.value = NetworkStatus.Loading(null)
        viewModelScope.launch {
            val result = hospitalRepository.hospital()
            hospitalResponse.value = when(result){
                is NetworkStatus.Success->{
                    result.data?.let {
                        success()
                        hospital.value = it.data
                        NetworkStatus.Success(result.data)
                    }?:run{
                        NetworkStatus.Error("error")
                    }
                }
                is NetworkStatus.Error -> TODO()
                is NetworkStatus.Loading -> TODO()
            }
        }
    }
}