package com.trian.continu_temperature.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.MeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TemperatureContinueViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository
):ViewModel() {
}