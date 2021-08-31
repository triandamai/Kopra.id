package com.trian.microlife

import android.util.Log
import androidx.lifecycle.*

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.repository.CexupRepository
import com.trian.data.repository.ICexupRepository
import com.trian.domain.entities.User

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
   private val cexupRepository: ICexupRepository
    ):ViewModel() {
    private val nama = MutableLiveData<String>("ghghg")
    private val status = MutableLiveData<NetworkStatus<List<User>>>()

    val nameLiveData:LiveData<String> get() = nama
    val statusLiveData:LiveData<NetworkStatus<List<User>>> get() = status
    init {
        Log.e("VM","${cexupRepository}")
    }
    fun tes(name:String){
        nama.value = "$name -> ${System.currentTimeMillis()}"
        Log.e("VM","${cexupRepository}")
    }

    fun users(){
        status.postValue(NetworkStatus.Loading())

        viewModelScope.launch {
         //  when(val result =  cexupRepository.fetchAllUsers()){

//                is NetworkStatus.Error ->{
//                    status.postValue(NetworkStatus.Error(result.errorMessage,data = listOf<User>()))
//                }
//                is NetworkStatus.Success->{
//                    status.postValue(NetworkStatus.Success(result.data))}
//                else -> {
//                    status.postValue(
//                        NetworkStatus.Error(
//                            result.errorMessage,
//                            data = listOf<User>()
//                        )
//                    )
//                }}
        }
    }
}