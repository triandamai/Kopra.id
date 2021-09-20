package com.trian.bmi.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.IMeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val measurementRepository: IMeasurementRepository
) :ViewModel(){
}