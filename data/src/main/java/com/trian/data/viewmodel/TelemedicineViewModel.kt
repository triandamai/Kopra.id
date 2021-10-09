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
     * data article
     */
    private val hospitalResponse = MutableLiveData<DataStatus<List<Hospital>>>()
    val hospitalStatus get() = hospitalResponse



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
            hospitalResponse.value = hospitalRepository.hospital()
    }
}