package com.trian.microlife.viewmodel

import androidx.lifecycle.*

import com.trian.data.repository.IMeasurementRepository
import com.trian.domain.models.Devices

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MicrolifeViewModel @Inject constructor(
    private val measurementRepository: IMeasurementRepository,
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