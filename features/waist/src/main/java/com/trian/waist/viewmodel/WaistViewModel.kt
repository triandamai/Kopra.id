package com.trian.waist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trian.data.repository.MeasurementRepository
import com.trian.domain.models.Devices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WaistViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository
) :ViewModel(){
    private val listDevices = MutableLiveData<List<Devices>>()
    val devices:LiveData<List<Devices>> get() = listDevices

    fun addDevice(devices: Devices){
        val list = mutableListOf<Devices>()
        listDevices.value?.forEach {
            list.add(it)
        }
        val exist = listDevices.value!!.any { it.mac == devices.mac }
        if(exist){
           list.add(devices)
        }

        listDevices.postValue(list)
    }
}