package com.trian.microlife.viewmodel

import android.util.Log
import androidx.lifecycle.*

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.repository.CexupRepository
import com.trian.data.repository.ICexupRepository
import com.trian.domain.entities.User
import com.trian.domain.models.Devices

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MicrolifeViewModel @Inject constructor(
   private val cexupRepository: ICexupRepository,
    ):ViewModel() {
    private val listDevices = MutableLiveData<List<Devices>>()
    val devices:LiveData<List<Devices>> get() = listDevices

    fun addDevice(devices: Devices){
        val list = mutableListOf<Devices>()
        listDevices.value?.forEach {
            list.add(it)
        }

            list.add(devices)


        listDevices.postValue(list)
    }
}